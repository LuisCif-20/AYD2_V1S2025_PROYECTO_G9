import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { Vendor } from '../../../models/models';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { RatingModule } from 'primeng/rating';
import { TextareaModule } from 'primeng/textarea';
import { SelectModule } from 'primeng/select';
import { RadioButtonModule } from 'primeng/radiobutton';
import { TagModule } from 'primeng/tag';
import { InputIconModule } from 'primeng/inputicon';
import { IconFieldModule } from 'primeng/iconfield';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { RippleModule } from 'primeng/ripple';
import { CommonModule } from '@angular/common';
import { InputMaskModule } from 'primeng/inputmask';
import { MessageService } from 'primeng/api';
import { VendorService } from '../../../services/vendor/vendor.service';
import { ProductService } from '../../../services/products/product.service';
import { Dropdown, DropdownModule } from 'primeng/dropdown';
import { Presentation, Product } from '../../../models/product.interface';

@Component({
  selector: 'app-product-form',
  providers: [MessageService, VendorService],
  imports: [
    DialogModule,
    InputTextModule,
    InputNumberModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    FormsModule,
    ButtonModule,
    ToastModule,
    ToolbarModule,
    RatingModule,
    InputTextModule,
    TextareaModule,
    SelectModule,
    RadioButtonModule,
    InputNumberModule,
    DialogModule,
    TagModule,
    InputIconModule,
    IconFieldModule,
    ConfirmDialogModule,
    RippleModule,
    CommonModule,
    InputMaskModule,
    DropdownModule
  ],
  templateUrl: './product-form.component.html',
  styles: ``
})
export class ProductFormComponent implements OnInit {

  visible: boolean = false;

  product!: Product;
  statuses!: any[];
  submitted: boolean = false;
  productCode!: string | undefined;
  presentaciones!: Presentation[];
  @Output() cerrado = new EventEmitter<boolean>();

  constructor(
    private messageService: MessageService,
    private productService: ProductService,
  ) { }

  ngOnInit(): void {
    this.productService.getAllPresentations().subscribe((presentations) => {
      this.presentaciones = presentations;
    });
  }

  mostrarFormulario(product: Product | undefined): void {
    if (product) {
      this.product = product;
      this.productCode = product.code;
    } else {
      this.product = new Product();
    }
    this.visible = true;
  }

  guardar(): void {
  const { code, name, presentation, unitsPerPresentation, pricePerPresentation } = this.product;

  const camposValidos =
    (code?.trim() ?? '') &&
    (name?.trim() ?? '') &&
    presentation?.id != null &&
    unitsPerPresentation > 0 &&
    pricePerPresentation >= 0;

  if (camposValidos) {
    const productoDTO = {
      code,
      name,
      presentationId: presentation.id,
      unitsPerPresentation,
      pricePerPresentation,
    };

    this.productService.saveProduct(productoDTO).subscribe({
      next: () => {
        this.visible = false;
        this.cerrado.emit(true);
        this.messageService.add({
          severity: 'success',
          summary: 'Éxito',
          detail: 'Producto guardado correctamente',
        });
      },
      error: (err) => {
        console.error('Error al guardar producto:', err);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Error al intentar registrar el producto. Intente más tarde.',
        });
      },
    });
  } else {
    this.messageService.add({
      severity: 'error',
      summary: 'Campos incompletos',
      detail: 'Debes ingresar un código, nombre, presentación, unidades y precio válidos.',
    });
  }
}


  hideDialog() {
    this.visible = false;
  }

}
