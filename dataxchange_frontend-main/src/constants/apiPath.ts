let environment = {kongUrlBase:'https://dev.api.cutom.com/marketplace', url:''}
export const GET_PREVIEW_INFO = environment.kongUrlBase+'/ddf/domain/api/v1/domainDetails/'
export const GET_REQUEST_COUNT = environment.kongUrlBase+'/ddf/access/api/v1/requestCount';
export const CREATE_DOMAIN_REQUEST = environment.kongUrlBase+'/ddf/domain/api/v1/createDomainRequest';
export const GET_STANDARD_VALUES = environment.kongUrlBase+'/ddf/ingestion/form/api/v1/standardValues';
export const GET_DOMAIN_CODE_CHECK = environment.kongUrlBase+'/ddf/domain/api/v1/validate?key=DomainCode&value=';
export const GET_SUGGEST_QUERY = environment.url+'api/v1/suggestquery';
export const EDIT_DOMAIN_REQUEST = environment.kongUrlBase+'/ddf/domain/api/v1/updateDomainRequest/';
export const GET_DOMAIN_ROLES = environment.kongUrlBase + '/ddf/access/api/v1/domainRoles?domainRequestId=';
export const GET_SEGREGATED_ENV = environment.kongUrlBase + '/ddf/access/api/v1/segregatedEnvironments?';
export const GET_REQUEST_ACCESS = environment.kongUrlBase + '/ddf/access/api/v1/accessDomainRequest';
export const ACCESS_DOMAIN_DECISION = environment.kongUrlBase + '/ddf/access/api/v1/accessDomainDecision';
export const DECISION_DOMAIN_REQUEST = environment.kongUrlBase + '/ddf/domain/api/v1/decisionDomainRequest/';
export const DECISION_DOMAIN_MANAGEMENT_REQUEST = environment.kongUrlBase + '/ddf/domain/api/v1/decisionDomainManagementRequest/';
export const GET_CATEGORY_AND_PLATFORMIDS_QUERY = environment.url+'api/v1/query';
export const EDIT_DOMAIN_REQUEST_AFTER_ACCEPTANCE = environment.kongUrlBase+'/ddf/domain/api/v1/createDomainManagementRequest/';
export const GET_INACTIVE_GROUP = environment.kongUrlBase+'/ddf/domain/api/v1/getInactiveADGroups?'
export const GET_PENDING_ACCESS_REQUEST_COUNT = environment.kongUrlBase+'/ddf/access/api/v1/getPendingAccessRequestCount?domainRequestId=';
export const GET_USER_ROLES = environment.kongUrlBase+'/ddf/domain/api/v1/getUserRoles';
export const DDF_CONSUMER_URL ='/ddfconsumer/#/search?query={"name":"DDFCatalog-HomeSearch-query","orderBy":"ddfDomainID desc"}';
export const UPLOAD_ATTACHMENT = environment.kongUrlBase+'/ddf/document/api/v1/uploadAttachment';
export const EDIT_DOMAIN_FORM ='/ddf/#';
export const GET_QUERY = environment.url+'api/v1/query';
export const REMOVE_ACCESS = environment.kongUrlBase+'/ddf/access/api/v1/revokeAccessRequest';
export const GET_DOMAIN_OWNER = environment.kongUrlBase + '/ddf/domain/api/v1/validateDomainOwner';
export const CREATE_INGESTION_REQUEST = environment.kongUrlBase + '/ddf/ingestion/form/api/v1/createTechnicalRequest';


export const Source_List_withSlash = {
    //Study Source
    "Study":"/GSK Studies/",
    "Clinical" : "/GSK Studies/Study Catalog/",
    "Drr" : "/GSK Studies/Data Reuse Requests/",
    //Veeva Source
    "Veeva": "/Veeva/",
    "Veeva_RIMS": "/Veeva/RIMS/",  
    //DDF
    "DDF":"/Data Domains/",
    "Data Domains": "/DDF/Data Domains",
    "Data Products": "/DDF/Data Products",
    "DataProduct": "/Data Products/",
 }