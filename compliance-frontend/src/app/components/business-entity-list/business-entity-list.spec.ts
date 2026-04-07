import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessEntityList } from './business-entity-list';

describe('BusinessEntityList', () => {
  let component: BusinessEntityList;
  let fixture: ComponentFixture<BusinessEntityList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusinessEntityList],
    }).compileComponents();

    fixture = TestBed.createComponent(BusinessEntityList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
