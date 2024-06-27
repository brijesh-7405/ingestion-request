import { TestBed } from '@angular/core/testing';

import { CommonFormService } from './common-form.service';

describe('CommonFormService', () => {
  let service: CommonFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommonFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
