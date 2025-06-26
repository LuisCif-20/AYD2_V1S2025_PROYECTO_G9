import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { CommonModule } from '@angular/common';
import { MessageService } from 'primeng/api';
import { Business } from '../../../models/business.inteface';
import { BusinessService } from '../../../services/cliente/business.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-business-form',
  providers: [MessageService],
  imports: [
    DialogModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    ToastModule,
    CommonModule
  ],
  templateUrl: './business-form.component.html',
  styles: ``
})
export class BusinessFormComponent {

  visible: boolean = false;
  business: Business = { name: '' };

  @Output() cerrado = new EventEmitter<boolean>(); // opcional: para notificar solo el cierre

  constructor(
    private messageService: MessageService,
    private businessService: BusinessService
  ) {}

  mostrarFormulario(business?: Business): void {
    if (business) {
      this.business = { ...business };
    } else {
      this.business = { name: '' };
    }
    this.visible = true;
  }

  guardar(): void {
    if (!this.business.name || this.business.name.trim() === '') {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'El nombre del negocio es obligatorio.',
      });
      return;
    }
    if (this.business.id) {
      this.businessService.updateBusiness(this.business.id, this.business).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Éxito',
            detail: 'Negocio actualizado correctamente',
          });
          this.cerrarFormulario();
        },
        error: (err: HttpErrorResponse) => {
          console.error('Error al actualizar negocio:', err);
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: err.error.detail,
          });
        }
      });
    } else {
      this.businessService.createBusiness(this.business.name).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Éxito',
            detail: 'Negocio registrado correctamente',
          });
          this.cerrarFormulario();
        },
        error: (err) => {
          console.error('Error al registrar negocio:', err);
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: err.error.detail,
          });
        }
      });
    }
  }

  cerrarFormulario() {
    this.visible = false;
    this.cerrado.emit(true); // opcional: solo para que el padre sepa que el diálogo se cerró
  }
}
