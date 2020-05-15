/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import org.hl7.fhir.r4.model.ExplanationOfBenefit
import java.io.File
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    val parser = ArgParser("Converter")
    val input by parser.option(ArgType.String, shortName = "i", description = "Input CPCDS CSV file").required()
    val fhirUrl by parser.option(ArgType.String, shortName = "f", description = "FHIR Server Endpoint").required()
    val delay by parser.option(ArgType.String, shortName = "d", description = "Delay between calls in milliseconds")

    parser.parse(args)

    println("Starting converter at ${nowToString()}")
    println("Loading from: ${input}")
    println("Loading to: ${fhirUrl}")
    println("Delay by: ${delay}")

    val sourceAdapter = HapiExplanationOfBenefitsFHIRSourceAdapter(fhirUrl)
    convert(File(input), sourceAdapter, delay)
}

fun convert(file: File, fhirSourceAdapter: FHIRSourceAdapter<ExplanationOfBenefit>, delay: String?) {
    csvReader().readAllWithHeader(file)
        .map(::mapToCPCDSRow)
        .forEach {
            writeRow(it, fhirSourceAdapter)
            if (delay != null) {
                TimeUnit.MILLISECONDS.sleep(delay.toLong())
            }
        }
}