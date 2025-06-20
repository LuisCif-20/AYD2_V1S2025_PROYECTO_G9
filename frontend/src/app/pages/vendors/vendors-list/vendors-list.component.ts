import { CommonModule } from '@angular/common';
import { Component, OnInit, signal, ViewChild } from '@angular/core';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { RatingModule } from 'primeng/rating';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { SelectModule } from 'primeng/select';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputNumberModule } from 'primeng/inputnumber';
import { DialogModule } from 'primeng/dialog';
import { TagModule } from 'primeng/tag';
import { InputIconModule } from 'primeng/inputicon';
import { IconFieldModule } from 'primeng/iconfield';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { FormVendorComponent } from '../form-vendor/form-vendor.component';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Product, Vendor } from '../../../models/models';
import { ProductService } from '../../../services/product/product.service';

interface Column {
    field: string;
    header: string;
    customExportHeader?: string;
}

interface ExportColumn {
    title: string;
    dataKey: string;
}

@Component({
    selector: 'app-vendors-list',
    standalone: true,
    imports: [
        CommonModule,
        TableModule,
        FormsModule,
        ButtonModule,
        RippleModule,
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
        FormVendorComponent
    ],
    providers: [MessageService, ProductService, ConfirmationService],
    templateUrl: './vendors-list.component.html',
    styleUrl: './vendors-list.component.scss'
})
export class VendorsListComponent implements OnInit {

    vendor!: Vendor;
    vendors = signal<Vendor[]>([]);
    selectedVendor!: Vendor[] | null;
    submitted: boolean = false;

    @ViewChild('dt') dt!: Table;
    cols!: Column[];

    constructor(
        private productService: ProductService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService
    ) {
    }

    ngOnInit() {
        this.loadDemoData();
    }

    loadDemoData() {
        //llamar api con todo los vendedores
        const vendorsss = new Vendor()
        vendorsss.code = "aasd11"
        vendorsss.name = "dani"
        vendorsss.commission = 0

        const vendorsss1 = new Vendor()
        vendorsss1.code = "3ert"
        vendorsss1.name = "kami"
        vendorsss1.commission = 0
        this.vendors.set([vendorsss, vendorsss1])

        this.cols = [
            { field: 'code', header: 'Code', customExportHeader: 'Code' },
            { field: 'name', header: 'Name' },
            { field: 'lastName', header: 'LastName' },
            { field: 'phone', header: 'Phone' },
            { field: 'direction', header: 'Direction' }
        ];
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }

    openNew() {
        this.submitted = false;
    }

    editProduct(vendor: Vendor) {
    }

    hideDialog() {
        this.submitted = false;
    }

    deleteProduct(product: Vendor) {
        this.confirmationService.confirm({
            message: 'Estas seguro en eliminar el vendedor ' + product.name + '?',
            header: 'Confirmación',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                //logica para eliminar
                this.messageService.add({
                    severity: 'success',
                    summary: 'Éxito',
                    detail: 'Vendedor eliminado',
                    life: 3000
                });
            }
        });
    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.vendors().length; i++) {
            if (this.vendors()[i].code === id) {
                index = i;
                break;
            }
        }

        return index;
    }


}
