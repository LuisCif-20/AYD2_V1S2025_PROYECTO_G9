import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { Product } from '../../../models/models';
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

@Component({
  selector: 'app-form-vendor',
  standalone: true,
  imports: [
    DialogModule,             // ⬅ IMPORTANTE
    InputTextModule,
    InputNumberModule,
   // InputTextareaModule,
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
            CommonModule
  ],
  templateUrl: './form-vendor.component.html',
  styleUrl: './form-vendor.component.scss'
})
export class FormVendorComponent implements OnInit {

  formVendedor!: FormGroup;
  visible: boolean = false;
  product!: Product;
  statuses!: any[];
  submitted: boolean = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.formVendedor = this.fb.group({
      codigo: [{ value: 'VEN089', disabled: true }],
      nombres: ['', Validators.required],
      apellidos: ['', Validators.required],
      telefono: [''],
      comision: [0.0, [Validators.required, Validators.min(0)]],
      direccion: ['']
    });
  }

  mostrarFormulario(): void {
    this.visible = true;
  }

  guardar(): void {
    if (this.formVendedor.valid) {
      const vendedor = this.formVendedor.getRawValue();
      console.log('Vendedor guardado:', vendedor);
      this.visible = false;
    }
  }

  hideDialog() {
          this.visible = false;
      }


}
