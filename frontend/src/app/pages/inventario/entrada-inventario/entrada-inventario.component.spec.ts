import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradaInventarioComponent } from './entrada-inventario.component';

describe('EntradaInventarioComponent', () => {
  let component: EntradaInventarioComponent;
  let fixture: ComponentFixture<EntradaInventarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EntradaInventarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntradaInventarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
