import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import {Vendor, Venta} from '../../../models/models';
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
import {UtilsService} from "../../../services/utils/utils.service";

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
  templateUrl: 'product-form.component.html'
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
    private utilsService: UtilsService,
    private productService: ProductService,
  ) { }

  ngOnInit(): void {

    this.productService.getAllPresentations().subscribe({
      next: (presentations) => (this.presentaciones = presentations),
      error: (err) => {
        const detalle = err?.error?.detail || 'No se pudieron cargar las presentaciones.'
        this.utilsService.error(detalle);
      }
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

    if (!this.validarCodigo(this.product.code)) {
      this.utilsService.error('Código inválido. Debe tener 4 letras y 4 números.');
      return;
    }

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
          this.utilsService.success('Producto guardado correctamente');
        },
        error: (err) => {
          console.error('Error al guardar producto:', err);
          const detalle = err?.error?.detail || 'Error al intentar registrar el producto. Intente más tarde.'
          this.utilsService.error(detalle);
        },
      });
    } else {
      this.utilsService.error('Campos incompletos, debes ingresar un código, nombre, presentación, unidades y precio válidos.');
    }
  }


  hideDialog() {
    this.visible = false;
  }

  validarCodigo(codigo: string): boolean {
    const regex = /^[A-Za-z]{4}[0-9]{4}$/;
    return regex.test(codigo);
  }

}
