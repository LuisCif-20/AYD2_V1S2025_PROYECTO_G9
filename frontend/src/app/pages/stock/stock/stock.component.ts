import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';

interface StockProducto {
    codigo: string;
    nombre: string;
    cantidadReserva: number;
    cantidadStock: number;
}

interface Column {
    field: string;
    header: string;
}

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

    ngOnInit() {
        this.cols = [
            { field: 'codigo', header: 'Código Producto' },
            { field: 'nombre', header: 'Nombre Producto' },
            { field: 'cantidadReserva', header: 'Cantidad en Reserva' },
            { field: 'cantidadStock', header: 'Cantidad en Stock' }
        ];

        this.loadStockProductos();
    }

    loadStockProductos() {
        this.stockProductos = [
            { codigo: 'P001', nombre: 'Producto A', cantidadReserva: 12, cantidadStock: 40 },
            { codigo: 'P002', nombre: 'Producto B', cantidadReserva: 0, cantidadStock: 85 },
            { codigo: 'P003', nombre: 'Producto C', cantidadReserva: 20, cantidadStock: 5 }
        ];
    }

    onGlobalFilter(event: Event) {
        const input = event.target as HTMLInputElement;
        this.dt.filterGlobal(input.value, 'contains');
    }
}
