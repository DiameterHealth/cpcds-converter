/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import org.hl7.fhir.r4.model.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


fun mapToExplanationOfBenefits(row: CPCDSRow): ExplanationOfBenefit {
    val eob = ExplanationOfBenefit()

    if (!row.claimUniqueIdentifier.isNullOrBlank()) {
        eob.addIdentifier(
            identifier(
                row.claimUniqueIdentifier,
                "claim-unique-identifier"
            )
        )      // TODO: Made up system
    }
    if (!row.rxserviceReferenceNumber.isNullOrBlank()) {
        eob.addIdentifier(
            identifier(
                row.rxserviceReferenceNumber,
                "rx-service-ref-num"
            )
        )        // TODO: Made up system
    }

    if (!row.memberId.isNullOrBlank()) {
        eob.patient =
            reference(
                row.memberId,
                "Patient"
            )
    } else if (!row.medicalRecordNumber.isNullOrBlank()) {
        eob.patient =
            reference(
                row.medicalRecordNumber,
                "Patient"
            )
    }

    if (!row.memberAdmissionDate.isNullOrBlank()) {
        eob.billablePeriod = if (eob.hasBillablePeriod()) eob.billablePeriod else Period()
        eob.billablePeriod.start =
            parseDate(row.memberAdmissionDate)
    } else if (!row.claimServiceStartDate.isNullOrBlank()) {
        eob.billablePeriod = if (eob.hasBillablePeriod()) eob.billablePeriod else Period()
        eob.billablePeriod.start =
            parseDate(row.claimServiceStartDate)
    }
    if (!row.memberDischargeDate.isNullOrBlank()) {
        eob.billablePeriod = if (eob.hasBillablePeriod()) eob.billablePeriod else Period()
        eob.billablePeriod.end =
            parseDate(row.memberDischargeDate)
    } else if (!row.claimServiceEndDate.isNullOrBlank()) {
        eob.billablePeriod = if (eob.hasBillablePeriod()) eob.billablePeriod else Period()
        eob.billablePeriod.end =
            parseDate(row.claimServiceEndDate)
    }

    if (!row.claimPaidDate.isNullOrBlank()) {
        eob.payment = if (eob.hasPayment()) eob.payment else ExplanationOfBenefit.PaymentComponent()
        eob.payment.date =
            parseDate(row.claimPaidDate)
    }

    if (!row.claimReceivedDate.isNullOrBlank()) {
        eob.addSupportingInfo()
            .setCategory(
                code(
                    "clmrecvddate",
                    "http://hl7.org/fhir/codesystem-claim-informationcategory"
                )
            )
            .setTiming(
                parseDateType(
                    row.claimReceivedDate
                )
            )
    }

    if (!row.claimAdjustedFromIdentifier.isNullOrBlank()) {
        eob.addRelated()
            .setRelationship(
                code(
                    "prior",
                    "http://hl7.org/fhir/codesystem-related-claim-relationship"
                )
            )
            .setReference(
                identifier(
                    row.claimAdjustedFromIdentifier
                )
            )
    }

    if (!row.claimAdjustedToIdentifier.isNullOrBlank()) {
        eob.addRelated()
            .setRelationship(
                code(
                    "replaced",
                    "http://hl7.org/fhir/codesystem-related-claim-relationship"
                )
            )
            .setReference(
                identifier(
                    row.claimAdjustedToIdentifier
                )
            )
    }

    addSupportingInfo(
        eob,
        row.claimDiagnosisRelated,
        "http://example.org/fhir/CodeSystem/ms-drg",
        "ms-drg"
    )

    addSupportingInfo(
        eob,
        row.claimInpatientSourceAdmissionCode,                                                  // TODO: Not sure system
        "http://example.org/fhir/CodeSystem/typeofbill-serviceclassification-type",
        "admsrc"
    )

    addSupportingInfo(
        eob,
        row.claimInpatientAdmissionTypeCode,                                                    // TODO: Not sure system
        "http://example.org/fhir/CodeSystem/typeofbill-serviceclassification-type",
        "admtype"
    )

    addSupportingInfo(
        eob,
        row.claimBillFacilityTypeCode,
        "http://example.org/fhir/CodeSystem/typeofbill-facility-type",
        "tob-typeoffacility”"
    )

    addSupportingInfo(
        eob,
        row.claimServiceClassificationTypeCode,
        "http://example.org/fhir/CodeSystem/typeofbill-serviceclassification-type",
        "tob-billclassification"
    )

    addSupportingInfo(
        eob,
        row.claimFrequencyCode,
        "http://example.org/fhir/CodeSystem/typeofbill-frequency",
        "tob-frequency"
    )

    if (!row.claimProcessingStatusCode.isNullOrBlank()) {
        eob.status = ExplanationOfBenefit.ExplanationOfBenefitStatus.fromCode(row.claimProcessingStatusCode)
    }

    if (!row.claimTypeCode.isNullOrBlank()) {
        eob.type = code(
            row.claimTypeCode,
            "http://terminology.hl7.org/CodeSystem/claim-type"
        )
    }

    addSupportingInfo(
        eob,
        row.patientDischargeStatusCode,                                                         // TODO: Not sure system
        null,
        "discharge-status"
    )

    if (!row.claimPaymentDenialCode.isNullOrBlank()) {
        eob.payment = if (eob.hasPayment()) eob.payment else ExplanationOfBenefit.PaymentComponent()
        eob.payment.adjustmentReason =
            code(row.claimPaymentDenialCode)                                                    // TODO: Not sure system
    }

    if (!row.claimPrimaryPayerIdentifier.isNullOrBlank()) {
        eob.addInsurance()
            .setFocal(false)
            .setCoverage(
                reference(
                    row.claimPrimaryPayerIdentifier,
                    "Organization"
                )
            )
    }

    if (!row.claimPayeeTypeCode.isNullOrBlank()) {
        eob.payee = if (eob.hasPayee()) eob.payee else ExplanationOfBenefit.PayeeComponent()
        eob.payee.type =
            code(row.claimPayeeTypeCode)                                           // TODO: Not sure system
    }

    if (!row.claimPayee.isNullOrBlank()) {
        eob.payee = if (eob.hasPayee()) eob.payee else ExplanationOfBenefit.PayeeComponent()
        eob.payee.party =
            reference(row.claimPayee)                                   // TODO: Not sure how to pick type
    }

    if (!row.claimPaymentStatusCode.isNullOrBlank()) {
        eob.payment = if (eob.hasPayment()) eob.payment else ExplanationOfBenefit.PaymentComponent()
        eob.payment.type =
            code(row.claimPaymentStatusCode)                                                    // TODO: Not sure system
    }

    if (!row.claimPayerIdentifier.isNullOrBlank()) {
        eob.addInsurance()
            .setFocal(true)
            .setCoverage(
                reference(
                    row.claimPayerIdentifier,
                    "Organization"
                )
            )
    }

    if (!row.daysSupply.isNullOrBlank()) {
        eob.addSupportingInfo()
            .setCategory(code("dayssupply"))                                              // TODO: Not sure system
            .setValue(Quantity(row.daysSupply.toDouble()))
    }

    addSupportingInfo(
        eob,
        row.dawproductSelectionCode,
        null,
        "dawcode"
    )   // TODO: Not sure system

    if (!row.refillNumber.isNullOrBlank()) {
        eob.addSupportingInfo()
            .setCategory(code("refillnum"))                                              // TODO: Not sure system
            .setValue(Quantity(row.refillNumber.toDouble()))
    }

    addSupportingInfo(
        eob,
        row.prescriptionOriginCode,
        null,
        "rxorigincode"
    ) // TODO: Not sure system

    addSupportingInfo(
        eob,
        row.planReportedBrandGenericCode,
        null,
        "brandgeneric"
    ) // TODO: Not sure system

    //                                                                 TODO: Unknown mapping row.pharmacyServiceTypeCode

    if (!row.claimBillingProviderNPI.isNullOrBlank()) {
        eob.provider =
            reference(
                row.claimBillingProviderNPI,
                "Organization"
            )
    }

    addSupportingInfo(
        eob,
        row.claimBillingProviderNetworkStatus,                                                  // TODO: Not sure system
        null,
        "billingnetworkcontractingstatus"
    )

    addCareTeam(
        eob,
        row.claimAttendingProviderNPI,
        "supervising",
        true
    )
    addSupportingInfo(
        eob,
        row.claimAttendingProviderNetworkStatus,
        null,
        "attendingnetworkcontractingstatus"
    )                                                                                           // TODO: Not sure system

    if (!row.claimSiteOfServiceNPI.isNullOrBlank()) {
        eob.facility =
            reference(
                row.claimSiteOfServiceNPI,
                "Location"
            )
    }

    addSupportingInfo(
        eob,
        row.claimSiteOfServiceNetworkStatus,
        null,
        "sitenetworkcontractingstatus"
    )                                                                                           // TODO: Not sure system

    addCareTeam(
        eob,
        row.claimReferringProviderNPI,
        "referrer"
    )
    addSupportingInfo(
        eob,
        row.claimReferringProviderNetworkStatus,
        null,
        "referringnetworkcontractingstatus"
    )                                                                                           // TODO: Not sure system

    addCareTeam(
        eob,
        row.claimPerformingProviderNPI,
        "performing"
    )
    addSupportingInfo(
        eob,
        row.claimPerformingProviderNetworkStatus,
        null,
        "performingnetworkcontractingstatus"
    )                                                                                           // TODO: Not sure system

    addCareTeam(
        eob,
        row.claimPrescribingProviderNPI,
        "prescribing"
    )
    addSupportingInfo(
        eob,
        row.claimPrescribingProviderNetworkStatus,
        null,
        "prescribingnetworkcontractingstatus”"
    )                                                                                           // TODO: Not sure system

    addCareTeam(
        eob,
        row.claimPCPNPI,
        "pcp"
    )

    /*
    TODO: It is not clear at all how adjudication and total work together or do not.
            The Data Dictionary maps most of these values to total and almost nothing to adjudication.
            The Maping Spread Sheet maps it all to adjudication, but appears to add a column called "Total Amount",
                which is mapped to total. The issue is that the note for that column says that it is the total for
                each category, so it is possible we are supposed to do some math or replicated everything from adjudication
                into total.
            It seems logical that items should be totaled up to total, but I don't know why those would be different from
            the values put into adjudication at the root level. I also suspect this confusion comes from the fact that
            FHIR's EoB can have multiple items, but the CPCDS spreadsheet only has one item per line. As stated above
            we are not yet handling this correctly. However, even if that issue was resolved it is still not clear what
            the difference between adjudication and total is at the root level.
            The synthetic sample data is not sufficient to answer this question.
     */
    addAdjudicationAmount(
        eob,
        row.claimTotalSubmittedAmount,
        "submitted"
    )
    addAdjudicationAmount(
        eob,
        row.claimTotalAllowedAmount,
        "allowed"
    )
    addAdjudicationAmount(
        eob,
        row.amountPaidByPatient,
        "paidbypatient"
    )
    addAdjudicationAmount(
        eob,
        row.claimAmountPaidToProvider,
        "paidtoprovider"
    )
    addAdjudicationAmount(
        eob,
        row.memberReimbursement,
        "paidtopatient"
    )
    addAdjudicationAmount(
        eob,
        row.claimPaymentAmount,
        "payment"
    )
    addAdjudicationAmount(
        eob,
        row.claimDisallowedAmount,
        "disallowed”"
    )
    addAdjudicationAmount(
        eob,
        row.memberPaidDeductible,
        "deductible"
    )
    addAdjudicationAmount(
        eob,
        row.coInsuranceLiabilityAmount,
        "coins"
    )
    addAdjudicationAmount(
        eob,
        row.copayAmount,
        "copay"
    )
    addAdjudicationAmount(
        eob,
        row.memberLiability,
        "memberliability"
    )
    addAdjudicationAmount(
        eob,
        row.claimPrimaryPayerPaidAmount,
        "paid”"
    )
    addAdjudicationAmount(
        eob,
        row.claimDiscountAmount,
        "discount"
    )

    addTotal(
        eob,
        row.totalAmount,
        "total"
    )                                          // TODO: Not sure category

    eob.addItem(mapToItem(row))

    if (!row.placeOfServiceCode.isNullOrBlank()) {
        // According to the mapping spreadsheet this should be put in the item and the supporting info
        addSupportingInfo(
            eob,
            row.placeOfServiceCode,
            null,
            "other"
        )   // TODO: Not sure category or system
    }

    // TODO: The following are also ambiguous as to what level they go at, so I am repeating them
    // TODO: Line Benefit Payment Status is listed in the mapping spreadsheet as, at this level, mapping to a category
    // but it is not clear what it would be the category of
    // Diff on type of EoB
    addAdjudicationReason(
        eob,
        row.lineBenefitPaymentStatus,
        "inoutnetwork"
    )
    addAdjudicationReason(
        eob,
        row.linePaymentDenialCode,
        "denialreason"
    )

    if (!row.diagnosisCode.isNullOrBlank()) {
        eob.addDiagnosis(
            mapToDiagnosis(
                row
            )
        )
    }

    if (!row.procedureCode.isNullOrBlank()) {
        eob.addProcedure(
            mapToProcedure(
                row
            )
        )
    }

    /*
        TODO: There are a number of Diagnosis codes and other codes that appear in the mapping spread sheet, but not
        in the data dictionary. I am not sure how to handle these, so I have left them off for now.
     */

    return eob
}

private fun mapToProcedure(row: CPCDSRow): ExplanationOfBenefit.ProcedureComponent {
    val procedure = ExplanationOfBenefit.ProcedureComponent()

    if (!row.procedureCode.isNullOrBlank()) {
        procedure.setProcedure(
            code(
                row.procedureCode,
                row.procedureCodeType,
                row.procedureDescription
            )
        )
    }

    if (!row.procedureDate.isNullOrBlank()) {
        procedure.date =
            parseDate(row.procedureDate)
    }

    if (!row.procedureType.isNullOrBlank()) {
        procedure.addType(
            code(
                row.procedureType
            )
        )                                              // TODO: Not sure system
    }

    return procedure
}

private fun mapToDiagnosis(row: CPCDSRow): ExplanationOfBenefit.DiagnosisComponent {
    val diagnosis = ExplanationOfBenefit.DiagnosisComponent()

    if (!row.diagnosisCode.isNullOrBlank()) {
        diagnosis.setDiagnosis(
            code(
                row.diagnosisCode,
                row.diagnosisCodeType,
                row.diagnosisDescription
            )
        )
    }

    if (!row.presentOnAdmission.isNullOrBlank()) {
        diagnosis.onAdmission =
            code(row.presentOnAdmission)                                    // TODO: Not sure system
    }

    if (!row.diagnosisType.isNullOrBlank()) {
        diagnosis.addType(
            code(
                row.diagnosisType,
                "http://hl7.org/fhir/codesystem-ex-diagnosistype"
            )
        )
    }

    //                                                                   TODO: Not at all sure how to handle "Is E Code"

    return diagnosis
}

private fun mapToItem(row: CPCDSRow): ExplanationOfBenefit.ItemComponent {
    val item = ExplanationOfBenefit.ItemComponent()

    if (!row.lineNumber.isNullOrBlank()) {
        item.sequence = row.lineNumber.toInt()
    }

    if (!row.serviceFromDate.isNullOrBlank() && !row.serviceToDate.isNullOrBlank()) {
        item.setServiced(
            Period()
                .setStart(
                    parseDate(
                        row.serviceFromDate
                    )
                )
                .setEnd(
                    parseDate(
                        row.serviceToDate
                    )
                )
        )
    } else if (!row.serviceFromDate.isNullOrBlank()) {
        item.setServiced(
            parseDateType(
                row.serviceFromDate
            )
        )
    }

    if (!row.typeOfService.isNullOrBlank()) {
        item.category =
            code(row.typeOfService)                                                 // TODO: Not sure system
    }

    if (!row.placeOfServiceCode.isNullOrBlank()) {
        item.location =
            reference(
                row.placeOfServiceCode,
                "Location"
            )
    }

    if (!row.revenueCenterCode.isNullOrBlank()) {
        item.revenue =
            code(row.revenueCenterCode)                                              // TODO: Not sure system
    }

    if (!row.numberOfUnits.isNullOrBlank()) {
        // Note that number of units was struck from the data dictionary. I am including it here just in case.
        item.quantity = Quantity(row.numberOfUnits.toDouble())
    }

    addAdjudicationValue(
        item,
        row.allowedNumberOfUnits,
        "units-allowed"
    )

    if (!row.nationalDrugCode.isNullOrBlank()) {
        // TODO: the documenation agrees that this is either at the root or in the detail, but does not
        // make it clear how to tell. I am leaving at the root for now.
        item.productOrService =
            code(row.nationalDrugCode)                                      // TODO: Not sure system
    }

    if (!row.compoundCode.isNullOrBlank()) {
        // TODO: no idea what to do if both nationalDrugCOde and compoundCode are specified
        // I am making it a modifier for now, but I don't know if that is correct
        if (item.hasProductOrService()) {
            addModifier(
                item,
                code(row.compoundCode)
            )                                           // TODO: Not sure system
        } else {
            item.productOrService =
                code(row.compoundCode)                                      // TODO: Not sure system
        }
    }

    if (!row.procedureCodeCPTHCPCS.isNullOrBlank()) {
        // TODO: no idea what to do if both nationalDrugCOde and / or compoundCode and procedureCodeCPTHCPCS are specified
        // I am making it a modifier for now, but I don't know if that is correct
        if (item.hasProductOrService()) {
            addModifier(
                item,
                code(
                    row.procedureCodeCPTHCPCS,
                    null,
                    row.cptHcpcsProcedureCodeDescription
                )
            )
        } else {
            item.productOrService =
                code(
                    row.procedureCodeCPTHCPCS,
                    null,
                    row.cptHcpcsProcedureCodeDescription
                )  // TODO: Not sure system, possibly HCPCS
        }
    }

    addModifier(
        item,
        code(
            row.procedureCodeModifierCPTHCPCS,
            null,
            row.cptHcpcsProcedureCodeModifierDescription
        )
    )

    if (!row.quantityDispensed.isNullOrBlank()) {
        item.quantity = if (item.hasQuantity())
            item.quantity.setValue(row.quantityDispensed.toDouble())
        else
            Quantity(row.quantityDispensed.toDouble())
    }

    if (!row.quantityQualifierCode.isNullOrBlank()) {
        item.quantity = if (item.hasQuantity()) item.quantity else Quantity()
        item.quantity.code = row.quantityQualifierCode
    }

    addAdjudicationReason(
        item,
        row.lineBenefitPaymentStatus,
        "inoutnetwork"
    )
    addAdjudicationReason(
        item,
        row.linePaymentDenialCode,
        "denialreason"
    )

    addAdjudicationAmount(
        item,
        row.lineDisallowedAmount,
        "disallowed"
    )
    addAdjudicationAmount(
        item,
        row.lineMemberReimbursement,
        "paidtopatient"
    )
    addAdjudicationAmount(
        item,
        row.lineAmountPaidByPatient,
        "paidbypatient"
    )
    addAdjudicationAmount(
        item,
        row.drugCost,
        "drugcost"
    )
    addAdjudicationAmount(
        item,
        row.linePaymentAmount,
        "payment"
    )       // TODO: corrected spelling of category
    addAdjudicationAmount(
        item,
        row.lineAmountPaidToProvider,
        "paidtoprovider"
    )
    addAdjudicationAmount(
        item,
        row.linePatientDeductible,
        "deductible"
    )
    addAdjudicationAmount(
        item,
        row.linePrimaryPayerPaidAmount,
        "paid"
    ) // TODO: corrected spelling of category
    addAdjudicationAmount(
        item,
        row.lineCoinsuranceAmount,
        "coin"
    )
    addAdjudicationAmount(
        item,
        row.lineSubmittedAmount,
        "submitted"
    )
    addAdjudicationAmount(
        item,
        row.lineAllowedAmount,
        "allowed"
    )
    addAdjudicationAmount(
        item,
        row.lineMemberLiability,
        "memberliability"
    ) // TODO: corrected spelling of category
    addAdjudicationAmount(
        item,
        row.lineCopayAmount,
        "copay"
    )
    addAdjudicationAmount(
        item,
        row.lineDiscountAmount,
        "discount"
    )

    addModifier(
        item,
        code(row.modifierCode1)
    )                                                  // TODO: Not sure system
    addModifier(
        item,
        code(row.modifierCode2)
    )                                                  // TODO: Not sure system
    addModifier(
        item,
        code(row.modifierCode3)
    )                                                  // TODO: Not sure system
    addModifier(
        item,
        code(row.modifierCode4)
    )                                                  // TODO: Not sure system

    return item
}

private fun addModifier(item: ExplanationOfBenefit.ItemComponent, code: CodeableConcept) {
    if (code.hasCoding()) {
        item.addModifier(code)
    }
}

private fun addAdjudicationValue(item: ExplanationOfBenefit.ItemComponent, value: String?, category: String) {
    if (!value.isNullOrBlank()) {
        item.addAdjudication()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setValue(BigDecimal(value))
    }
}

private fun addAdjudicationValue(eob: ExplanationOfBenefit, value: String?, category: String) {
    if (!value.isNullOrBlank()) {
        eob.addAdjudication()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setValue(BigDecimal(value))
    }
}

private fun addAdjudicationReason(item: ExplanationOfBenefit.ItemComponent, reason: String?, category: String) {
    if (!reason.isNullOrBlank()) {
        item.addAdjudication()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setReason(
                code(
                    reason
                )
            )
    }
}


private fun addAdjudicationReason(eob: ExplanationOfBenefit, reason: String?, category: String) {
    if (!reason.isNullOrBlank()) {
        eob.addAdjudication()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setReason(
                code(
                    reason
                )
            )
    }
}

private fun addAdjudicationAmount(item: ExplanationOfBenefit.ItemComponent, amount: String?, category: String) {
    if (!amount.isNullOrBlank()) {
        item.addAdjudication()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setAmount(Money().setValue(BigDecimal(amount)))
    }
}

private fun addAdjudicationAmount(eob: ExplanationOfBenefit, amount: String?, category: String) {
    if (!amount.isNullOrBlank()) {
        eob.addAdjudication()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setAmount(Money().setValue(BigDecimal(amount)))
    }
}

private fun addTotal(eob: ExplanationOfBenefit, amount: String?, category: String) {
    if (!amount.isNullOrBlank()) {
        eob.addTotal()
            .setCategory(
                code(
                    category
                )
            )                                                        // TODO: Not sure system
            .setAmount(Money().setValue(BigDecimal(amount)))
    }
}

private fun addCareTeam(
    eob: ExplanationOfBenefit,
    npi: String?,
    role: String,
    responsible: Boolean = false,
    type: String? = "PractitionerRole"
) {
    if (!npi.isNullOrBlank()) {
        eob.addCareTeam()
            .setRole(code(role))                                                                // TODO: Not sure system
            .setResponsible(responsible)
            .setProvider(
                reference(
                    npi,
                    type
                )
            )                                                      // TODO: confirm type
    }
}

private fun addSupportingInfo(
    eob: ExplanationOfBenefit,
    code: String?,
    codeSystem: String?,
    category: String
) {
    if (!code.isNullOrBlank()) {
        eob.addSupportingInfo()
            .setCategory(
                code(
                    category,
                    "http://hl7.org/fhir/codesystem-claim-informationcategory"
                )
            )
            .setCode(
                code(
                    code,
                    codeSystem
                )
            )
    }

}

private fun reference(id: String?, type: String? = null): Reference {
    return Reference().setIdentifier(
        identifier(
            id
        )
    ).setType(type)
}


private fun identifier(id: String?, system: String? = null): Identifier {
    return Identifier().setValue(id).setSystem(system)
}

private fun code(code: String?, codeSystem: String? = null, display: String? = null): CodeableConcept {
    return CodeableConcept().addCoding(Coding().setCode(code).setSystem(codeSystem).setDisplay(display))
}

private fun parseDate(date: String): Date? =
    if (date.isNullOrBlank()) null else SimpleDateFormat("YYYY-MM-dd").parse(date)


private fun parseDateType(date: String): DateType? =
    if (date.isNullOrBlank()) null else DateType(
        parseDate(
            date
        )
    )

