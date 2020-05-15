/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import com.google.common.io.Resources
import java.io.File
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun nowToString() = DateTimeFormatter.ISO_INSTANT.format(Instant.now())

fun dateToLocalDateTime(date: Date?): LocalDateTime? =
    if (date == null) null else date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

fun String.asResourceFile(): File = File(Resources.getResource(this).file)