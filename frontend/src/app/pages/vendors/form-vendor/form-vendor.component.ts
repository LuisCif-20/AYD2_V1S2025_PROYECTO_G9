import { Component, OnInit } from '@angular/core';
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


@Component({
  selector: 'app-form-vendor',
  standalone: true,
  providers: [MessageService],
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

  constructor(
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    
  }

  mostrarFormulario(vendor: Vendor | undefined): void {
    console.log(vendor)
    if(vendor){
      this.vendedor = vendor;
      this.vendorCode = vendor.code;
    } else {
      this.vendedor = new Vendor();
    }
    
    this.visible = true;
  }

  guardar(): void {
    const { name, lastName, commission } = this.vendedor;

    if ((name?.trim() ?? '') && (lastName?.trim() ?? '') && commission >= 0) {
      console.log('Vendedor guardado:', this.vendedor);
      this.visible = false;

      this.messageService.add({
        severity: 'success',
        summary: 'Éxito',
        detail: 'Vendedor guardado correctamente',
      });

    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Campos incompletos',
        detail: 'Debes ingresar nombres, apellidos y una comisión es igual o mayor a 0%',
      });
    }
  }

  hideDialog() {
    this.visible = false;
  }


}
