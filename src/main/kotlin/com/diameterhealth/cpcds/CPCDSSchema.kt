/* COPYRIGHT NOTICE Copyright (c) 2019-2020, Diameter Health Inc.

  This code is property of Diameter Health and may only used in accordance with licensing terms of Diameter Health
  software. Redistribution and use of this code not in accordance with licensing terms of Diameter Health, Inc.
  is expressly forbidden. If you have received this code without a licensing contract with Diameter Health, notify
  info@diameterhealth.com immediately. You are not authorized to disclose, copy, distribute or retain this code without
  written authorization from Diameter Health Inc. This code contains proprietary, privileged and confidential
  information to Diameter Health, Inc.
*/
package com.diameterhealth.cpcds

import java.io.Serializable


enum class CPCDSColumn(val columnName: String, val columnNameVariant: String? = null) {
    CLAIM_SERVICE_START_DATE("Claim service start date"),
    CLAIM_SERVICE_END_DATE("Claim service end date"),
    CLAIM_PAID_DATE("Claim paid date"),
    CLAIM_RECEIVED_DATE("Claim received date", "Claim recieved date"),
    MEMBER_ADMISSION_DATE("Member admission date"),
    MEMBER_DISCHARGE_DATE("Member discharge date"),
    PATIENT_ACCOUNT_NUMBER("Patient account number"),
    MEDICAL_RECORD_NUMBER("Medical record number"),
    CLAIM_UNIQUE_IDENTIFIER("Claim unique identifier"),
    CLAIM_ADJUSTED_FROM_IDENTIFIER("Claim adjusted from identifier"),
    CLAIM_ADJUSTED_TO_IDENTIFIER("Claim adjusted to identifier"),
    CLAIM_DIAGNOSIS_RELATED("Claim diagnosis related"),
    CLAIM_INPATIENT_SOURCE_ADMISSION_CODE("Claim inpatient source admission code"),
    CLAIM_INPATIENT_ADMISSION_TYPE_CODE("Claim inpatient admission type code"),
    CLAIM_BILL_FACILITY_TYPE_CODE("Claim bill facility type code"),
    CLAIM_SERVICE_CLASSIFICATION_TYPE_CODE("Claim service classification type code"),
    CLAIM_FREQUENCY_CODE("Claim frequency code"),
    CLAIM_PROCESSING_STATUS_CODE("Claim processing status code"),
    CLAIM_TYPE_CODE("Claim type code"),
    PATIENT_DISCHARGE_STATUS_CODE("Patient discharge status code"),
    CLAIM_PAYMENT_DENIAL_CODE("Claim payment denial code"), // STOP 4/1 16%
    CLAIM_PRIMARY_PAYER_IDENTIFIER("Claim primary payer identifier"),
    CLAIM_PAYEE_TYPE_CODE("Claim payee type code"),
    CLAIM_PAYEE("Claim payee"),
    CLAIM_PAYMENT_STATUS_CODE("Claim payment status code"),
    CLAIM_PAYER_IDENTIFIER("Claim payer identifier"),
    DAYS_SUPPLY("Days supply"),
    RX_SERVICE_REFERENCE_NUMBER("RX service reference number"),
    DAW_PRODUCT_SELECTION_CODE("DAW product selection code"),
    REFILL_NUMBER("Refill number"),
    PRESCRIPTION_ORIGIN_CODE("Prescription origin code"),
    PLAN_REPORTED_BRAND_GENERIC_CODE("Plan reported brand-generic code"),
    PHARMACY_SERVICE_TYPE_CODE("Pharmacy service type code"),
    PATIENT_RESIDENCE_CODE("Patient residence code"),
    CLAIM_BILLING_PROVIDER_NPI("Claim billing provider NPI"),
    CLAIM_BILLING_PROVIDER_NETWORK_STATUS("Claim billing provider network status"),
    CLAIM_ATTENDING_PROVIDER_NPI("Claim attending provider NPI"),
    CLAIM_ATTENDING_PROVIDER_NETWORK_STATUS("Claim attending provider network status"),
    CLAIM_SITE_OF_SERVICE_NPI("Claim site of service NPI"),
    CLAIM_SITE_OF_SERVICE_NETWORK_STATUS("Claim site of service network status"),
    CLAIM_REFERRING_PROVIDER_NPI("Claim referring provider NPI"),
    CLAIM_REFERRING_PROVIDER_NETWORK_STATUS("Claim referring provider network status"),
    CLAIM_PERFORMING_PROVIDER_NPI("Claim performing provider NPI"),
    CLAIM_PERFORMING_PROVIDER_NETWORK_STATUS("Claim performing provider network status"),
    CLAIM_PRESCRIBING_PROVIDER_NPI("Claim prescribing provider NPI"),
    CLAIM_PRESCRIBING_PROVIDER_NETWORK_STATUS("Claim prescribing provider network status"),
    CLAIM_PCP_NPI("Claim PCP NPI"),
    CLAIM_TOTAL_SUBMITTED_AMOUNT("Claim total submitted amount"),
    CLAIM_TOTAL_ALLOWED_AMOUNT("Claim total allowed amount"),
    AMOUNT_PAID_BY_PATIENT("Amount paid by patient"),
    CLAIM_AMOUNT_PAID_TO_PROVIDER("Claim amount paid to provider"),
    MEMBER_REIMBURSEMENT("Member reimbursement"),
    CLAIM_PAYMENT_AMOUNT("Claim payment amount"),
    CLAIM_DISALLOWED_AMOUNT("Claim disallowed amount"),
    MEMBER_PAID_DEDUCTIBLE("Member paid deductible"),
    CO_INSURANCE_LIABILITY_AMOUNT("Co-insurance liability amount"),
    COPAY_AMOUNT("Copay amount"),
    MEMBER_LIABILITY("Member liability"),
    CLAIM_PRIMARY_PAYER_PAID_AMOUNT("Claim primary payer paid amount"),
    CLAIM_DISCOUNT_AMOUNT("Claim discount amount"),
    SERVICE__FROM__DATE("Service (from) date"),
    LINE_NUMBER("Line number"),
    SERVICE_TO_DATE("Service to date"),
    TYPE_OF_SERVICE("Type of service"),
    PLACE_OF_SERVICE_CODE("Place of service code"),
    REVENUE_CENTER_CODE("Revenue center code"),
    NUMBER_OF_UNITS("Number of units"),
    ALLOWED_NUMBER_OF_UNITS("Allowed number of units"),
    NATIONAL_DRUG_CODE("National drug code"),
    COMPOUND_CODE("Compound code"),
    QUANTITY_DISPENSED("Quantity dispensed"),
    QUANTITY_QUALIFIER_CODE("Quantity qualifier code"),
    LINE_BENEFIT_PAYMENT_STATUS("Line benefit payment status"),
    LINE_PAYMENT_DENIAL_CODE("Line payment denial code"),
    LINE_DISALLOWED_AMOUNT("Line disallowed amount"),
    LINE_MEMBER_REIMBURSEMENT("Line member reimbursement"),
    LINE_AMOUNT_PAID_BY_PATIENT("Line amount paid by patient"),
    DRUG_COST("Drug cost"),
    LINE_PAYMENT_AMOUNT("Line payment amount"),
    LINE_AMOUNT_PAID_TO_PROVIDER("Line amount paid to provider"),
    LINE_PATIENT_DEDUCTIBLE("Line patient deductible"),
    LINE_PRIMARY_PAYER_PAID_AMOUNT("Line primary payer paid amount"),
    LINE_COINSURANCE_AMOUNT("Line coinsurance amount"),
    LINE_SUBMITTED_AMOUNT("Line submitted amount"),
    LINE_ALLOWED_AMOUNT("Line allowed amount"),
    LINE_MEMBER_LIABILITY("Line member liability"),
    LINE_COPAY_AMOUNT("Line copay amount"),
    LINE_DISCOUNT_AMOUNT("Line discount amount", "Line discounted amount"),
    DIAGNOSIS_CODE("Diagnosis code"),
    DIAGNOSIS_DESCRIPTION("Diagnosis description"),
    PRESENT_ON_ADMISSION("Present on admission"),
    DIAGNOSIS_CODE_TYPE("Diagnosis code type"),
    DIAGNOSIS_TYPE("Diagnosis type"),
    IS_E_CODE("Is E code"),
    PROCEDURE_CODE("Procedure code"),
    PROCEDURE_DESCRIPTION("Procedure description"),
    PROCEDURE_DATE("Procedure date"),
    PROCEDURE_CODE_TYPE("Procedure code type"),
    PROCEDURE_TYPE("Procedure type"),
    MODIFIER_CODE__1("Modifier Code-1", "Modifier Code -1"),
    MODIFIER_CODE__2("Modifier Code-2", "Modifier Code -2"),
    MODIFIER_CODE__3("Modifier Code-3", "Modifier Code -3"),
    MODIFIER_CODE__4("Modifier Code-4", "Modifier Code -4"),

    // Below this line comes from the mapping spread sheet, not the data dictionary
    MEMBER_ID("Member id"),
    PATIENT_REASON_FOR_VISIT("Patient reason for visit"),
    DIAGNOSIS_CODE___ICD_9_ADMITTING("Diagnosis code - ICD-9 admitting"),
    DIAGNOSIS_CODE___ICD_9_PRINCIPAL("Diagnosis code - ICD-9 principal"),
    DIAGNOSIS_CODE___ICD_9_SECONDARY("Diagnosis code - ICD-9 secondary"),
    PROCEDURE_CODE___ICD_9_PCS_PRINCIPAL("Procedure Code - ICD-9 PCS principal"),
    PROCEDURE_CODE___ICD_9_PCS_SECONDARY("Procedure Code - ICD-9 PCS secondary"),
    PROCEDURE_DATE___ICD_9_PRINCIPAL("Procedure Date - ICD-9 principal"),
    PROCEDURE_DATE___ICD_9_SECONDARY("Procedure Date - ICD-9 secondary"),
    DIAGNOSIS_CODE___ICD_10_ADMITTING("Diagnosis code - ICD-10 admitting"),
    DIAGNOSIS_CODE___ICD_10_PRINCIPAL("Diagnosis code - ICD-10 principal"),
    DIAGNOSIS_CODE___ICD_10_SECONDARY("Diagnosis code - ICD-10 secondary"),
    PROCEDURE_CODE___ICD_10_PCS_PRIMARY("Procedure Code - ICD-10 PCS primary"),
    PROCEDURE_CODE___ICD_10_PCS_SECONDARY("Procedure Code - ICD-10 PCS secondary"),
    CLAIM_DIAGNOSIS_RELATED_GROUP__DRG_("Claim diagnosis related group (DRG)"),
    PROCEDURE_CODE___CPT___HCPCS("Procedure Code - CPT / HCPCS"),
    CPT___HCPCS_PROCEDURE_CODE_DESCRIPTION("CPT / HCPCS Procedure Code Description"),
    PROCEDURE_CODE_MODIFIER___CPT___HCPCS("Procedure Code Modifier - CPT / HCPCS"),
    CPT___HCPCS_PROCEDURE_CODE_MODIFIER_DESCRIPTION("CPT / HCPCS Procedure Code Modifier Description"),
    ICD_PROCEDURE_CODE_DESCRIPTION("ICD Procedure Code Description"),
    TOTAL_AMOUNT("Total Amount")
}

data class CPCDSRow (
    val claimServiceStartDate: String?,
    val claimServiceEndDate: String?,
    val claimPaidDate: String?,
    val claimReceivedDate: String?,
    val memberAdmissionDate: String?,
    val memberDischargeDate: String?,
    val patientAccountNumber: String?,
    val medicalRecordNumber: String?,
    val claimUniqueIdentifier: String?,
    val claimAdjustedFromIdentifier: String?,
    val claimAdjustedToIdentifier: String?,
    val claimDiagnosisRelated: String?,
    val claimInpatientSourceAdmissionCode: String?,
    val claimInpatientAdmissionTypeCode: String?,
    val claimBillFacilityTypeCode: String?,
    val claimServiceClassificationTypeCode: String?,
    val claimFrequencyCode: String?,
    val claimProcessingStatusCode: String?,
    val claimTypeCode: String?,
    val patientDischargeStatusCode: String?,
    val claimPaymentDenialCode: String?,
    val claimPrimaryPayerIdentifier: String?,
    val claimPayeeTypeCode: String?,
    val claimPayee: String?,
    val claimPaymentStatusCode: String?,
    val claimPayerIdentifier: String?,
    val daysSupply: String?,
    val rxserviceReferenceNumber: String?,
    val dawproductSelectionCode: String?,
    val refillNumber: String?,
    val prescriptionOriginCode: String?,
    val planReportedBrandGenericCode: String?,
    val pharmacyServiceTypeCode: String?,
    val patientResidenceCode: String?,
    val claimBillingProviderNPI: String?,
    val claimBillingProviderNetworkStatus: String?,
    val claimAttendingProviderNPI: String?,
    val claimAttendingProviderNetworkStatus: String?,
    val claimSiteOfServiceNPI: String?,
    val claimSiteOfServiceNetworkStatus: String?,
    val claimReferringProviderNPI: String?,
    val claimReferringProviderNetworkStatus: String?,
    val claimPerformingProviderNPI: String?,
    val claimPerformingProviderNetworkStatus: String?,
    val claimPrescribingProviderNPI: String?,
    val claimPrescribingProviderNetworkStatus: String?,
    val claimPCPNPI: String?,
    val claimTotalSubmittedAmount: String?,
    val claimTotalAllowedAmount: String?,
    val amountPaidByPatient: String?,
    val claimAmountPaidToProvider: String?,
    val memberReimbursement: String?,
    val claimPaymentAmount: String?,
    val claimDisallowedAmount: String?,
    val memberPaidDeductible: String?,
    val coInsuranceLiabilityAmount: String?,
    val copayAmount: String?,
    val memberLiability: String?,
    val claimPrimaryPayerPaidAmount: String?,
    val claimDiscountAmount: String?,
    val serviceFromDate: String?,
    val lineNumber: String?,
    val serviceToDate: String?,
    val typeOfService: String?,
    val placeOfServiceCode: String?,
    val revenueCenterCode: String?,
    val numberOfUnits: String?,
    val allowedNumberOfUnits: String?,
    val nationalDrugCode: String?,
    val compoundCode: String?,
    val quantityDispensed: String?,
    val quantityQualifierCode: String?,
    val lineBenefitPaymentStatus: String?,
    val linePaymentDenialCode: String?,
    val lineDisallowedAmount: String?,
    val lineMemberReimbursement: String?,
    val lineAmountPaidByPatient: String?,
    val drugCost: String?,
    val linePaymentAmount: String?,
    val lineAmountPaidToProvider: String?,
    val linePatientDeductible: String?,
    val linePrimaryPayerPaidAmount: String?,
    val lineCoinsuranceAmount: String?,
    val lineSubmittedAmount: String?,
    val lineAllowedAmount: String?,
    val lineMemberLiability: String?,
    val lineCopayAmount: String?,
    val lineDiscountAmount: String?,
    val diagnosisCode: String?,
    val diagnosisDescription: String?,
    val presentOnAdmission: String?,
    val diagnosisCodeType: String?,
    val diagnosisType: String?,
    val isECode: String?,
    val procedureCode: String?,
    val procedureDescription: String?,
    val procedureDate: String?,
    val procedureCodeType: String?,
    val procedureType: String?,
    val modifierCode1: String?,
    val modifierCode2: String?,
    val modifierCode3: String?,
    val modifierCode4: String?,

    // Below this line comes from the mapping spread sheet, not the data dictionary
    val memberId: String?,
    val patientReasonForVisit: String?,
    val diagnosisCodeICD9Admitting: String?,
    val diagnosisCodeICD9Principal: String?,
    val diagnosisCodeICD9Secondary: String?,
    val procedureCodeICD9PCSPrincipal: String?,
    val procedureCodeICD9PCSSecondary: String?,
    val procedureDateICD9Principal: String?,
    val procedureDateICD9Secondary: String?,
    val diagnosisCodeICD10Admitting: String?,
    val diagnosisCodeICD10Principal: String?,
    val diagnosisCodeICD10Secondary: String?,
    val procedureCodeICD10PCSPrimary: String?,
    val procedureCodeICD10PCSSecondary: String?,
    val claimDiagnosisRelatedGroupDRG: String?,
    val procedureCodeCPTHCPCS: String?,
    val cptHcpcsProcedureCodeDescription: String?,
    val procedureCodeModifierCPTHCPCS: String?,
    val cptHcpcsProcedureCodeModifierDescription: String?,
    val icdprocedureCodeDescription: String?,
    val totalAmount: String?,

    // Below this line are properties that do not come from CPCDS
    val retryCount: Int = 0
): Serializable

//val cpcdsSchema = CPCDSColumn.values().fold(StructType()) { structType, column -> structType.add(column.column, column.dataType)}

/*
    StructType()
    .add("Claim service start date", "string")
    .add("Claim service end date", "string")
    .add("Claim paid date", "string")
    .add("Claim received date", "string")
    .add("Member admission date", "string")
    .add("Member discharge date", "string")
    .add("Patient account number", "string")
    .add("Medical record number", "string")
    .add("Claim unique identifier", "string")
    .add("Claim adjusted from identifier", "string")
    .add("Claim adjusted to identifier", "string")
    .add("Claim diagnosis related", "string")
    .add("Claim inpatient source admission code", "string")
    .add("Claim inpatient admission type code", "string")
    .add("Claim bill facility type code", "string")
    .add("Claim service classification type code", "string")
    .add("Claim frequency code", "string")
    .add("Claim processing status code", "string")
    .add("Claim type code", "string")
    .add("Patient discharge status code", "string")
    .add("Claim payment denial code", "string")
    .add("Claim primary payer identifier", "string")
    .add("Claim payee type code", "string")
    .add("Claim payee", "string")
    .add("Claim payment status code", "string")
    .add("Claim payer identifier", "string")
    .add("Days supply", "string")
    .add("RX service reference number", "string")
    .add("DAW product selection code", "string")
    .add("Refill number", "string")
    .add("Prescription origin code", "string")
    .add("Plan reported brand-generic code", "string")
    .add("Pharmacy service type code", "string")
    .add("Patient residence code", "string")
    .add("Claim billing provider NPI", "string")
    .add("Claim billing provider network status", "string")
    .add("Claim attending provider NPI", "string")
    .add("Claim attending provider network status", "string")
    .add("Claim site of service NPI", "string")
    .add("Claim site of service network status", "string")
    .add("Claim referring provider NPI", "string")
    .add("Claim referring provider network status", "string")
    .add("Claim performing provider NPI", "string")
    .add("Claim performing provider network status", "string")
    .add("Claim prescribing provider NPI", "string")
    .add("Claim prescribing provider network status", "string")
    .add("Claim PCP NPI", "string")
    .add("Claim total submitted amount", "string")
    .add("Claim total allowed amount", "string")
    .add("Amount paid by patient", "string")
    .add("Claim amount paid to provider", "string")
    .add("Member reimbursement", "string")
    .add("Claim payment amount", "string")
    .add("Claim disallowed amount", "string")
    .add("Member paid deductible", "string")
    .add("Co-insurance liability amount", "string")
    .add("Copay amount", "string")
    .add("Member liability", "string")
    .add("Claim primary payer paid amount", "string")
    .add("Claim discount amount", "string")
    .add("Service (from) date", "string")
    .add("Line number", "string")
    .add("Service to date", "string")
    .add("Type of service", "string")
    .add("Place of service code", "string")
    .add("Revenue center code", "string")
    .add("Number of units", "string") // REMOVED
    .add("Allowed number of units", "string")
    .add("National drug code", "string")
    .add("Compound code", "string")
    .add("Quantity dispensed", "string")
    .add("Quantity qualifier code", "string")
    .add("Line benefit payment status", "string")
    .add("Line payment denial code", "string")
    .add("Line disallowed amount", "string")
    .add("Line member reimbursement", "string")
    .add("Line amount paid by patient", "string")
    .add("Drug cost", "string")
    .add("Line payment amount", "string")
    .add("Line amount paid to provider", "string")
    .add("Line patient deductible", "string")
    .add("Line primary payer paid amount", "string")
    .add("Line coinsurance amount", "string")
    .add("Line submitted amount", "string")
    .add("Line allowed amount", "string")
    .add("Line member liability", "string")
    .add("Line copay amount", "string")
    .add("Line discount amount", "string")
    .add("Diagnosis code", "string")
    .add("Diagnosis description", "string")
    .add("Present on admission", "string")
    .add("Diagnosis code type", "string")
    .add("Diagnosis type", "string")
    .add("Is E code", "string")
    .add("Procedure code", "string")
    .add("Procedure description", "string")
    .add("Procedure date", "string")
    .add("Procedure code type", "string")
    .add("Procedure type", "string")
    .add("Modifier Code -1", "string")
    .add("Modifier Code -2", "string")
    .add("Modifier Code -3", "string")
    .add("Modifier Code -4", "string")

    // Below this line comes from the mapping spread sheet, not the data dictionary
    .add("Member id", "string")
    .add("Diagnosis code - ICD-9 admitting", "string")
    .add("Diagnosis code - ICD-9 principal", "string")
    .add("Diagnosis code - ICD-9 secondary", "string")
    .add("Procedure Code - ICD-9 PCS principal", "string")
    .add("Procedure Code - ICD-9 PCS secondary", "string")
    .add("Procedure Date - ICD-9 principal", "string")
    .add("Procedure Date - ICD-9 secondary", "string")
    .add("Diagnosis code - ICD-10 admitting", "string")
    .add("Diagnosis code - ICD-10 principal", "string")
    .add("Diagnosis code - ICD-10 secondary", "string")
    .add("Procedure Code - ICD-10 PCS primary", "string")
    .add("Procedure Code - ICD-10 PCS secondary", "string")
    .add("Patient reason for visit", "string")
    .add("Claim diagnosis related group (DRG)", "string")
    .add("Procedure Code - CPT / HCPCS", "string")
    .add("ICD Procedure Code Description", "string")
    .add("CPT / HCPCS Procedure Code Description", "string")

 */