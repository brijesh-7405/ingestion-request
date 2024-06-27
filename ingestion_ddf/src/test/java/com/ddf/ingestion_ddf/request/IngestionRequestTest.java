package com.ddf.ingestion_ddf.request;

import com.ddf.ingestion_ddf.util.TestDataUtil;
import com.ddf.ingestion_ddf.validators.ValidationGroups;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the {@link IngestionRequest} class.
 */
public class IngestionRequestTest {

    private static Validator validator;

    /**
     * Setup method to initialize the validator before running tests.
     */
    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Test case to verify the getter and setter methods of the {@link IngestionRequest} class.
     */
    @Test
    public void testGetterSetterMethods() {
        // Arrange
        IngestionRequest ingestionRequest = new IngestionRequest();
        String expectedRequesterName = "John Doe";
        String expectedRequesterMudid = "JD12345";
        String expectedRequesterEmail = "john.doe@example.com";
        String expectedDatasetName = "Clinical Trial Data";
        String expectedDatasetSmeName = "Jane Smith";
        String expectedDatasetSmeMudid = "JS54321";
        String expectedDatasetSmeEmail = "jane.smith@example.com";
        String expectedRequestRationaleReason = "For statistical analysis";
        String expectedDatasetOriginSource = "Internal Research";
        Boolean expectedMeteorSpaceDominoUsageFlag = true;
        Boolean expectedIhdFlag = false;
        String expectedDatasetRequiredForRef = "Exploration";
        String expectedEstimatedDataVolumeRef = "500GB";
        String expectedDataRefreshFrequency = "Monthly";
        Date expectedAnalysisInitDt = new Date(2024, 1, 1, 0, 0, 0); // 2024-01-01T00:00:00Z
        Date expectedAnalysisEndDt = new Date(2024, 12, 31, 23, 59, 59); // 2024-12-31T23:59:59Z
        Boolean expectedDtaContractCompleteFlag = true;
        Date expectedDtaExpectedCompletionDate = new Date(2024, 6, 30, 0, 0, 0); // 2024-06-30T00:00:00Z
        List<String> expectedDataCategoryRefs = Arrays.asList("Clinical", "Genomics");
        String expectedDatasetTypeRef = "Clinical Trial Data";
        List<String> expectedStudyIds = Arrays.asList("ST123", "ST456");
        String expectedDatasetOwnerName = "Alice Johnson";
        String expectedDatasetOwnerMudid = "AJ67890";
        String expectedDatasetOwnerEmail = "alice.johnson@example.com";
        String expectedDatasetStewardName = "Bob Williams";
        String expectedDatasetStewardMudid = "BW09876";
        String expectedDatasetStewardEmail = "bob.williams@example.com";
        String expectedContractPartner = "XYZ Pharmaceuticals";
        String expectedRetentionRules = "7 years";
        Date expectedRetentionRulesContractDate = new Date(2024, 1, 1, 0, 0, 0); // 2024-01-01T00:00:00Z
        List<String> expectedUsageRestrictions = Arrays.asList("Internal Use Only", "Confidential");
        List<String> expectedUserRestrictions = Arrays.asList("Role-Based Access", "Restricted Access");
        String expectedInformationClassificationTypeRef = "Confidential";
        String expectedPiiTypeRef = "None";
        List<String> expectedTherapyAreas = Arrays.asList("Cardiology", "Neurology");
        List<String> expectedTechniqueAndAssays = Arrays.asList("PCR", "ELISA");
        List<String> expectedIndications = Arrays.asList("Hypertension", "Alzheimer's Disease");
        Date expectedTargetIngestionStartDate = new Date(2024, 7, 1, 0, 0, 0); // 2024-07-01T00:00:00Z
        Date expectedTargetIngestionEndDate = new Date(2024, 7, 31, 23, 59, 59); // 2024-07-31T23:59:59Z
        String expectedTargetPath = "/data/ingestion/clinical_trials";
        String expectedDatasetTypeIngestionRef = "Batch";
        List<String> expectedGuestUsersEmail = Arrays.asList("guest1@example.com", "guest2@example.com");
        List<String> expectedWhitelistIpAddresses = Arrays.asList("192.168.1.1", "192.168.1.2");
        String expectedExternalStagingContainerName = "staging-container";
        String expectedDomainRequestId = "DR78901";
        String expectedExternalDataSourceLocation = "/external/source/location";
        String expectedGskAccessSourceLocationRef = "GSK Internal Server";
        String expectedExternalSourceSecretKeyName = "external-secret-key";
        String expectedModifiedReason = "Modified Reason";
        String expectedCurrentDataLocationRef = "Data Warehouse";
        String expectedDataLocationPath = "/data/warehouse/clinical_trials";

        // Act
        ingestionRequest.setRequesterName(expectedRequesterName);
        ingestionRequest.setRequesterMudid(expectedRequesterMudid);
        ingestionRequest.setRequesterEmail(expectedRequesterEmail);
        ingestionRequest.setDatasetName(expectedDatasetName);
        ingestionRequest.setDatasetSmeName(expectedDatasetSmeName);
        ingestionRequest.setDatasetSmeMudid(expectedDatasetSmeMudid);
        ingestionRequest.setDatasetSmeEmail(expectedDatasetSmeEmail);
        ingestionRequest.setCurrentDataLocationRef(expectedCurrentDataLocationRef);
        ingestionRequest.setDataLocationPath(expectedDataLocationPath);
        ingestionRequest.setRequestRationaleReason(expectedRequestRationaleReason);
        ingestionRequest.setDatasetOriginSource(expectedDatasetOriginSource);
        ingestionRequest.setMeteorSpaceDominoUsageFlag(expectedMeteorSpaceDominoUsageFlag);
        ingestionRequest.setIhdFlag(expectedIhdFlag);
        ingestionRequest.setDatasetRequiredForRef(expectedDatasetRequiredForRef);
        ingestionRequest.setEstimatedDataVolumeRef(expectedEstimatedDataVolumeRef);
        ingestionRequest.setDataRefreshFrequency(expectedDataRefreshFrequency);
        ingestionRequest.setAnalysisInitDt(expectedAnalysisInitDt);
        ingestionRequest.setAnalysisEndDt(expectedAnalysisEndDt);
        ingestionRequest.setDtaContractCompleteFlag(expectedDtaContractCompleteFlag);
        ingestionRequest.setDtaExpectedCompletionDate(expectedDtaExpectedCompletionDate);
        ingestionRequest.setDataCategoryRefs(expectedDataCategoryRefs);
        ingestionRequest.setDatasetTypeRef(expectedDatasetTypeRef);
        ingestionRequest.setStudyIds(expectedStudyIds);
        ingestionRequest.setDatasetOwnerName(expectedDatasetOwnerName);
        ingestionRequest.setDatasetOwnerMudid(expectedDatasetOwnerMudid);
        ingestionRequest.setDatasetOwnerEmail(expectedDatasetOwnerEmail);
        ingestionRequest.setDatasetStewardName(expectedDatasetStewardName);
        ingestionRequest.setDatasetStewardMudid(expectedDatasetStewardMudid);
        ingestionRequest.setDatasetStewardEmail(expectedDatasetStewardEmail);
        ingestionRequest.setContractPartner(expectedContractPartner);
        ingestionRequest.setRetentionRules(expectedRetentionRules);
        ingestionRequest.setRetentionRulesContractDate(expectedRetentionRulesContractDate);
        ingestionRequest.setUsageRestrictions(expectedUsageRestrictions);
        ingestionRequest.setUserRestrictions(expectedUserRestrictions);
        ingestionRequest.setInformationClassificationTypeRef(expectedInformationClassificationTypeRef);
        ingestionRequest.setPiiTypeRef(expectedPiiTypeRef);
        ingestionRequest.setTherapyAreas(expectedTherapyAreas);
        ingestionRequest.setTechniqueAndAssays(expectedTechniqueAndAssays);
        ingestionRequest.setIndications(expectedIndications);
        ingestionRequest.setTargetIngestionStartDate(expectedTargetIngestionStartDate);
        ingestionRequest.setTargetIngestionEndDate(expectedTargetIngestionEndDate);
        ingestionRequest.setTargetPath(expectedTargetPath);
        ingestionRequest.setDatasetTypeIngestionRef(expectedDatasetTypeIngestionRef);
        ingestionRequest.setGuestUsersEmail(expectedGuestUsersEmail);
        ingestionRequest.setWhitelistIpAddresses(expectedWhitelistIpAddresses);
        ingestionRequest.setExternalStagingContainerName(expectedExternalStagingContainerName);
        ingestionRequest.setDomainRequestId(expectedDomainRequestId);
        ingestionRequest.setExternalDataSourceLocation(expectedExternalDataSourceLocation);
        ingestionRequest.setGskAccessSourceLocationRef(expectedGskAccessSourceLocationRef);
        ingestionRequest.setExternalSourceSecretKeyName(expectedExternalSourceSecretKeyName);
        ingestionRequest.setModifiedReason(expectedModifiedReason);

        // Assert
        assertEquals(expectedRequesterName, ingestionRequest.getRequesterName());
        assertEquals(expectedRequesterMudid, ingestionRequest.getRequesterMudid());
        assertEquals(expectedRequesterEmail, ingestionRequest.getRequesterEmail());
        assertEquals(expectedDatasetName, ingestionRequest.getDatasetName());
        assertEquals(expectedDatasetSmeName, ingestionRequest.getDatasetSmeName());
        assertEquals(expectedDatasetSmeMudid, ingestionRequest.getDatasetSmeMudid());
        assertEquals(expectedDatasetSmeEmail, ingestionRequest.getDatasetSmeEmail());
        assertEquals(expectedRequestRationaleReason, ingestionRequest.getRequestRationaleReason());
        assertEquals(expectedDatasetOriginSource, ingestionRequest.getDatasetOriginSource());
        assertEquals(expectedMeteorSpaceDominoUsageFlag, ingestionRequest.getMeteorSpaceDominoUsageFlag());
        assertEquals(expectedIhdFlag, ingestionRequest.getIhdFlag());
        assertEquals(expectedDatasetRequiredForRef, ingestionRequest.getDatasetRequiredForRef());
        assertEquals(expectedEstimatedDataVolumeRef, ingestionRequest.getEstimatedDataVolumeRef());
        assertEquals(expectedDataRefreshFrequency, ingestionRequest.getDataRefreshFrequency());
        assertEquals(expectedAnalysisInitDt, ingestionRequest.getAnalysisInitDt());
        assertEquals(expectedAnalysisEndDt, ingestionRequest.getAnalysisEndDt());
        assertEquals(expectedDtaContractCompleteFlag, ingestionRequest.getDtaContractCompleteFlag());
        assertEquals(expectedDtaExpectedCompletionDate, ingestionRequest.getDtaExpectedCompletionDate());
        assertEquals(expectedDataCategoryRefs, ingestionRequest.getDataCategoryRefs());
        assertEquals(expectedDatasetTypeRef, ingestionRequest.getDatasetTypeRef());
        assertEquals(expectedStudyIds, ingestionRequest.getStudyIds());
        assertEquals(expectedDatasetOwnerName, ingestionRequest.getDatasetOwnerName());
        assertEquals(expectedDatasetOwnerMudid, ingestionRequest.getDatasetOwnerMudid());
        assertEquals(expectedDatasetOwnerEmail, ingestionRequest.getDatasetOwnerEmail());
        assertEquals(expectedDatasetStewardName, ingestionRequest.getDatasetStewardName());
        assertEquals(expectedDatasetStewardMudid, ingestionRequest.getDatasetStewardMudid());
        assertEquals(expectedDatasetStewardEmail, ingestionRequest.getDatasetStewardEmail());
        assertEquals(expectedContractPartner, ingestionRequest.getContractPartner());
        assertEquals(expectedRetentionRules, ingestionRequest.getRetentionRules());
        assertEquals(expectedRetentionRulesContractDate, ingestionRequest.getRetentionRulesContractDate());
        assertEquals(expectedUsageRestrictions, ingestionRequest.getUsageRestrictions());
        assertEquals(expectedUserRestrictions, ingestionRequest.getUserRestrictions());
        assertEquals(expectedInformationClassificationTypeRef, ingestionRequest.getInformationClassificationTypeRef());
        assertEquals(expectedPiiTypeRef, ingestionRequest.getPiiTypeRef());
        assertEquals(expectedTherapyAreas, ingestionRequest.getTherapyAreas());
        assertEquals(expectedTechniqueAndAssays, ingestionRequest.getTechniqueAndAssays());
        assertEquals(expectedIndications, ingestionRequest.getIndications());
        assertEquals(expectedTargetIngestionStartDate, ingestionRequest.getTargetIngestionStartDate());
        assertEquals(expectedTargetIngestionEndDate, ingestionRequest.getTargetIngestionEndDate());
        assertEquals(expectedTargetPath, ingestionRequest.getTargetPath());
        assertEquals(expectedDatasetTypeIngestionRef, ingestionRequest.getDatasetTypeIngestionRef());
        assertEquals(expectedGuestUsersEmail, ingestionRequest.getGuestUsersEmail());
        assertEquals(expectedWhitelistIpAddresses, ingestionRequest.getWhitelistIpAddresses());
        assertEquals(expectedExternalStagingContainerName, ingestionRequest.getExternalStagingContainerName());
        assertEquals(expectedDomainRequestId, ingestionRequest.getDomainRequestId());
        assertEquals(expectedExternalDataSourceLocation, ingestionRequest.getExternalDataSourceLocation());
        assertEquals(expectedGskAccessSourceLocationRef, ingestionRequest.getGskAccessSourceLocationRef());
        assertEquals(expectedExternalSourceSecretKeyName, ingestionRequest.getExternalSourceSecretKeyName());
        assertEquals(expectedModifiedReason, ingestionRequest.getModifiedReason());
        assertEquals(expectedCurrentDataLocationRef, ingestionRequest.getCurrentDataLocationRef());
        assertEquals(expectedDataLocationPath, ingestionRequest.getDataLocationPath());

    }

    /**
     * Test case to verify a valid ingestion request.
     */
    @Test
    public void testValidIngestionRequest() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        Errors errors = validateRequest(ingestionRequest);
        assertFalse(errors.hasErrors());
    }

    /**
     * Test case to verify an invalid ingestion request with an empty requester name.
     * Verifies that the requester name cannot be empty as per the @NotBlank validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithEmptyRequesterName() {
        // Create a test ingestion request
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        // Set the requester name to an empty string
        ingestionRequest.setRequesterName(""); // Invalid as per @NotBlank
        // Assertion
        assertFieldError(ingestionRequest, "requesterName", "Requester name is required", ValidationGroups.BasicFieldValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with an invalid email.
     * Verifies that the requester email must be a valid email address as per the @Email validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithInvalidEmail() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        // Set an invalid email address
        ingestionRequest.setRequesterEmail("invalid-email"); // Invalid as per @Email
        assertFieldError(ingestionRequest, "requesterEmail", "Requester email must be a valid email address");
    }

    /**
     * Test case to verify an invalid ingestion request with a null MeteorSpaceDominoUsageFlag.
     * Verifies that the Meteor space Domino usage flag cannot be null as per the @NotNull validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithNullFlag() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        // Set the Meteor space Domino usage flag to null
        ingestionRequest.setMeteorSpaceDominoUsageFlag(null); // Invalid as per @NotNull
        assertFieldError(ingestionRequest, "meteorSpaceDominoUsageFlag", "Meteor space Domino usage flag is required",ValidationGroups.BasicFieldValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with a long requester name.
     * Verifies that the requester name cannot exceed the maximum allowed length as per the @Size validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithLongRequesterName() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        String longName = "A".repeat(256); // 256 characters long string
        ingestionRequest.setRequesterName(longName); // Invalid as per @Size(max = 255)
        assertFieldError(ingestionRequest, "requesterName", "Requester name must be at most 255 characters long");
    }

    /**
     * Test case to verify an invalid ingestion request with multiple errors.
     * Verifies that when multiple fields violate their respective validation constraints,
     * all error messages are captured and reported correctly.
     */
    @Test
    public void testInvalidIngestionRequestWithMultipleErrors() {
        // Arrange
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setRequesterName(""); // Invalid as per @NotBlank
        ingestionRequest.setRequesterEmail("invalid-email"); // Invalid as per @Email
        ingestionRequest.setDatasetName(""); // Invalid as per @NotBlank

        // Act
        Set<ConstraintViolation<IngestionRequest>> violations = validator.validate(ingestionRequest, ValidationGroups.BasicFieldValidationGroup.class);

        // Assert
        assertTrue(violations.size() > 0);

        // Check specific error messages
        for (ConstraintViolation<IngestionRequest> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            switch (fieldName) {
                case "requesterName":
                    assertEquals("Requester name is required", errorMessage);
                    break;
                case "requesterEmail":
                    assertEquals("Requester email must be a valid email address", errorMessage);
                    break;
                case "datasetName":
                    assertEquals("Dataset name is required", errorMessage);
                    break;
                default:
                    fail("Unexpected validation error: " + errorMessage);
                    break;
            }
        }
    }

    /**
     * Test case to verify an invalid ingestion request with an empty Dataset SME Email.
     * Verifies that the Dataset SME Email field cannot be empty as per the @NotBlank validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithEmptyDatasetSmeEmail() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setDatasetSmeEmail(""); // Invalid as per @NotBlank
        assertFieldError(ingestionRequest, "datasetSmeEmail", "Dataset SME email is required",ValidationGroups.BasicFieldValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with null Analysis Dates.
     * Verifies that both the Analysis Init Date and Analysis End Date fields cannot be null as per the @NotNull validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithAnalysisDates() {
        // Arrange
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setAnalysisInitDt(null); // Invalid as per @NotNull
        ingestionRequest.setAnalysisEndDt(null); // Invalid as per @NotNull

        // Act
        Errors errors = validateRequest(ingestionRequest,ValidationGroups.PrioritizationDetailValidationGroup.class);

        // Assert
        assertTrue(errors.hasErrors());
        assertEquals("Analysis init date is required", errors.getFieldError("analysisInitDt").getDefaultMessage());
        assertEquals("Analysis end date is required", errors.getFieldError("analysisEndDt").getDefaultMessage());
    }

    /**
     * Test case to verify an invalid ingestion request with a null DTA Contract Complete Flag.
     * Verifies that the DTA Contract Complete Flag field cannot be null as per the @NotNull validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithNullDtaContractCompleteFlag() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setDtaContractCompleteFlag(null); // Invalid as per @NotNull
        assertFieldError(ingestionRequest, "dtaContractCompleteFlag", "DTA contract complete flag is required",ValidationGroups.PrioritizationDetailValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with missing Requester MUDID.
     * Verifies that the Requester MUDID field cannot be empty as per the @NotBlank validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithMissingRequesterMudid() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setRequesterMudid(""); // Invalid as per @NotBlank
        assertFieldError(ingestionRequest, "requesterMudid", "Requester MUDID is required",ValidationGroups.BasicFieldValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with an empty Dataset Required For Reference field.
     * Verifies that the Dataset Required For Reference field cannot be empty as per the @NotBlank validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithEmptyDatasetRequiredForRef() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setDatasetRequiredForRef(""); // Invalid as per @NotBlank
        assertFieldError(ingestionRequest, "datasetRequiredForRef", "Dataset required for reference is required",ValidationGroups.BasicFieldValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with an empty Data Refresh Frequency.
     * Verifies that the Data Refresh Frequency field cannot be empty as per the @NotBlank validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithEmptyDataRefreshFrequency() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setDataRefreshFrequency(""); // Invalid as per @NotBlank
        assertFieldError(ingestionRequest, "dataRefreshFrequency", "Data refresh frequency is required",ValidationGroups.PrioritizationDetailValidationGroup.class);
    }

    /**
     * Test case to verify an invalid ingestion request with an empty Dataset Type Reference.
     * Verifies that the Dataset Type Reference field cannot be empty as per the @NotBlank validation constraint.
     */
    @Test
    public void testInvalidIngestionRequestWithEmptyDatasetTypeRef() {
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();
        ingestionRequest.setDatasetTypeRef(""); // Invalid as per @NotBlank
        assertFieldError(ingestionRequest, "datasetTypeRef", "Dataset type reference is required",ValidationGroups.DatasetDetailsValidationGroup.class);
    }

    /**
     * Test case to verify a valid ingestion request with optional fields.
     */
    @Test
    public void testValidIngestionRequestWithOptionalFields() {
        // Arrange
        IngestionRequest ingestionRequest = TestDataUtil.createTestIngestionRequest();

        // Act
        Errors errors = validateRequest(ingestionRequest);

        // Assert
        assertFalse(errors.hasErrors());
    }

    /**
     * Validates the provided {@link IngestionRequest} instance using the validator and specified validation groups.
     *
     * @param ingestionRequest The ingestion request to validate.
     * @param groups           The validation groups to apply.
     * @return The validation errors.
     */
    private Errors validateRequest(IngestionRequest ingestionRequest, Class<?>... groups) {
        Errors errors = new BeanPropertyBindingResult(ingestionRequest, "ingestionRequest");
        SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
        springValidator.validate(ingestionRequest, errors, groups);
        return errors;
    }

    /**
     * Asserts that a field error with the given field name and error message exists in the validation errors.
     *
     * @param ingestionRequest     The ingestion request to validate.
     * @param fieldName            The name of the field with the error.
     * @param expectedErrorMessage The expected error message.
     * @param groups               The validation groups to apply.
     */
    private void assertFieldError(IngestionRequest ingestionRequest, String fieldName, String expectedErrorMessage, Class<?>... groups) {
        Errors errors = validateRequest(ingestionRequest, groups);
        assertTrue(errors.hasFieldErrors(fieldName));
        assertEquals(expectedErrorMessage, errors.getFieldError(fieldName).getDefaultMessage());
    }
}
