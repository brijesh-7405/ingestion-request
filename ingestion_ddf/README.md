
# Ingestion Request

## Technology Used
<ul>
<li>Java 17</li>
<li>Spring Boot 3.2.5</li>
<li>PostgreSQL 13</li>
<li>Junit 5</li>
</ul>

## Database Connection
### Configure Database Connection
To connect the application to your PostgreSQL database,please update the `application.properties` file with the following properties:
```sh
# Database connection
spring.datasource.url=jdbc:postgresql://localhost:5432/ingestion_service
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=org.postgresql.Driver
```
***Note: Make sure to create a new database named ingestion_service in your PostgreSQL server.***

#
## Email Configuration
### Configure SMTP Settings
For SMTP configuration, update the `application.properties` file with your SMTP server details:
```sh
# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<email-id>
spring.mail.password=<password>
spring.mail.properties.mail.smtp.auth=true
```
***Note: Replace `<email-id>` with your email-address/username of smtp and `<password>` with your password of smtp.***

#

## Swagger Documentation

I have also implemented Swagger documentation for the APIs. You can access it through the following link: [Swagger Documentation](http://localhost:3000/api/v1/swagger-ui.html)
**<p>Swagger Link:http://localhost:3000/api/v1/swagger-ui.html</p>**
#

## Ingestion Request API

### 1. Create Ingestion Request
Submit a new ingestion request.


**Endpoint:** `POST /api/v1/ingestion_requests?submit=false`

**Request:**
```sh
curl -X 'POST' \
  'http://localhost:3000/api/v1/ingestion_requests?submit=false' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "requesterName": "John Doe",
  "requesterMudid": "JD12345",
  "requesterEmail": "john.doe@example.com",
  "datasetName": "Clinical Trial Data",
  "datasetSmeName": "Jane Smith",
  "datasetSmeMudid": "JS54321",
  "datasetSmeEmail": "jane.smith@example.com",
  "requestRationaleReason": "For statistical analysis",
  "datasetOriginSource": "Internal Research",
  "currentDataLocationRef": "Data Warehouse",
  "dataLocationPath": "/data/warehouse/clinical_trials",
  "meteorSpaceDominoUsageFlag": true,
  "ihdFlag": false,
  "datasetRequiredForRef": "Exploration",
  "estimatedDataVolumeRef": "500GB",
  "dataRefreshFrequency": "Monthly",
  "analysisInitDt": "2024-01-01T00:00:00Z",
  "analysisEndDt": "2024-12-31T23:59:59Z",
  "dtaContractCompleteFlag": true,
  "dtaExpectedCompletionDate": "2024-06-30T00:00:00Z",
  "dataCategoryRefs": ["Clinical", "Genomics"],
  "datasetTypeRef": "Clinical Trial Data",
  "studyIds": ["ST123", "ST456"],
  "datasetOwnerName": "Alice Johnson",
  "datasetOwnerMudid": "AJ67890",
  "datasetOwnerEmail": "alice.johnson@example.com",
  "datasetStewardName": "Bob Williams",
  "datasetStewardMudid": "BW09876",
  "datasetStewardEmail": "bob.williams@example.com",
  "contractPartner": "XYZ Pharmaceuticals",
  "retentionRules": "7 years",
  "retentionRulesContractDate": "2024-01-01T00:00:00Z",
  "usageRestrictions": ["Internal Use Only", "Confidential"],
  "userRestrictions": ["Role-Based Access", "Restricted Access"],
  "informationClassificationTypeRef": "Confidential",
  "piiTypeRef": "None",
  "therapyAreas": ["Cardiology", "Neurology"],
  "techniqueAndAssays": ["PCR", "ELISA"],
  "indications": ["Hypertension", "Alzheimer'\''s Disease"],
  "targetIngestionStartDate": "2024-07-01T00:00:00Z",
  "targetIngestionEndDate": "2024-07-31T23:59:59Z",
  "targetPath": "/data/ingestion/clinical_trials",
  "datasetTypeIngestionRef": "Batch",
  "guestUsersEmail": ["guest1@example.com", "guest2@example.com"],
  "whitelistIpAddresses": ["192.168.1.1", "192.168.1.2"],
  "externalStagingContainerName": "staging-container",
  "domainRequestId": "DR78901",
  "externalDataSourceLocation": "/external/source/location",
  "gskAccessSourceLocationRef": "GSK Internal Server",
  "externalSourceSecretKeyName": "external-secret-key"
}
'
```
**Response:**  JSON object with details of the ingestion request.

### 2. Get Ingestion Request by ID
Retrieve details of a specific ingestion request.

**Endpoint:** `GET /api/v1/ingestion_requests/17`

**Request:**
```sh
curl -X 'GET' \
 'http://localhost:3000/api/v1/ingestion_requests/17' \
 -H 'accept: */*'
 ```
**Response:**  JSON object with details of the ingestion request.


### 3. Update Ingestion Request by ID
Update details of a specific ingestion request.

**Endpoint:** `PUT /api/v1/ingestion_requests/17?submit=false`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/17?submit=false' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "requesterName": "John Doe Updated",
  "requesterMudid": "JD12345",
  "requesterEmail": "john.doe@example.com",
  "datasetName": "Clinical Trial Data",
  "datasetSmeName": "Jane Smith",
  "datasetSmeMudid": "JS54321",
  "datasetSmeEmail": "jane.smith@example.com",
  "requestRationaleReason": "For statistical analysis",
  "datasetOriginSource": "Internal Research",
  "currentDataLocationRef": "Data Warehouse",
  "dataLocationPath": "/data/warehouse/clinical_trials",
  "meteorSpaceDominoUsageFlag": true,
  "ihdFlag": false,
  "datasetRequiredForRef": "Exploration",
  "estimatedDataVolumeRef": "500GB",
  "dataRefreshFrequency": "Monthly",
  "analysisInitDt": "2024-01-01T00:00:00Z",
  "analysisEndDt": "2024-12-31T23:59:59Z",
  "dtaContractCompleteFlag": true,
  "dtaExpectedCompletionDate": "2024-06-30T00:00:00Z",
  "dataCategoryRefs": ["Clinical", "Genomics"],
  "datasetTypeRef": "Clinical Trial Data",
  "studyIds": ["ST123", "ST456"],
  "datasetOwnerName": "Alice Johnson",
  "datasetOwnerMudid": "AJ67890",
  "datasetOwnerEmail": "alice.johnson@example.com",
  "datasetStewardName": "Bob Williams",
  "datasetStewardMudid": "BW09876",
  "datasetStewardEmail": "bob.williams@example.com",
  "contractPartner": "XYZ Pharmaceuticals",
  "retentionRules": "7 years",
  "retentionRulesContractDate": "2024-01-01T00:00:00Z",
  "usageRestrictions": ["Internal Use Only", "Confidential"],
  "userRestrictions": ["Role-Based Access", "Restricted Access"],
  "informationClassificationTypeRef": "Confidential",
  "piiTypeRef": "None",
  "therapyAreas": ["Cardiology", "Neurology"],
  "techniqueAndAssays": ["PCR", "ELISA"],
  "indications": ["Hypertension", "Alzheimer'\''s Disease"],
  "targetIngestionStartDate": "2024-07-01T00:00:00Z",
  "targetIngestionEndDate": "2024-07-31T23:59:59Z",
  "targetPath": "/data/ingestion/clinical_trials",
  "datasetTypeIngestionRef": "Batch",
  "guestUsersEmail": ["guest1@example.com", "guest2@example.com"],
  "whitelistIpAddresses": ["192.168.1.1", "192.168.1.2"],
  "externalStagingContainerName": "staging-container",
  "domainRequestId": "DR78901",
  "externalDataSourceLocation": "/external/source/location",
  "gskAccessSourceLocationRef": "GSK Internal Server",
  "externalSourceSecretKeyName": "external-secret-key"
}'
```
**Response:** JSON object with updated details of the ingestion request.


### 4. Get Ingestion Requests with Filters
Retrieve a list of ingestion requests with specified filters.

**Endpoint:** `GET /api/v1/ingestion_requests?my_approvals=false&my_submissions=true&status=All&page=1&per_page=20&order_by=MODIFIED_DATE&order_direction=DESC`

**Request:**
```sh
curl -X 'GET' \
  'http://localhost:3000/api/v1/ingestion_requests?my_approvals=false&my_submissions=true&status=All&page=1&per_page=20&order_by=MODIFIED_DATE&order_direction=DESC' \
  -H 'accept: */*'
 ```
**Response:**  JSON object with details of the ingestion request.


#
## Ingestion Request Status

### 1. Submit Ingestion Request
Submit a specific ingestion request.

**Endpoint:** `PUT /api/v1/ingestion_requests/16/submit`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/16/submit' \
  -H 'accept: */*'
  ```
**Response:** Status update from DRAFT to TRIAGE PENDING APPROVAL

### 2. Reject Ingestion Request
Reject a specific ingestion request with comments.

**Endpoint:** `PUT /api/v1/ingestion_requests/15/reject`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/15/reject' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "decisionComments": "decisionComments",
  "notifyThroughEmail": true,
  "rejectionReason": "rejectionReason",
  "existingDataLocationIdentified": "existingDataLocationIdentified"
}'
  ```
**Response:** Status update from TRIAGE PENDING APPROVAL to REJECTED

### 3. Approve Ingestion Request
Approve a specific ingestion request.

**Endpoint:** `PUT /api/v1/ingestion_requests/15/approve`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/16/approve' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "decisionComments": "decisionComments",
  "notifyThroughEmail": true
}'
  ```
**Response:** Status update from TRIAGE PENDING APPROVAL to APPROVED

### 4. Mark Ingestion Request as In Progress
Mark a specific ingestion request as ingestion in progress.

**Endpoint:** `PUT /api/v1/ingestion_requests/16/ingestion_in_progress`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/16/ingestion_in_progress' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "decisionComments": "decisionComments",
  "notifyThroughEmail": true
}'
  ```
**Response:** Status update from APPROVED to INGESTION_IN_PROGRESS

### 5. Mark Ingestion Request as Failed
Mark a specific ingestion request as ingestion failure.

**Endpoint:** `PUT /api/v1/ingestion_requests/16/ingestion_failure`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/16/ingestion_failure' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "decisionComments": "decisionComments",
  "notifyThroughEmail": true
}'
  ```
**Response:** Status update from INGESTION_IN_PROGRESS to INGESTION_FAILURE

### 6. Mark Ingestion Request as Complete
Mark a specific ingestion request as ingestion complete.

**Endpoint:** `PUT /api/v1/ingestion_requests/16/ingestion_complete`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/16/ingestion_complete' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "decisionComments": "decisionComments",
  "notifyThroughEmail": true
}'
  ```
**Response:** Status update from INGESTION_IN_PROGRESS to INGESTION_COMPLETED

#

## Ingestion Request Note

### 1. Add a Note to Ingestion Request
Add a new note to a specific ingestion request.

**Endpoint:** `POST /api/v1/ingestion_requests/16/notes`

**Request:**
```sh
curl -X 'POST' \
  'http://localhost:3000/api/v1/ingestion_requests/16/notes' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "notes": "demoNotes"
}'

  ```
**Response:** JSON data of added note

### 2. Update Ingestion Request Note by ID
Update a note on a specific ingestion request.

**Endpoint:** `PUT /api/v1/ingestion_requests/16/notes/5`

**Request:**
```sh
curl -X 'PUT' \
  'http://localhost:3000/api/v1/ingestion_requests/16/notes/5' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "notes": "updatedNotes"
}'
  ```
**Response:** JSON data of updated note

### 3.  Delete Ingestion Request Note by ID
Delete a note from a specific ingestion request.

**Endpoint:** `DELETE /api/v1/ingestion_requests/16/notes/5`

**Request:**
```sh
curl -X 'DELETE' \
  'http://localhost:3000/api/v1/ingestion_requests/16/notes/5' \
  -H 'accept: */*'
  ```
**Response:** Deleted the note successfully

#
## Application Reference
### 1.  Get Application References
Retrieve a list of application references.

**Endpoint:** `GET /api/v1/application_references`

**Request:**
```sh
curl -X 'GET' \
  'http://localhost:3000/api/v1/application_references' \
  -H 'accept: */*'
  ```
**Response:** JSON data of all application refereences sorted by refrence_order

  




