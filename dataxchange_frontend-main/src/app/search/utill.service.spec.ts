import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UtillService } from './utill.service';

describe('UtillService', () => {
  let service: UtillService;
  let httpMock: HttpTestingController;
  let mockUrl: string;
  let mockResponse: any;
  let mockMudId: string;
  let mockData: any;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UtillService],
    });

    service = TestBed.inject(UtillService);
    httpMock = TestBed.inject(HttpTestingController);
    mockUrl = '/api/mock';
    mockResponse = { data: 'data' };
    mockMudId = '123';
    mockData = { key: 'value' };
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should make GET request in getRequestCount()', () => {
    service.getRequestCount(mockMudId).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
  });

  it('should make GET request in getPreviewInfoData()', () => {
    service.getPreviewInfoData(mockMudId, mockUrl).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
  });

  it('should make POST request in createDomain()', () => {
    service.createDomain(mockMudId, mockData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockData);
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
  });

  it('should make GET request in getStandardValues()', () => {
    service.getStandardValues(mockMudId).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
  });

  it('should make POST request in technicalOwner()', () => {
    const mockName = 'name';
    service.technicalOwner(mockName, mockUrl).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);
  });

  it('should make GET request in getDomainRoles()', () => {
    service.getDomainRoles(mockUrl).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });

  it('should make GET request in getSegregateEnvs()', () => {
    service.getSegregateEnvs(mockUrl).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockResponse);
  });

  it('should make POST request in requestAccess()', () => {
    service.requestAccess(mockData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);
  });

  it('should make POST request in domainAccessRequest()', () => {
    service.domainAccessRequest(mockData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);
  });

  it('should make PUT request in decissionDomainAccess()', () => {
    service.decisionDomainAccess(mockMudId, mockUrl, mockData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req =  httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toBe(mockData);
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
  });

  it('should make PUT request in editDomain()', () => {
    service.editDomain(mockMudId, mockUrl, mockData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(mockData);
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
});

it('should make POST request in getCategory()', () => {
  service.getCategory(mockUrl).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);
});

it('should make POST request in getTagsList()', () => {
    const mockName = 'name';
    const postData = {
        "app": "DDFCatalogSearch",
        "suggestQuery": "DDF_SciSearch_Domain_Tags",
        "text": mockName
    }

    service.getTagsList(mockName, mockUrl).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(postData);
    req.flush(mockResponse);
});

it('should make POST request in editDomainAfterAcceptance()', () => {
    service.editDomainAfterAcceptance(mockMudId, mockUrl, mockData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(mockUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockData);
    expect(req.request.headers.get('cutom-apigw-user-mud-id')).toBe(mockMudId);
    req.flush(mockResponse);
});
});