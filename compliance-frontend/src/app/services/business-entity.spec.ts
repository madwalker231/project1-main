import { TestBed } from '@angular/core/testing';

import { BusinessEntity } from './business-entity';

describe('BusinessEntity', () => {
  let service: BusinessEntity;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessEntity);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
