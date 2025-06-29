import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormVendorComponent } from './form-vendor.component';

describe('FormVendorComponent', () => {
  let component: FormVendorComponent;
  let fixture: ComponentFixture<FormVendorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormVendorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormVendorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
