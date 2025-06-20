import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { StockService } from '../../../services/inventario/stock.service';
import { Column, StockProducto } from '../../../models/models';


@Component({
  selector: 'app-stock',
  standalone: true,
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.scss'],
  imports: [
    CommonModule,
    FormsModule,
    TableModule,
    InputTextModule
  ]
})
export class StockComponent implements OnInit {
  stockProductos: StockProducto[] = [];
  cols: Column[] = [];

  @ViewChild('dt') dt!: Table;

  constructor(private stockService: StockService) {}

  ngOnInit() {
    this.cols = [
      { field: 'product.code', header: 'Código Producto' },
      { field: 'product.name', header: 'Nombre Producto' },
      { field: 'product.presentation.name', header: 'Presentación' },
      { field: 'totalQuantity', header: 'Cantidad Total' },
      { field: 'availableQuantity', header: 'Cantidad Disponible' },
      { field: 'reservedQuantity', header: 'Cantidad en Reserva' }
    ];

    this.loadStockProductos();
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
}
