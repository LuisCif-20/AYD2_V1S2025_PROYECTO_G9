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
import {SalesService} from "../../services/sales/sales.service";
import {DatePickerModule} from "primeng/datepicker";
import {Client, ItemProduct, Sale, SaleDetailForm, SaleForm, Salesman} from "../../models/models";
import {UtilsService} from "../../services/utils/utils.service";

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
        ConfirmDialogModule,
        DatePickerModule,
    ],
    providers: [ProductService, ConfirmationService, ClienteService]
})
export class Sales implements OnInit {
    sales = signal<Sale[]>([])
    @ViewChild("dt") dt: any
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
    clients: Client[] = []
    salesmen: Salesman[] = []
    products: ItemProduct[] = []

    paymentTypes = [
        { label: "Contado", value: "CONTADO" },
        { label: "Crédito", value: "CREDITO" },
    ]
    selectedClientNit: any = '';

    constructor(
        private utilsService: UtilsService,
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
        this.salesService.getSales().subscribe({
            next: (sales) => this.sales.set(sales),
            error: (err) => {
                const detalle = err?.error?.detail || 'Error al cargar las ventas.';
                this.utilsService.error(detalle);
            }
        });
    }


    loadClients() {
        this.salesService.getClients().subscribe({
            next: (clients) => this.clients = clients,
            error: (err) => {
                const detalle = err?.error?.detail || 'Error al cargar los clientes.';
                this.utilsService.error(detalle);
            }
        });
    }

    loadSalesmen() {
        this.salesService.getSalesmen().subscribe({
            next: (salesmen) => {
                this.salesmen = salesmen.map(s => ({
                    ...s,
                    fullName: `${s.firstName} ${s.lastName}`
                }));
            },
            error: (err) => {
                const detalle = err?.error?.detail || 'Error al cargar los vendedores.';
                this.utilsService.error(detalle);
            }
        });
    }

    loadProducts() {
        this.salesService.getProducts().subscribe({
            next: (products) => this.products = products,
            error: (err) => {
                const detalle = err?.error?.detail || 'Error al cargar los productos.';
                this.utilsService.error(detalle);
            }
        });
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
            message: "¿Está seguro de que desea anular esta venta?",
            header: "Confirmar",
            icon: "pi pi-times-circle",
            accept: () => {
                // Llamada al servicio para eliminar
                this.saving = true;
                this.salesService.deleteSale(sale.id).subscribe({
                    next: () => {
                        this.saving = false;
                        this.loadSales();
                        this.utilsService.success('Venta anulada');
                    },
                    error: (err) => {
                        this.saving = false;
                        const detalle = err?.error?.detail || 'Error al anular la venta.';
                        this.utilsService.error(detalle);
                    }
                });
            },
        })
    }


    saveSale() {
        this.submitted = true

        if (this.isValidSaleForm()) {
            this.saving = true

            if (this.isEditMode) {
                // Actualizar venta existente
                /*this.salesService.updateSale(this.saleForm).subscribe({
                   next: () => {
                     this.handleSaveSuccess("Venta actualizada")
                   },
                   error: () => {
                     this.saving = false
                   }
                })*/
            } else {
                this.salesService.createSale(this.saleForm).subscribe({
                    next: () => {
                        this.saving = false;
                        this.saleDialog = false;
                        this.loadSales();
                        this.utilsService.success('Venta creada');
                    },
                    error: (err) => {
                        this.saving = false;
                        const detalle = err?.error?.detail || 'Error al crear la venta.';
                        this.utilsService.error(detalle);
                    }
                });

            }
        }
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
            case "ANULADA":
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

    // Método para manejar cambio de cliente
    onClientChange(event: any) {
        const clientId = event.value
        const selectedClient = this.clients.find((c) => c.id === clientId)
        if (selectedClient) {
            this.selectedClientNit = selectedClient.nit
            // Opcionalmente, puedes llenar automáticamente el NIT de factura
            if (!this.saleForm.invoiceNit) {
                this.saleForm.invoiceNit = selectedClient.nit
            }
        } else {
            this.selectedClientNit = ""
        }
    }

    exportCSV() {
        // Usar el método nativo de PrimeNG si está disponible
        if (this.dt) {
            this.dt.exportCSV()
        }
    }

    printSale(sale: any) {
       const resp = this.salesService.printSale(sale);
       if (resp.status === 'success') this.utilsService.success(resp.message);
       else this.utilsService.error(resp.message);
    }
}
