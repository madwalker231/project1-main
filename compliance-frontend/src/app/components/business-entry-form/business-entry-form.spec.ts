import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessEntryForm } from './business-entry-form';

describe('BusinessEntryForm', () => {
  let component: BusinessEntryForm;
  let fixture: ComponentFixture<BusinessEntryForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusinessEntryForm],
    }).compileComponents();

    fixture = TestBed.createComponent(BusinessEntryForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
