package com.diameterhealth.cpcds


fun mapToCPCDSRow(row: Map<String, String>): CPCDSRow {

    println("Mapping for Claim: ${row[CPCDSColumn.CLAIM_UNIQUE_IDENTIFIER.columnName]} at ${nowToString()}")
    return CPCDSRow(
        claimServiceStartDate = valueAsString(
            row,
            CPCDSColumn.CLAIM_SERVICE_START_DATE
        ),
        claimServiceEndDate = valueAsString(
            row,
            CPCDSColumn.CLAIM_SERVICE_END_DATE
        ),
        claimPaidDate = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAID_DATE
        ),
        claimReceivedDate = valueAsString(
            row,
            CPCDSColumn.CLAIM_RECEIVED_DATE
        ),
        memberAdmissionDate = valueAsString(
            row,
            CPCDSColumn.MEMBER_ADMISSION_DATE
        ),
        memberDischargeDate = valueAsString(
            row,
            CPCDSColumn.MEMBER_DISCHARGE_DATE
        ),
        patientAccountNumber = valueAsString(
            row,
            CPCDSColumn.PATIENT_ACCOUNT_NUMBER
        ),
        medicalRecordNumber = valueAsString(
            row,
            CPCDSColumn.MEDICAL_RECORD_NUMBER
        ),
        claimUniqueIdentifier = valueAsString(
            row,
            CPCDSColumn.CLAIM_UNIQUE_IDENTIFIER
        ),
        claimAdjustedFromIdentifier = valueAsString(
            row,
            CPCDSColumn.CLAIM_ADJUSTED_FROM_IDENTIFIER
        ),
        claimAdjustedToIdentifier = valueAsString(
            row,
            CPCDSColumn.CLAIM_ADJUSTED_TO_IDENTIFIER
        ),
        claimDiagnosisRelated = valueAsString(
            row,
            CPCDSColumn.CLAIM_DIAGNOSIS_RELATED
        ),
        claimInpatientSourceAdmissionCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_INPATIENT_SOURCE_ADMISSION_CODE
        ),
        claimInpatientAdmissionTypeCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_INPATIENT_ADMISSION_TYPE_CODE
        ),
        claimBillFacilityTypeCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_BILL_FACILITY_TYPE_CODE
        ),
        claimServiceClassificationTypeCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_SERVICE_CLASSIFICATION_TYPE_CODE
        ),
        claimFrequencyCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_FREQUENCY_CODE
        ),
        claimProcessingStatusCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_PROCESSING_STATUS_CODE
        ),
        claimTypeCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_TYPE_CODE
        ),
        patientDischargeStatusCode = valueAsString(
            row,
            CPCDSColumn.PATIENT_DISCHARGE_STATUS_CODE
        ),
        claimPaymentDenialCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAYMENT_DENIAL_CODE
        ),
        claimPrimaryPayerIdentifier = valueAsString(
            row,
            CPCDSColumn.CLAIM_PRIMARY_PAYER_IDENTIFIER
        ),
        claimPayeeTypeCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAYEE_TYPE_CODE
        ),
        claimPayee = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAYEE
        ),
        claimPaymentStatusCode = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAYMENT_STATUS_CODE
        ),
        claimPayerIdentifier = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAYER_IDENTIFIER
        ),
        daysSupply = valueAsString(
            row,
            CPCDSColumn.DAYS_SUPPLY
        ),
        rxserviceReferenceNumber = valueAsString(
            row,
            CPCDSColumn.RX_SERVICE_REFERENCE_NUMBER
        ),
        dawproductSelectionCode = valueAsString(
            row,
            CPCDSColumn.DAW_PRODUCT_SELECTION_CODE
        ),
        refillNumber = valueAsString(
            row,
            CPCDSColumn.REFILL_NUMBER
        ),
        prescriptionOriginCode = valueAsString(
            row,
            CPCDSColumn.PRESCRIPTION_ORIGIN_CODE
        ),
        planReportedBrandGenericCode = valueAsString(
            row,
            CPCDSColumn.PLAN_REPORTED_BRAND_GENERIC_CODE
        ),
        pharmacyServiceTypeCode = valueAsString(
            row,
            CPCDSColumn.PHARMACY_SERVICE_TYPE_CODE
        ),
        patientResidenceCode = valueAsString(
            row,
            CPCDSColumn.PATIENT_RESIDENCE_CODE
        ),
        claimBillingProviderNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_BILLING_PROVIDER_NPI
        ),
        claimBillingProviderNetworkStatus = valueAsString(
            row,
            CPCDSColumn.CLAIM_BILLING_PROVIDER_NETWORK_STATUS
        ),
        claimAttendingProviderNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_ATTENDING_PROVIDER_NPI
        ),
        claimAttendingProviderNetworkStatus = valueAsString(
            row,
            CPCDSColumn.CLAIM_ATTENDING_PROVIDER_NETWORK_STATUS
        ),
        claimSiteOfServiceNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_SITE_OF_SERVICE_NPI
        ),
        claimSiteOfServiceNetworkStatus = valueAsString(
            row,
            CPCDSColumn.CLAIM_SITE_OF_SERVICE_NETWORK_STATUS
        ),
        claimReferringProviderNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_REFERRING_PROVIDER_NPI
        ),
        claimReferringProviderNetworkStatus = valueAsString(
            row,
            CPCDSColumn.CLAIM_REFERRING_PROVIDER_NETWORK_STATUS
        ),
        claimPerformingProviderNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_PERFORMING_PROVIDER_NPI
        ),
        claimPerformingProviderNetworkStatus = valueAsString(
            row,
            CPCDSColumn.CLAIM_PERFORMING_PROVIDER_NETWORK_STATUS
        ),
        claimPrescribingProviderNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_PRESCRIBING_PROVIDER_NPI
        ),
        claimPrescribingProviderNetworkStatus = valueAsString(
            row,
            CPCDSColumn.CLAIM_PRESCRIBING_PROVIDER_NETWORK_STATUS
        ),
        claimPCPNPI = valueAsString(
            row,
            CPCDSColumn.CLAIM_PCP_NPI
        ),
        claimTotalSubmittedAmount = valueAsString(
            row,
            CPCDSColumn.CLAIM_TOTAL_SUBMITTED_AMOUNT
        ),
        claimTotalAllowedAmount = valueAsString(
            row,
            CPCDSColumn.CLAIM_TOTAL_ALLOWED_AMOUNT
        ),
        amountPaidByPatient = valueAsString(
            row,
            CPCDSColumn.AMOUNT_PAID_BY_PATIENT
        ),
        claimAmountPaidToProvider = valueAsString(
            row,
            CPCDSColumn.CLAIM_AMOUNT_PAID_TO_PROVIDER
        ),
        memberReimbursement = valueAsString(
            row,
            CPCDSColumn.MEMBER_REIMBURSEMENT
        ),
        claimPaymentAmount = valueAsString(
            row,
            CPCDSColumn.CLAIM_PAYMENT_AMOUNT
        ),
        claimDisallowedAmount = valueAsString(
            row,
            CPCDSColumn.CLAIM_DISALLOWED_AMOUNT
        ),
        memberPaidDeductible = valueAsString(
            row,
            CPCDSColumn.MEMBER_PAID_DEDUCTIBLE
        ),
        coInsuranceLiabilityAmount = valueAsString(
            row,
            CPCDSColumn.CO_INSURANCE_LIABILITY_AMOUNT
        ),
        copayAmount = valueAsString(
            row,
            CPCDSColumn.COPAY_AMOUNT
        ),
        memberLiability = valueAsString(
            row,
            CPCDSColumn.MEMBER_LIABILITY
        ),
        claimPrimaryPayerPaidAmount = valueAsString(
            row,
            CPCDSColumn.CLAIM_PRIMARY_PAYER_PAID_AMOUNT
        ),
        claimDiscountAmount = valueAsString(
            row,
            CPCDSColumn.CLAIM_DISCOUNT_AMOUNT
        ),
        serviceFromDate = valueAsString(
            row,
            CPCDSColumn.SERVICE__FROM__DATE
        ),
        lineNumber = valueAsString(
            row,
            CPCDSColumn.LINE_NUMBER
        ),
        serviceToDate = valueAsString(
            row,
            CPCDSColumn.SERVICE_TO_DATE
        ),
        typeOfService = valueAsString(
            row,
            CPCDSColumn.TYPE_OF_SERVICE
        ),
        placeOfServiceCode = valueAsString(
            row,
            CPCDSColumn.PLACE_OF_SERVICE_CODE
        ),
        revenueCenterCode = valueAsString(
            row,
            CPCDSColumn.REVENUE_CENTER_CODE
        ),
        numberOfUnits = valueAsString(
            row,
            CPCDSColumn.NUMBER_OF_UNITS
        ),
        allowedNumberOfUnits = valueAsString(
            row,
            CPCDSColumn.ALLOWED_NUMBER_OF_UNITS
        ),
        nationalDrugCode = valueAsString(
            row,
            CPCDSColumn.NATIONAL_DRUG_CODE
        ),
        compoundCode = valueAsString(
            row,
            CPCDSColumn.COMPOUND_CODE
        ),
        quantityDispensed = valueAsString(
            row,
            CPCDSColumn.QUANTITY_DISPENSED
        ),
        quantityQualifierCode = valueAsString(
            row,
            CPCDSColumn.QUANTITY_QUALIFIER_CODE
        ),
        lineBenefitPaymentStatus = valueAsString(
            row,
            CPCDSColumn.LINE_BENEFIT_PAYMENT_STATUS
        ),
        linePaymentDenialCode = valueAsString(
            row,
            CPCDSColumn.LINE_PAYMENT_DENIAL_CODE
        ),
        lineDisallowedAmount = valueAsString(
            row,
            CPCDSColumn.LINE_DISALLOWED_AMOUNT
        ),
        lineMemberReimbursement = valueAsString(
            row,
            CPCDSColumn.LINE_MEMBER_REIMBURSEMENT
        ),
        lineAmountPaidByPatient = valueAsString(
            row,
            CPCDSColumn.LINE_AMOUNT_PAID_BY_PATIENT
        ),
        drugCost = valueAsString(
            row,
            CPCDSColumn.DRUG_COST
        ),
        linePaymentAmount = valueAsString(
            row,
            CPCDSColumn.LINE_PAYMENT_AMOUNT
        ),
        lineAmountPaidToProvider = valueAsString(
            row,
            CPCDSColumn.LINE_AMOUNT_PAID_TO_PROVIDER
        ),
        linePatientDeductible = valueAsString(
            row,
            CPCDSColumn.LINE_PATIENT_DEDUCTIBLE
        ),
        linePrimaryPayerPaidAmount = valueAsString(
            row,
            CPCDSColumn.LINE_PRIMARY_PAYER_PAID_AMOUNT
        ),
        lineCoinsuranceAmount = valueAsString(
            row,
            CPCDSColumn.LINE_COINSURANCE_AMOUNT
        ),
        lineSubmittedAmount = valueAsString(
            row,
            CPCDSColumn.LINE_SUBMITTED_AMOUNT
        ),
        lineAllowedAmount = valueAsString(
            row,
            CPCDSColumn.LINE_ALLOWED_AMOUNT
        ),
        lineMemberLiability = valueAsString(
            row,
            CPCDSColumn.LINE_MEMBER_LIABILITY
        ),
        lineCopayAmount = valueAsString(
            row,
            CPCDSColumn.LINE_COPAY_AMOUNT
        ),
        lineDiscountAmount = valueAsString(
            row,
            CPCDSColumn.LINE_DISCOUNT_AMOUNT
        ),
        diagnosisCode = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE
        ),
        diagnosisDescription = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_DESCRIPTION
        ),
        presentOnAdmission = valueAsString(
            row,
            CPCDSColumn.PRESENT_ON_ADMISSION
        ),
        diagnosisCodeType = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE_TYPE
        ),
        diagnosisType = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_TYPE
        ),
        isECode = valueAsString(
            row,
            CPCDSColumn.IS_E_CODE
        ),
        procedureCode = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE
        ),
        procedureDescription = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_DESCRIPTION
        ),
        procedureDate = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_DATE
        ),
        procedureCodeType = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE_TYPE
        ),
        procedureType = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_TYPE
        ),
        modifierCode1 = valueAsString(
            row,
            CPCDSColumn.MODIFIER_CODE__1
        ),
        modifierCode2 = valueAsString(
            row,
            CPCDSColumn.MODIFIER_CODE__2
        ),
        modifierCode3 = valueAsString(
            row,
            CPCDSColumn.MODIFIER_CODE__3
        ),
        modifierCode4 = valueAsString(
            row,
            CPCDSColumn.MODIFIER_CODE__4
        ),
        memberId = valueAsString(
            row,
            CPCDSColumn.MEMBER_ID
        ),
        diagnosisCodeICD9Admitting = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE___ICD_9_ADMITTING
        ),
        diagnosisCodeICD9Principal = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE___ICD_9_PRINCIPAL
        ),
        diagnosisCodeICD9Secondary = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE___ICD_9_SECONDARY
        ),
        procedureCodeICD9PCSPrincipal = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE___ICD_9_PCS_PRINCIPAL
        ),
        procedureCodeICD9PCSSecondary = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE___ICD_9_PCS_SECONDARY
        ),
        procedureDateICD9Principal = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_DATE___ICD_9_PRINCIPAL
        ),
        procedureDateICD9Secondary = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_DATE___ICD_9_SECONDARY
        ),
        diagnosisCodeICD10Admitting = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE___ICD_10_ADMITTING
        ),
        diagnosisCodeICD10Principal = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE___ICD_10_PRINCIPAL
        ),
        diagnosisCodeICD10Secondary = valueAsString(
            row,
            CPCDSColumn.DIAGNOSIS_CODE___ICD_10_SECONDARY
        ),
        procedureCodeICD10PCSPrimary = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE___ICD_10_PCS_PRIMARY
        ),
        procedureCodeICD10PCSSecondary = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE___ICD_10_PCS_SECONDARY
        ),
        patientReasonForVisit = valueAsString(
            row,
            CPCDSColumn.PATIENT_REASON_FOR_VISIT
        ),
        claimDiagnosisRelatedGroupDRG = valueAsString(
            row,
            CPCDSColumn.CLAIM_DIAGNOSIS_RELATED_GROUP__DRG_
        ),
        procedureCodeCPTHCPCS = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE___CPT___HCPCS
        ),
        cptHcpcsProcedureCodeDescription = valueAsString(
            row,
            CPCDSColumn.CPT___HCPCS_PROCEDURE_CODE_DESCRIPTION
        ),
        procedureCodeModifierCPTHCPCS = valueAsString(
            row,
            CPCDSColumn.PROCEDURE_CODE_MODIFIER___CPT___HCPCS
        ),
        cptHcpcsProcedureCodeModifierDescription = valueAsString(
            row,
            CPCDSColumn.CPT___HCPCS_PROCEDURE_CODE_MODIFIER_DESCRIPTION
        ),
        icdprocedureCodeDescription = valueAsString(
            row,
            CPCDSColumn.ICD_PROCEDURE_CODE_DESCRIPTION
        ),
        totalAmount = valueAsString(
            row,
            CPCDSColumn.TOTAL_AMOUNT
        )
    )
}

private fun valueAsString(row: Map<String, String>, column: CPCDSColumn): String? =
    if (!row[column.columnName].isNullOrBlank())
        row[column.columnName]
    else if (!column.columnNameVariant.isNullOrBlank() && !row[column.columnNameVariant].isNullOrBlank())
        row[column.columnNameVariant]
    else null
