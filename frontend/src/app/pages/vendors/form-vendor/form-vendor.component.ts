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
import {UtilsService} from "../../../services/utils/utils.service";


@Component({
  selector: 'app-form-vendor',
  standalone: true,
  providers: [VendorService],
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
    InputMaskModule
  ],
  templateUrl: './form-vendor.component.html',
  styleUrl: './form-vendor.component.scss'
})
export class FormVendorComponent implements OnInit {

  visible: boolean = false;

  vendedor!: Vendor;
  statuses!: any[];
  submitted: boolean = false;
  vendorCode!: string | undefined;
  @Output() cerrado = new EventEmitter<boolean>();

  constructor(
    private utilsService: UtilsService,
    private vendorService: VendorService,
  ) { }

  ngOnInit(): void {

  }

  mostrarFormulario(vendor: Vendor | undefined): void {
    console.log(vendor)
    if (vendor) {
      this.vendedor = vendor;
      this.vendorCode = vendor.code;
    } else {
      this.vendedor = new Vendor();
    }

    this.visible = true;
  }

  guardar(): void {
    const { firstName, lastName, commissionPercent } = this.vendedor;

    if ((firstName?.trim() ?? '') && (lastName?.trim() ?? '') && commissionPercent >= 0) {
      console.log('Vendedor guardado:', this.vendedor);
      this.vendorService.save(this.vendedor).subscribe({
        next: (data) => {
          this.visible = false;
          this.utilsService.success('Vendedor guardado correctamente');
        },
        error: (err) => {
          this.visible = false;
          console.error('Error al guardar vendedor:', err);
          const detalle = err?.error?.detail || 'Error al intentar registrar un vendedor, por favor, intente más tarde';
          this.utilsService.error(detalle);
        }
      });

    } else {
      this.utilsService.error('Campos incompletos, debes ingresar nombres, apellidos y una comisión es igual o mayor a 0%');
    }
  }

  hideDialog() {
    this.visible = false;
  }


}
