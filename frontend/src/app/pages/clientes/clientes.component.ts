import {Component, OnInit, ViewChild} from "@angular/core";
import {ConfirmationService} from "primeng/api";
import {Table, TableModule} from "primeng/table";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import {DropdownModule} from "primeng/dropdown";
import {TextareaModule} from "primeng/textarea";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ClienteService} from "../../services/cliente/cliente.service";
import {UbicacionService} from "../../services/ubicacion/ubicacion.service";
import {Cliente, Departamento} from "../../models/models";
import {UtilsService} from "../../services/utils/utils.service";


interface Column {
    field: string;
    header: string;
}

@Component({
    selector: "app-clientes",
    standalone: true,
    templateUrl: "./clientes.component.html",
    styleUrls: ["./clientes.component.scss"],
    imports: [
        CommonModule,
        FormsModule,
        TableModule,
        ButtonModule,
        ToolbarModule,
        InputTextModule,
        DialogModule,
        DropdownModule,
        TextareaModule,
        ConfirmDialogModule,
        ToastModule,
    ],
    providers: [ConfirmationService],
})
export class ClientesComponent implements OnInit {
    clienteDialog: boolean = false;
    clientes: Cliente[] = [];
    cliente!: Cliente;
    selectedClientes!: Cliente[] | null;
    submitted: boolean = false;
    cols!: Column[];
    departamentos!: Departamento[];
    modoEdicion: boolean = false;


    municipios: any[] = [];
    municipiosPorDepartamento: { [key: string]: string[] } = {
        GT: ["Guatemala", "Mixco", "Villa Nueva"],
        QZ: ["Quetzaltenango", "Olintepeque"],
    };

    tiposVenta = [
        {label: "Contado", value: "CONTADO"},
        {label: "Crédito", value: "CREDITO"},
        {label: "Ambas", value: "AMBAS"},
    ];

    @ViewChild("dt") dt!: Table;

    constructor(
        private ubicacionService: UbicacionService,
        private clienteService: ClienteService,
        private confirmationService: ConfirmationService,
        private utilsService: UtilsService,
    ) {
    }

    ngOnInit() {
        this.cols = [
            {field: "code", header: "Código"},
            {field: "contactName", header: "Contacto"},
            {field: "businessName", header: "Negocio"},
            {field: "municipality.name", header: "Municipio"},
            {field: "phone", header: "Teléfono"},
            {field: "saleType", header: "Tipo Venta"}
        ];


        this.loadClientes();
        this.loadDepartamentos();
    }

    loadClientes() {
        this.clienteService.getClientes().subscribe({
            next: (data) => {
                this.clientes = data.map(cliente => {
                    if (!cliente.municipality?.name && cliente.municipalityCode && this.municipios.length > 0) {
                        const municipio = this.municipios.find(m => m.code === cliente.municipalityCode);
                        if (municipio) {
                            cliente.municipality = {...cliente.municipality, name: municipio.name};
                        }
                    }
                    return cliente;
                });
            },
            error: (err) => {
                const detalle = err?.error?.detail || 'No se pudieron cargar los clientes';
                this.utilsService.error(detalle);
            }
        });
    }


    loadDepartamentos() {
        this.ubicacionService.getDepartamentos().subscribe({
            next: (data) => this.departamentos = data,
            error: (err) => {
                const detalle = err?.error?.detail || 'No se pudieron cargar los departamentos';
                this.utilsService.error(detalle);
            }
        });
    }

    onDepartamentoSeleccionado(code: string) {
        this.ubicacionService.getMunicipiosByDepartamento(code).subscribe({
            next: (data) => this.municipios = data,
            error: (err) => {
                const detalle = err?.error?.detail || 'No se pudieron cargar los municipios';
                this.utilsService.error(detalle);
            }
        });
    }


    onGlobalFilter(event: Event) {
        const input = event.target as HTMLInputElement;
        this.dt.filterGlobal(input.value, "contains");
    }

    loadMunicipios() {
        const dep = this.cliente.municipalityCode?.substring(0, 2);
        this.municipios =
            this.municipiosPorDepartamento[dep!]?.map((m) => ({
                label: m,
                value: m,
            })) || [];
    }

    openNew() {
        this.cliente = {
            contactName: "",
            businessName: "",
            municipalityCode: "",
            municipality: {name: ""},
            address: "",
            nit: "",
            warehouseManager: "",
            phone: "",
            saleType: undefined,
            notes: "",
        };
        this.modoEdicion = false;
        this.submitted = false;
        this.clienteDialog = true;
    }


    hideDialog() {
        this.clienteDialog = false;
        this.submitted = false;
    }

    saveCliente() {
        this.submitted = true;
        console.log(this.cliente)
        if (
            !this.cliente.contactName ||
            !this.cliente.municipalityCode ||
            !this.cliente.saleType
        )
            return;

        const esEdicion = !!this.cliente.id;
        const request$ = esEdicion
            ? this.clienteService.updateCliente(this.cliente)
            : this.clienteService.createCliente(this.cliente);
        console.log(this.cliente)
        request$.subscribe({
            next: () => {
                this.utilsService.success(esEdicion ? "Cliente actualizado" : "Cliente creado")
                this.loadClientes();
            },
            error: (err) => {
                const detalle = err?.error?.detail || 'No se pudo guardar el cliente';
                this.utilsService.error(detalle);
            }
        });

        this.clienteDialog = false;
        this.cliente = {};
    }

    editCliente(cliente: Cliente) {
        this.cliente = {...cliente};

        if (!this.cliente.municipalityCode && this.cliente.municipality?.code) {
            this.cliente.municipalityCode = String(this.cliente.municipality.code);
        }

        this.modoEdicion = true;
        this.loadMunicipios();
        this.clienteDialog = true;
    }


    deleteCliente(cliente: Cliente) {
        this.confirmationService.confirm({
            message:
                "¿Está seguro que desea eliminar a " + cliente.contactName + "?",
            header: "Confirmar",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                this.clienteService.deleteCliente(cliente.id!.toString()).subscribe({
                    next: () => {
                        this.utilsService.success("Cliente eliminado")
                        this.loadClientes();
                    },
                    error: (err) => {
                        const detalle = err?.error?.detail || 'No se pudo eliminar el cliente';
                        this.utilsService.error(detalle);
                    }
                });
            },
        });
    }

    deleteSelectedClientes() {
        if (!this.selectedClientes || !this.selectedClientes.length) return;

        this.confirmationService.confirm({
            message: "¿Está seguro que desea eliminar los clientes seleccionados?",
            header: "Confirmar",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                const eliminaciones = this.selectedClientes!.map((cliente) =>
                    this.clienteService.deleteCliente(cliente.id!.toString())
                );

                Promise.all(eliminaciones.map((obs) => obs.toPromise()))
                    .then(() => {
                        this.utilsService.success("Clientes eliminados");
                        this.selectedClientes = null;
                        this.loadClientes();
                    }).catch((err) => {
                    const detalle = err?.error?.detail || "No se pudieron eliminar uno o mas clientes";
                    this.utilsService.error(detalle);
                });
            },
        });
    }

    generateCodigo(): string {
        const depCode = this.cliente.municipalityCode?.substring(0, 2) ?? "XX";
        const correlativo = String(this.clientes.length + 1).padStart(2, "0");
        return `${depCode}${correlativo}`;
    }
}
