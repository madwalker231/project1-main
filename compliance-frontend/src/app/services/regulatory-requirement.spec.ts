import { TestBed } from '@angular/core/testing';

import { RegulatoryRequirement } from './regulatory-requirement';

describe('RegulatoryRequirement', () => {
  let service: RegulatoryRequirement;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegulatoryRequirement);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
