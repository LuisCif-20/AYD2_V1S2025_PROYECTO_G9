import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { StockService } from '../../services/inventario/stock.service';
import { Column, StockProducto } from '../../models/models';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { MessageService, ConfirmationService } from 'primeng/api';
import { DialogModule } from 'primeng/dialog';



@Component({
  selector: 'app-stock',
  standalone: true,
  templateUrl: './stock.component.html',
  imports: [
  CommonModule,
  FormsModule,
  TableModule,
  InputTextModule,
  ButtonModule,
  ConfirmDialogModule,
  ToastModule,
  DialogModule,
  FormsModule

],
providers: [ConfirmationService, MessageService]

})
export class StockComponent implements OnInit {
  stockProductos: StockProducto[] = [];
  cols: Column[] = [];
  editarDialogVisible = false;
  productoSeleccionado!: StockProducto;
  nuevoUmbral: number | null = null;


  @ViewChild('dt') dt!: Table;

  constructor(
        private confirmationService: ConfirmationService,
        private messageService: MessageService,
        private stockService: StockService) {}

  ngOnInit() {
  this.cols = [
    { field: 'product.code', header: 'Código Producto' },
    { field: 'product.name', header: 'Nombre Producto' },
    { field: 'product.presentation.name', header: 'Presentación' },
    { field: 'totalQuantity', header: 'Cantidad Total' },
    { field: 'availableQuantity', header: 'Cantidad Disponible' },
    { field: 'reservedQuantity', header: 'Cantidad en Reserva' },
    { field: 'lowStockThreshold', header: 'Umbral de Alerta' }  // Nueva columna
  ];

  this.loadStockProductos();
}

confirmarActualizacion(prod: StockProducto) {
  this.confirmationService.confirm({
    message: `¿Desea actualizar el umbral de alerta del producto "${prod.product.name}"?`,
    header: 'Confirmar actualización',
    icon: 'pi pi-exclamation-triangle',
    accept: () => this.actualizarUmbral(prod)
  });
}

actualizarUmbral(prod: StockProducto) {
  this.stockService.updateLowStockThreshold(prod.id, prod.lowStockThreshold).subscribe({
    next: () => {
      this.messageService.add({
        severity: 'success',
        summary: 'Actualizado',
        detail: `Umbral actualizado correctamente para "${prod.product.name}"`,
        life: 3000
      });
    },
    error: () => {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: `No se pudo actualizar el umbral para "${prod.product.name}"`,
        life: 3000
      });
    }
  });
}

onThresholdChange(prod: StockProducto) {
  console.log("hola a todos");
  
  if (prod.lowStockThreshold >= 0) {
    this.stockService.updateLowStockThreshold(prod.id, prod.lowStockThreshold).subscribe({
      next: () => console.log('Umbral actualizado'),
      error: () => console.error('Error al actualizar umbral')
    });
  }
}


  loadStockProductos() {
    this.stockService.getInventario().subscribe({
      next: (data) => this.stockProductos = data,
      error: () => console.error('Error al cargar el inventario.')
    });
  }

  onGlobalFilter(event: Event) {
    const input = event.target as HTMLInputElement;
    this.dt.filterGlobal(input.value, 'contains');
  }


  abrirDialogEditar(prod: StockProducto) {
  this.productoSeleccionado = prod;
  this.nuevoUmbral = prod.lowStockThreshold;
  this.editarDialogVisible = true;
}

guardarNuevoUmbral() {
  if (this.nuevoUmbral == null || this.nuevoUmbral < 0) return;

  const payload = this.nuevoUmbral;

  this.stockService.updateLowStockThreshold(this.productoSeleccionado.id, payload).subscribe({
    next: () => {
      this.productoSeleccionado.lowStockThreshold = payload;
      this.messageService.add({
        severity: 'success',
        summary: 'Actualizado',
        detail: 'Umbral actualizado exitosamente',
        life: 3000
      });
      this.editarDialogVisible = false;
      //this.ngOnInit()
    },
    error: () => {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'No se pudo actualizar el umbral',
        life: 3000
      });
    }
  });
}

}
