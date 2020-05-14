/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.rest.client.api.IGenericClient
import ca.uhn.fhir.util.BundleUtil
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.ExplanationOfBenefit

class HapiExplanationOfBenefitsFHIRSourceAdapter(val fhirUrl: String) : FHIRSourceAdapter<ExplanationOfBenefit> {
/* "http://localhost:8080/hapi-fhir-jpaserver/fhir" */

    override fun exists(resource: ExplanationOfBenefit): ExplanationOfBenefit? {
        val bundle = fhirClient()
            .search<Bundle>()
            .forResource(ExplanationOfBenefit::class.java)
            .where(
                ExplanationOfBenefit.IDENTIFIER.exactly().systemAndValues(
                    resource.identifier[0].system,
                    resource.identifier[0].value
                )
            )
            .returnBundle(Bundle::class.java)
            .execute()

        val existing =
            BundleUtil.toListOfResourcesOfType(FhirContext.forR4(), bundle, ExplanationOfBenefit::class.java)

        return if (existing.isEmpty()) null else existing[0]
    }

    override fun create(resource: ExplanationOfBenefit): String {
        return fhirClient()
            .create()
            .resource(resource)
            .execute()
            .id.toString()
    }

    override fun update(existingId: String, newResource: ExplanationOfBenefit) {
        fhirClient()
            .update()
            .resource(newResource)
            .withId(existingId)
            .execute()
    }

    private fun fhirClient(): IGenericClient = FhirContext.forR4().newRestfulGenericClient(fhirUrl)
}