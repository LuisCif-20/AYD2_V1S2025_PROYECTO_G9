import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {InputTextModule} from 'primeng/inputtext';
import {InputNumberModule} from 'primeng/inputnumber';
import {CalendarModule} from 'primeng/calendar';
import {TextareaModule} from 'primeng/textarea';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import {ProductEntryForm, Producto} from '../../models/models';
import {InventarioService} from '../../services/inventario/inventario.service';
import {UtilsService} from "../../services/utils/utils.service";


@Component({
    selector: 'app-entrada-inventario',
    standalone: true,
    templateUrl: './entrada-inventario.component.html',
    imports: [
        CommonModule,
        FormsModule,
        TableModule,
        DialogModule,
        InputTextModule,
        InputNumberModule,
        CalendarModule,
        TextareaModule,
        ButtonModule,
        ToastModule
    ],
    providers: [MessageService]
})
export class EntradaInventarioComponent implements OnInit {
    productos: Producto[] = [];
    selectedProduct!: Producto;
    showDialog = false;
    submitted = false;

    entry: ProductEntryForm = {};

    constructor(
        private inventarioService: InventarioService,
        private utilsService: UtilsService) {
    }

    ngOnInit(): void {
        this.loadProductos();
    }

    loadProductos() {
        this.inventarioService.getProductos().subscribe({
            next: (data) => this.productos = data,
            error: (err) => {
                const detalle = err?.error?.detail || 'No se pudo cargar los productos';
                this.utilsService.error(detalle);
            },
        });
    }


    openIngreso(product: Producto) {
        this.selectedProduct = product;
        this.entry = {
            productCode: product.code,
            entryDate: new Date().toISOString().split('T')[0]
        };
        this.submitted = false;
        this.showDialog = true;
    }

    get totalUnidades(): number {
        return (this.entry.quantityPresentation || 0) * (this.selectedProduct?.unitsPerPresentation || 0);
    }


    saveIngreso() {
        this.submitted = true;

        const {
            productCode, entryDate, quantityPresentation,
            containerNumber, ducaNumber, ducaDate,
            rectifiedDucaNumber, rectifiedDucaDate, notes
        } = this.entry;

        const isValid = productCode && entryDate && quantityPresentation && containerNumber && ducaNumber && ducaDate;
        if (!isValid) return;

        const dto: ProductEntryForm = {
            productCode,
            entryDate,
            quantityPresentation,
            unitsPerPresentation: this.selectedProduct.unitsPerPresentation,
            containerNumber,
            ducaNumber,
            ducaDate,
            rectifiedDucaNumber,
            rectifiedDucaDate,
            notes
        };

        this.inventarioService.guardarEntrada(dto).subscribe({
            next: () => {
                this.utilsService.success('Entrada registrada');
                this.showDialog = false;
                this.entry = {};
                this.submitted = false;
            },
            error: (err) => {
                const detalle = err?.error?.detail || 'No se pudo registrar la entrada';
                this.utilsService.error(detalle);
            }
        });
    }


}
