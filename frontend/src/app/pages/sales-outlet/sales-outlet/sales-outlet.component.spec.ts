import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesOutletComponent } from './sales-outlet.component';

describe('SalesOutletComponent', () => {
  let component: SalesOutletComponent;
  let fixture: ComponentFixture<SalesOutletComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalesOutletComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SalesOutletComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
