/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.hl7.fhir.r4.model.ExplanationOfBenefit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.jupiter.api.Assertions.*

class ConverterTest {

    @Mock
    lateinit var sourceAdapter: FHIRSourceAdapter<ExplanationOfBenefit>

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `one claim is precessed`() {
        Mockito.`when`(sourceAdapter.exists(any())).thenReturn(null)
        Mockito.`when`(sourceAdapter.create(any())).thenReturn("TEST_ID")

        val file = "data/CPCDS_Claims_one_claim.csv".asResourceFile()
        convert(file, sourceAdapter, null)

        verify(sourceAdapter).exists(any())
        verify(sourceAdapter).create(any())
        verify(sourceAdapter, times(0)).update(any(), any())
    }


    @Test
    fun `one claim is has correct patient reference`() {
        Mockito.`when`(sourceAdapter.exists(any())).thenReturn(null)
        Mockito.`when`(sourceAdapter.create(any())).thenAnswer { invocation ->
                val eob = invocation.arguments[0] as ExplanationOfBenefit
                assertEquals("Patient/57ecca3a-7157-414f-bc00-1345618b9656", eob.patient.reference)

                "TEST_ID"
        }

        val file = "data/CPCDS_Claims_one_claim.csv".asResourceFile()
        convert(file, sourceAdapter, null)
    }

    @Test
    fun `multiple claims are precessed`() {
        Mockito.`when`(sourceAdapter.exists(any())).thenReturn(null)
        Mockito.`when`(sourceAdapter.create(any())).thenReturn("TEST_ID")

        val file = "data/CPCDS_Claims_multi_claim.csv".asResourceFile()
        convert(file, sourceAdapter, null)

        verify(sourceAdapter, times(3)).exists(any())
        verify(sourceAdapter, times(3)).create(any())
        verify(sourceAdapter, times(0)).update(any(), any())
    }

    @Test
    fun `multiple items are precessed`() {
        var eob = ExplanationOfBenefit()

        Mockito.`when`(sourceAdapter.create(any()))
            .then {
                eob = it.arguments[0] as ExplanationOfBenefit
                eob.id = "TEST_ID"
                return@then eob.id
            }

        Mockito.`when`(sourceAdapter.exists(any()))
            .thenReturn(null)
            .then {
                return@then eob
            }

        val file = "data/CPCDS_Claims_multi_item.csv".asResourceFile()
        convert(file, sourceAdapter, null)

        verify(sourceAdapter, times(5)).exists(any())
        verify(sourceAdapter, times(1)).create(any())
        verify(sourceAdapter, times(4)).update(any(), any())
    }
}