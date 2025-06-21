import {Component, OnInit, signal, ViewChild} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {Table, TableModule} from 'primeng/table';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {RippleModule} from 'primeng/ripple';
import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {RatingModule} from 'primeng/rating';
import {InputTextModule} from 'primeng/inputtext';
import {TextareaModule} from 'primeng/textarea';
import {SelectModule} from 'primeng/select';
import {RadioButtonModule} from 'primeng/radiobutton';
import {InputNumberModule} from 'primeng/inputnumber';
import {DialogModule} from 'primeng/dialog';
import {TagModule} from 'primeng/tag';
import {InputIconModule} from 'primeng/inputicon';
import {IconFieldModule} from 'primeng/iconfield';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ProductService} from "../../services/product/product.service";
import {ClienteService} from "../../services/cliente/cliente.service";
import {Cliente} from "../clientes/clientes/clientes.component";
import {SalesService} from "../../services/sales/sales.service";

export interface Sale {
    id: string
    saleDate: string
    warehouseExitDate: string
    client: {
        id: number
        code: string
        contactName: string
        businessName: string
        municipality: {
            code: string
            name: string
            department: {
                code: string
                name: string
            }
        }
        address: string
        nit: string
        warehouseManager: string
        phone: string
        saleType: string
        notes: string
        active: boolean
    }
    shipmentNumber: string
    paymentType: string
    creditDays: number
    salesman: {
        code: string
        firstName: string
        lastName: string
        phone: string
        address: string
        commissionPercent: number
    }
    dteInvoiceNumber: number
    invoiceName: string
    invoiceNit: string
    total: number
    paymentStatus: string
    saleStatus: string
    paymentDate: string
    notes: string
    details: SaleDetail[]
}

// Interfaz actualizada para los detalles (GET)
export interface SaleDetail {
    productName: string
    quantity: number
    unitQuantity: number
    pricePerPresentation: number
    subtotal: number
}

// Interfaz para crear/editar ventas (POST)
export interface SaleForm {
    saleDate: string
    clientId: number | null
    paymentType: string
    creditDays: number
    salesmanCode: string
    dteInvoiceNumber: number
    invoiceName: string
    invoiceNit: string
    notes: string
    details: SaleDetailForm[]
}

export interface SaleDetailForm {
    productCode: string
    quantity: number
    unitQuantity: number
    pricePerPresentation: number
}

export interface Client {
    id: number
    code: string
    contactName: string
    businessName: string
    nit: string
    phone: string
    address: string
}

export interface Salesman {
    code: string
    firstName: string
    lastName: string
    fullName: string
    phone: string
    address: string
    commissionPercent: number
}

export interface ItemProduct {
    code: string
    name: string
    presentation: {
        id: number
        name: string
    }
    unitsPerPresentation: number
    pricePerPresentation: number
}

@Component({
    selector: 'app-sales',
    standalone: true,
    templateUrl: 'sales.html',
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
        ConfirmDialogModule
    ],
    providers: [MessageService, ProductService, ConfirmationService, ClienteService]
})
export class Sales implements OnInit {
    sales = signal<Sale[]>([])

    saleDialog = false
    viewSaleDialog = false
    isEditMode = false
    submitted = false
    saving = false

    saleForm: SaleForm = this.getEmptySaleForm()
    selectedSales: Sale[] = []
    selectedSaleForView: Sale | null = null

    cols: any[] = []

    // Datos para los selects
    clients: Cliente[] = []
    salesmen: Salesman[] = []
    products: ItemProduct[] = []

    paymentTypes = [
        { label: "Contado", value: "CONTADO" },
        { label: "Crédito", value: "CREDITO" },
    ]

    constructor(
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private clientService: ClienteService,
        private salesService: SalesService
    ) {}

    ngOnInit() {
        this.loadSales()
        this.loadClients()
        this.loadSalesmen()
        this.loadProducts()

        this.cols = [
            { field: "saleDate", header: "Fecha Venta" },
            { field: "client.businessName", header: "Cliente" },
            { field: "salesman.firstName", header: "Vendedor" },
            { field: "total", header: "Total" },
            { field: "paymentStatus", header: "Estado Pago" },
            { field: "saleStatus", header: "Estado Venta" },
        ]
    }

    loadSales() {
        // Llamada al servicio para obtener ventas
        this.salesService.getSales().subscribe(sales => this.sales.set(sales))
    }

    loadClients() {
        // Llamada al servicio para obtener clientes
        this.clientService.getClientes().subscribe(clients => this.clients = clients)
    }

    loadSalesmen() {
        // Llamada al servicio para obtener vendedores
        // this.salesmanService.getSalesmen().subscribe(salesmen => {
        //   this.salesmen = salesmen.map(s => ({
        //     ...s,
        //     fullName: `${s.firstName} ${s.lastName}`
        //   }))
        // })
    }

    loadProducts() {
        // Llamada al servicio para obtener productos
        // this.productService.getProducts().subscribe(products => this.products = products)
    }

    openNew() {
        this.saleForm = this.getEmptySaleForm()
        this.isEditMode = false
        this.submitted = false
        this.saleDialog = true
    }

    editSale(sale: Sale) {
        // Convertir de Sale a SaleForm
        this.saleForm = {
            saleDate: sale.saleDate,
            clientId: sale.client.id,
            paymentType: sale.paymentType,
            creditDays: sale.creditDays,
            salesmanCode: sale.salesman.code,
            dteInvoiceNumber: sale.dteInvoiceNumber,
            invoiceName: sale.invoiceName,
            invoiceNit: sale.invoiceNit,
            notes: sale.notes,
            details: sale.details.map((detail) => ({
                productCode: this.getProductCodeByName(detail.productName), // Necesitarás implementar esta función
                quantity: detail.quantity,
                unitQuantity: detail.unitQuantity,
                pricePerPresentation: detail.pricePerPresentation,
            })),
        }
        this.isEditMode = true
        this.saleDialog = true
    }

    viewSale(sale: Sale) {
        this.selectedSaleForView = sale
        this.viewSaleDialog = true
    }

    editFromView() {
        if (this.selectedSaleForView) {
            this.hideViewDialog()
            this.editSale(this.selectedSaleForView)
        }
    }

    hideViewDialog() {
        this.viewSaleDialog = false
        this.selectedSaleForView = null
    }

    deleteSale(sale: Sale) {
        this.confirmationService.confirm({
            message: "¿Está seguro de que desea eliminar esta venta?",
            header: "Confirmar",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                // Llamada al servicio para eliminar
                // this.salesService.deleteSale(sale.id).subscribe(() => {
                //   this.loadSales()
                //   this.messageService.add({
                //     severity: "success",
                //     summary: "Exitoso",
                //     detail: "Venta eliminada",
                //     life: 3000,
                //   })
                // })
            },
        })
    }

    deleteSelectedSales() {
        this.confirmationService.confirm({
            message: "¿Está seguro de que desea eliminar las ventas seleccionadas?",
            header: "Confirmar",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                // Implementar eliminación múltiple
            },
        })
    }

    saveSale() {
        this.submitted = true

        if (this.isValidSaleForm()) {
            this.saving = true

            if (this.isEditMode) {
                // Actualizar venta existente
                // this.salesService.updateSale(this.saleForm).subscribe({
                //   next: () => {
                //     this.handleSaveSuccess("Venta actualizada")
                //   },
                //   error: () => {
                //     this.saving = false
                //   }
                // })
            } else {
                // Crear nueva venta
                // this.salesService.createSale(this.saleForm).subscribe({
                //   next: () => {
                //     this.handleSaveSuccess("Venta creada")
                //   },
                //   error: () => {
                //     this.saving = false
                //   }
                // })
            }

            // Simulación para el ejemplo
            setTimeout(() => {
                this.handleSaveSuccess(this.isEditMode ? "Venta actualizada" : "Venta creada")
            }, 1000)
        }
    }

    private handleSaveSuccess(message: string) {
        this.saving = false
        this.saleDialog = false
        this.loadSales()
        this.messageService.add({
            severity: "success",
            summary: "Exitoso",
            detail: message,
            life: 3000,
        })
    }

    private isValidSaleForm(): boolean {
        return !!(
            this.saleForm.saleDate &&
            this.saleForm.clientId &&
            this.saleForm.paymentType &&
            this.saleForm.salesmanCode &&
            this.saleForm.details &&
            this.saleForm.details.length > 0 &&
            this.saleForm.details.every(
                (detail) => detail.productCode && detail.quantity > 0 && detail.pricePerPresentation > 0,
            )
        )
    }

    hideDialog() {
        this.saleDialog = false
        this.submitted = false
        this.saving = false
    }

    addProductDetail() {
        this.saleForm.details.push({
            productCode: "",
            quantity: 1,
            unitQuantity: 1,
            pricePerPresentation: 0,
        })
    }

    removeProductDetail(index: number) {
        this.saleForm.details.splice(index, 1)
    }

    onProductChange(index: number, productCode: string) {
        const product = this.products.find((p) => p.code === productCode)
        if (product) {
            this.saleForm.details[index].pricePerPresentation = product.pricePerPresentation
            this.calculateSubtotal(index)
        }
    }

    calculateSubtotal(index: number) {
        const detail = this.saleForm.details[index]
        // El subtotal se calculará automáticamente en el getter
    }

    getDetailSubtotal(detail: SaleDetailForm): number {
        return detail.quantity * detail.pricePerPresentation
    }

    getTotalAmount(): number {
        return this.saleForm.details.reduce((total, detail) => total + this.getDetailSubtotal(detail), 0)
    }

    onGlobalFilter(table: any, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, "contains")
    }

    exportCSV() {
        // Implementar exportación CSV
    }

    getPaymentStatusSeverity(status: string): string {
        switch (status) {
            case "PAGADO":
                return "success"
            case "PENDIENTE":
                return "warning"
            case "VENCIDO":
                return "danger"
            default:
                return "info"
        }
    }

    getSaleStatusSeverity(status: string): string {
        switch (status) {
            case "VIGENTE":
                return "success"
            case "COMPLETADA":
                return "info"
            case "CANCELADA":
                return "danger"
            default:
                return "warning"
        }
    }

    getPaymentTypeSeverity(type: string): string {
        switch (type) {
            case "CONTADO":
                return "success"
            case "CREDITO":
                return "warning"
            default:
                return "info"
        }
    }

    private getProductCodeByName(productName: string): string {
        // Función auxiliar para obtener el código del producto por nombre
        // Necesaria para la conversión de Sale a SaleForm
        const product = this.products.find((p) => p.name === productName)
        return product ? product.code : ""
    }

    private getEmptySaleForm(): SaleForm {
        return {
            saleDate: new Date().toISOString().split("T")[0],
            clientId: null,
            paymentType: "",
            creditDays: 0,
            salesmanCode: "",
            dteInvoiceNumber: 0,
            invoiceName: "",
            invoiceNit: "",
            notes: "",
            details: [],
        }
    }
}
