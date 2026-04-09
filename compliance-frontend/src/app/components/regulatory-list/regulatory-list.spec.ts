import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegulatoryList } from './regulatory-list';

describe('RegulatoryList', () => {
  let component: RegulatoryList;
  let fixture: ComponentFixture<RegulatoryList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegulatoryList],
    }).compileComponents();

    fixture = TestBed.createComponent(RegulatoryList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
