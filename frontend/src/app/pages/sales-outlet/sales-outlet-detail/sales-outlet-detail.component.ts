import { Component } from '@angular/core';
import { SalesDataDto, SalesOutlet } from '../../../models/models';
import { DialogModule } from 'primeng/dialog';
import { MessageService } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { ButtonModule } from 'primeng/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { TableModule } from 'primeng/table';
import { CalendarModule } from 'primeng/calendar';
import {SalesService} from "../../../services/sales/sales.service";

@Component({
  selector: 'app-sales-outlet-detail',
  standalone: true,
  providers: [MessageService, SalesService],
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
    CommonModule,
    TableModule,
    CalendarModule
  ],
  templateUrl: './sales-outlet-detail.component.html',
  styleUrl: './sales-outlet-detail.component.scss'
})
export class SalesOutletDetailComponent {

  venta!: SalesDataDto;
  visible: boolean = false;
  wareHouseExitDate!: Date | null;

  salesOutlet!: SalesOutlet;

  constructor(
    private salesOutletService: SalesService,
    private messageService: MessageService,
  ) { }

  viewDetail(salesData: SalesDataDto): void {
    console.log('detail')
    this.visible = true;
    this.venta = salesData;
    if (this.venta.warehouseExitDate) {
      this.wareHouseExitDate = new Date(this.venta.warehouseExitDate);
    } else {
      this.wareHouseExitDate = null;
    }
  }

  registerOutput(): void {
    console.log(this.venta)

    if (!this.wareHouseExitDate) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Escribe la fecha de salida de bodega para continuar',
      });
      return;
    }

    this.salesOutlet = new SalesOutlet();
    this.salesOutlet.saleId = this.venta.id;
    this.salesOutlet.exitDate = this.wareHouseExitDate;

    this.salesOutletService.registerSalesOutlet(this.salesOutlet)
      .subscribe(
        {
          next: (data: any) => {
            this.visible = false;
            this.messageService.add({
              severity: 'success',
              summary: 'Éxito',
              detail: 'Se registro la salida de productos a bodega correctamente',
            });
          },
          error: (err: any) => {
            console.error('Error en registro de salida:', err);
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: err.error.detail,
            });
          },
          complete: () => {
            console.log('Carga de proveedores completada');
          }
        }
      );

  }

}
