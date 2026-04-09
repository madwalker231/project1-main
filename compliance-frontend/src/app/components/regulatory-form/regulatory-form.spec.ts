import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegulatoryForm } from './regulatory-form';

describe('RegulatoryForm', () => {
  let component: RegulatoryForm;
  let fixture: ComponentFixture<RegulatoryForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegulatoryForm],
    }).compileComponents();

    fixture = TestBed.createComponent(RegulatoryForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
