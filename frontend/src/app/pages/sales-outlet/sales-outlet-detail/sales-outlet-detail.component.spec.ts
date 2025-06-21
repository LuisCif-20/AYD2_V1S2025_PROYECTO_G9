import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesOutletDetailComponent } from './sales-outlet-detail.component';

describe('SalesOutletDetailComponent', () => {
  let component: SalesOutletDetailComponent;
  let fixture: ComponentFixture<SalesOutletDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalesOutletDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SalesOutletDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
