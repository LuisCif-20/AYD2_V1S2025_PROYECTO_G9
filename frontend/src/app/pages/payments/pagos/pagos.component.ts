import { Component, OnInit, ViewChild } from '@angular/core';
//import { VentaService } from 'src/app/services/venta/venta.service';
import { MessageService } from 'primeng/api';
import { Banco, Pago, Venta } from '../../../models/models';
import { PaymentService } from '../../../services/payments/payment.service';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { FormsModule } from '@angular/forms';

import { Table } from 'primeng/table';
import {DatePickerModule} from "primeng/datepicker";



@Component({
  selector: 'app-pagos',
  standalone: true,
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss'],
  imports: [
    CommonModule,
    TableModule,
    DialogModule,
    DropdownModule,
    InputTextModule,
    InputNumberModule,
    ButtonModule,
    ToastModule,
    FormsModule,
    DatePickerModule
  ],
  providers: [MessageService]
})


export class PagosComponent implements OnInit {
  bancos: Banco[] = [];
  ventas: any[] = [];
  selectedVenta: any;
  pagoDialog = false;
  submitted = false;

  pago: Pago = {
    saleId: '',
    bankId: '',
    accountNumber: '',
    transactionNumber: '',
    amount: 0,
  };

  @ViewChild('dt') dt!: Table;

  constructor(
    private paymentService: PaymentService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.obtenerBancos();
    this.obtenerVentas();
    console.log("ventas");
    
    console.log(this.ventas);
  }

  obtenerBancos() {
    this.paymentService.obtenerBancos().subscribe({
      next: (data) => (this.bancos = data as Banco[]),
      
      
      error: () =>
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudieron cargar los bancos',
        }),

    });
    console.log(this.bancos);
    
  }

  obtenerVentas() {
    this.paymentService.obtenerVentasPendientes().subscribe({
      next: (data) => (this.ventas = data as Venta[]),
      error: () =>
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudieron cargar las ventas',
        }),
    });
    
    
  }

  abrirPago(venta: any) {
  this.selectedVenta = venta;

  const today = new Date();
  const paymentDate = today.toISOString().split('T')[0];

  this.pago = {
    saleId: venta.id,
    bankId: '',
    //paymentDate: "",
    accountNumber: '',
    transactionNumber: '',
    amount: venta.remainingBalance,
    paymentDate: paymentDate
  };

  this.pagoDialog = true;
}


  registrarPago() {
  this.submitted = true;

  if (
    !this.pago.saleId ||
    !this.pago.bankId ||
    !this.pago.accountNumber ||
    !this.pago.transactionNumber ||
    !this.pago.amount ||
    !this.pago.paymentDate
  ) {
    return;
  }

  this.paymentService.registrarPago(this.pago).subscribe({
    next: () => {
      this.messageService.add({
        severity: 'success',
        summary: 'Pago registrado',
        detail: 'El pago se ha guardado exitosamente',
      });
      this.pagoDialog = false;
      this.obtenerVentas();
    },
    error: (err) => {
      const detalle = err?.error?.detail || 'Error desconocido al registrar el pago';
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: detalle,
      });
    },
  });
}


  onGlobalFilter(event: Event) {
  const input = event.target as HTMLInputElement;
  this.dt.filterGlobal(input.value, 'contains');
}

}
