/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import org.hl7.fhir.r4.model.ExplanationOfBenefit

data class ConvertContext(
    val cpcdsRow: CPCDSRow,
    val explanationOfBenefit: ExplanationOfBenefit,
    val update: Boolean = false,
    val updateId: String? = null
)

fun writeRow(row: CPCDSRow, fhirSourceAdapter: FHIRSourceAdapter<ExplanationOfBenefit>) {

    println("Writing for Claim: ${row.claimUniqueIdentifier} at ${nowToString()}")
    var context = checkExists(
        ConvertContext(cpcdsRow = row, explanationOfBenefit = mapToExplanationOfBenefits(row)),
        fhirSourceAdapter
    )

    if (context.update) {
        fhirSourceAdapter.update(context.updateId!!, context.explanationOfBenefit)
        println("Updated EoB: ${context.updateId} at ${nowToString()}")
    } else {
        val id = fhirSourceAdapter.create(context.explanationOfBenefit)
        println("Create EoB: ${id} at ${nowToString()}")
    }
}


private fun checkExists(
    convertContext: ConvertContext,
    fhirSourceAdapter: FHIRSourceAdapter<ExplanationOfBenefit>
): ConvertContext {
    println(
        "Checked for EoB: ${convertContext.explanationOfBenefit.identifier[0].system}:${convertContext.explanationOfBenefit.identifier[0].value} at ${nowToString()}"
    )
    val existingEoB = fhirSourceAdapter.exists(convertContext.explanationOfBenefit)
    val newEoB = convertContext.explanationOfBenefit

    if (existingEoB != null) {
        println("Found one")
    } else {
        println("Did not find existing")
    }
    return when {
        (existingEoB == null) -> convertContext
        (newEoB.item.isEmpty()) -> {
            throw Exception(
                "CPCDS Row with id ${convertContext.cpcdsRow.claimUniqueIdentifier} " +
                        "is not a valid Explanation Of Benefits, because it has no item, and will not be processed"
            )
        }
        (newEoB.item[0].sequence < 2) ->
            convertContext.copy(
                update = true,
                updateId = existingEoB.id
            )
        (newEoB.item[0].sequence == existingEoB.item.size + 1) ->
            convertContext.copy(
                explanationOfBenefit = addItem(existingEoB, newEoB.item[0]),
                update = true,
                updateId = existingEoB.id
            )
        (newEoB.item[0].sequence <= existingEoB.item.size) ->
            convertContext.copy(
                explanationOfBenefit = replaceItem(existingEoB, newEoB.item[0]),
                update = true,
                updateId = existingEoB.id
            )
        else -> {
            throw Exception(
                "CPCDS Row with id ${convertContext.cpcdsRow.claimUniqueIdentifier} " +
                        "and line number ${convertContext.cpcdsRow.lineNumber} " +
                        "has can't be processed (it could be out of order)"
            )
        }
    }
}

private fun addItem(
    existing: ExplanationOfBenefit,
    item: ExplanationOfBenefit.ItemComponent
): ExplanationOfBenefit = existing.addItem(item)  // TODO: I don't think we need to do anything about totals here

private fun replaceItem(
    existing: ExplanationOfBenefit,
    item: ExplanationOfBenefit.ItemComponent
): ExplanationOfBenefit {
    // TODO: Probably need to change totals and adjudicated at EoB level, but I am not sure this is even a valid use case
    existing.item[item.sequence] = item
    return existing
}