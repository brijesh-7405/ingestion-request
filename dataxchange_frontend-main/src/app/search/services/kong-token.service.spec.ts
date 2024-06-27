import { TestBed } from '@angular/core/testing';

import { KongTokenService } from './kong-token.service';

describe('KongTokenService', () => {
  let service: KongTokenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KongTokenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
