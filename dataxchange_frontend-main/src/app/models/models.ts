export interface RequestCount {
  status: string,
  myRequestCount: number,
  pendingApprovalCount: number,
  approvalButtonRequired: string,
  code: string | number
}

export interface CreateDomain {
  header: string,
  data: CreateDomainControls[]
}

export interface CreateDomainControls {
  name: string,
  type: string,
  isReq: boolean,
  value: any,
  key: string,
  valmsg: string,
  isDynamic: boolean,
  tooltip: boolean,
  placeHolder: string,
  multi?: boolean,
  data?: any[],
  reference_data_type?: string
}

export interface UserRoles {
  userMudId: string,
  haveAccess: boolean,
  roles?: []
}

export interface StandardValues {
  classificationTypes?: [],
  domainTypes?: [],
  environments?: [],
  lineOfBusiness?: [],
  piiTypes?: [],
  roles?: [],
  sourcePlatforms?: []
}

export interface IngestionRequestDetails {
  ingestionRequestId: number;
  activeRequestStatus: RequestStatusDetails;
  existingDataLocationIdentified: string;
  notes: ValidationNotes[];
  createdBy: string;
  createdDate: Date;
  modifiedBy: string;
  modifiedDate: Date;
}

export interface RequestStatusDetails {
  requestStatusId: number;
  ingestionRequestId: number;
  decisionByName: string;
  decisionByMudid: string;
  decisionByEmail: string;
  decisionDate: Date;
  decisionComments: string;
  rejectionReason: string;
  activeFlag: boolean;
  status: Status;
}

export interface Status {
  statusId: number;
  statusCode: string;
  statusName: string;
}

export interface ValidationNotes {
  notesId: number;
  ingestionRequest: IngestionRequestDetails;
  notes: string;
}

export interface ApplicationReference {
  referenceData : string,
  referenceDataType : string,
  referenceOrder: number
}

export interface IngestionRequest {
  requesterName: string;
  requesterMudid: string;
  requesterEmail: string;
  datasetName: string;
  datasetSmeName: string;
  datasetSmeMudid: string;
  datasetSmeEmail: string;
  requestRationaleReason: string;
  datasetOriginSource: string;
  currentDataLocationRef: string;
  dataLocationPath: string;
  meteorSpaceDominoUsageFlag: boolean;
  ihdFlag: boolean;
  datasetRequiredForRef: string;
  estimatedDataVolumeRef: string;
  dataRefreshFrequency: string;
  analysisInitDt: string;
  analysisEndDt: string;
  dtaContractCompleteFlag: boolean;
  dtaExpectedCompletionDate: string;
  dataCategoryRefs: string[];
  datasetTypeRef: string;
  studyIds: string[];
  datasetOwnerName: string;
  datasetOwnerMudid: string;
  datasetOwnerEmail: string;
  datasetStewardName: string;
  datasetStewardMudid: string;
  datasetStewardEmail: string;
  contractPartner: string;
  retentionRules: string;
  retentionRulesContractDate: string;
  usageRestrictions: string[];
  userRestrictions: string[];
  informationClassificationTypeRef: string;
  piiTypeRef: string;
  therapyAreas: string[];
  techniqueAndAssays: string[];
  indications: string[];
  targetIngestionStartDate: string;
  targetIngestionEndDate: string;
  targetPath: string;
  datasetTypeIngestionRef: string;
  guestUsersEmail: string[];
  whitelistIpAddresses: string[];
  externalStagingContainerName: string;
  domainRequestId: string;
  externalDataSourceLocation: string;
  gskAccessSourceLocationRef: string;
  externalSourceSecretKeyName: string;
}


export interface DecisionRequestDTO {
  decisionComments: string;
  notifyThroughEmail: boolean;
  rejectionReason?: string;
  existingDataLocationIdentified?: string;
}
