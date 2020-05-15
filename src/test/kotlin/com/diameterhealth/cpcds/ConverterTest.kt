package com.diameterhealth.cpcds

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.hl7.fhir.r4.model.ExplanationOfBenefit
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

// @ExtendWith(MockitoExtension.class)
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