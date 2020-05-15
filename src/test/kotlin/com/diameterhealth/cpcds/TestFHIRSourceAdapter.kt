package com.diameterhealth.cpcds

import org.hl7.fhir.r4.model.ExplanationOfBenefit

class TestExplanationOfBenefitSourceAdapter(): FHIRSourceAdapter<ExplanationOfBenefit> {
    override fun exists(resource: ExplanationOfBenefit): ExplanationOfBenefit? {
        // not op
        return null
    }

    override fun create(resource: ExplanationOfBenefit): String {
        // not op
        return ""
    }

    override fun update(existingId: String, newResource: ExplanationOfBenefit) {
        // not op
    }

}