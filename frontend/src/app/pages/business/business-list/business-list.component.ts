import { CommonModule } from '@angular/common';
import { Component, computed, inject, OnInit, ViewChild } from '@angular/core';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { InputTextModule } from 'primeng/inputtext';
import { DialogModule } from 'primeng/dialog';
import { TagModule } from 'primeng/tag';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';

import { BusinessFormComponent } from '../business-form/business-form.component';
import { BusinessService } from '../../../services/cliente/business.service';
import { Business } from '../../../models/business.inteface';

interface Column {
  field: string;
  header: string;
}

@Component({
  selector: 'app-business-list',
  standalone: true,
  providers: [MessageService, ConfirmationService],
  imports: [
    CommonModule,
    TableModule,
    FormsModule,
    ButtonModule,
    RippleModule,
    ToastModule,
    ToolbarModule,
    InputTextModule,
    DialogModule,
    ConfirmDialogModule,
    TagModule,
    BusinessFormComponent
  ],
  templateUrl: './business-list.component.html',
  styles: ``
})
export class BusinessListComponent implements OnInit {

  private businessService = inject(BusinessService);

  businesses = computed<Business[]>(() => this.businessService.business());
  selectedBusiness!: Business[] | null;

  @ViewChild('dt') dt!: Table;
  @ViewChild('formNegocio') formNegocio!: BusinessFormComponent;

  cols!: Column[];

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
  ) {}

  ngOnInit() {
    this.loadIniData();
  }

  loadIniData() {
    this.businessService.getAllBusiness().subscribe({
      error: (err) => {
        console.error('Error al cargar negocios:', err);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudo cargar la lista de negocios',
        });
      }
    });

    this.cols = [
      { field: 'name', header: 'Nombre' },
      { field: 'isActive', header: 'Activo' }
    ];
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

  deleteBusiness(business: Business) {
    this.confirmationService.confirm({
      message: `¿Estás seguro de inactivar el negocio "${business.name}"?`,
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        if (!business.id) {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Negocio inválido, no se puede inactivar',
          });
          return;
        }

        this.businessService.deleteBusiness(business.id).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Éxito',
              detail: 'Negocio eliminado correctamente',
              life: 3000
            });
            this.loadIniData();
          },
          error: (err) => {
            console.error('Error al inactivar negocio:', err);
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'No se pudo eliminar el negocio. Intenta nuevamente.',
            });
          }
        });
      }
    });
  }

  activateBusiness(business: Business) {
    if (!business.id) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Negocio inválido, no se puede activar',
      });
      return;
    }

    this.businessService.updateBusiness(business.id, { ...business, isActive: true }).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Éxito',
          detail: 'Negocio activado correctamente',
          life: 3000
        });
        this.loadIniData();
      },
      error: (err) => {
        console.error('Error al activar negocio:', err);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudo activar el negocio. Intenta nuevamente.',
        });
      }
    });
  }
}
