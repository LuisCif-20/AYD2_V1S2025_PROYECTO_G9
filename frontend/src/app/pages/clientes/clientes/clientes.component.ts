import { Component, OnInit, ViewChild } from "@angular/core";
import { ConfirmationService, MessageService } from "primeng/api";
import { Table, TableModule } from "primeng/table";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { ToastModule } from "primeng/toast";
import { ToolbarModule } from "primeng/toolbar";
import { InputTextModule } from "primeng/inputtext";
import { DialogModule } from "primeng/dialog";
import { DropdownModule } from "primeng/dropdown";
import { TextareaModule } from "primeng/textarea";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ClienteService } from "../../../services/cliente/cliente.service";
import { UbicacionService } from "../../../services/ubicacion/ubicacion.service";
import { Cliente, Departamento } from "../../../models/models";



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
  providers: [MessageService, ConfirmationService],
})
export class ClientesComponent implements OnInit {
  clienteDialog: boolean = false;
  clientes: Cliente[] = [];
  cliente!: Cliente;
  selectedClientes!: Cliente[] | null;
  submitted: boolean = false;
  cols!: Column[];
  departamentos!: Departamento[];

  

  municipios: any[] = [];
  municipiosPorDepartamento: { [key: string]: string[] } = {
    GT: ["Guatemala", "Mixco", "Villa Nueva"],
    QZ: ["Quetzaltenango", "Olintepeque"],
  };

  tiposVenta = [
    { label: "Contado", value: "CONTADO" },
    { label: "Crédito", value: "CREDITO" },
    { label: "Ambas", value: "AMBAS" },
  ];

  @ViewChild("dt") dt!: Table;

  constructor(
    private ubicacionService: UbicacionService,
    private clienteService: ClienteService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit() {
    this.cols = [
  { field: "code", header: "Código" },
  { field: "contactName", header: "Contacto" },
  { field: "businessName", header: "Negocio" },
  { field: "municipality.name", header: "Municipio" },  
  { field: "phone", header: "Teléfono" },
  { field: "saleType", header: "Tipo Venta" }
];


    this.loadClientes();
    this.loadDepartamentos();
  }

  loadClientes() {
  this.clienteService.getClientes().subscribe({
    next: (data) => {
      this.clientes = data.map(cliente => {
        // Si municipality.name está vacío pero municipalityCode está definido
        if (!cliente.municipality?.name && cliente.municipalityCode && this.municipios.length > 0) {
          const municipio = this.municipios.find(m => m.code === cliente.municipalityCode);
          if (municipio) {
            cliente.municipality = { ...cliente.municipality, name: municipio.name };
          }
        }
        return cliente;
      });
    },
    error: () =>
      this.messageService.add({
        severity: "error",
        summary: "Error",
        detail: "No se pudieron cargar los clientes",
      }),
  });
}


  loadDepartamentos() {
    this.ubicacionService.getDepartamentos().subscribe({
        next: (data) => this.departamentos = data,
        error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No se pudieron cargar los departamentos' })
    });
}

onDepartamentoSeleccionado(code: string) {
    this.ubicacionService.getMunicipiosByDepartamento(code).subscribe({
        next: (data) => this.municipios = data,
        error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No se pudieron cargar los municipios' })
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
      municipality: {
        
        name: ""
      },
      address: "",
      nit: "",
      warehouseManager: "",
      phone: "",
      saleType: undefined,
      notes: "",
    };

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
        this.messageService.add({
          severity: "success",
          summary: "Éxito",
          detail: esEdicion ? "Cliente actualizado" : "Cliente creado",
          life: 3000,
        });
        this.loadClientes();
      },
      error: () =>
        this.messageService.add({
          severity: "error",
          summary: "Error",
          detail: "No se pudo guardar el cliente",
          life: 3000,
        }),
    });

    this.clienteDialog = false;
    this.cliente = {};
  }

  editCliente(cliente: Cliente) {
    this.cliente = { ...cliente };
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
            this.messageService.add({
              severity: "success",
              summary: "Éxito",
              detail: "Cliente eliminado",
              life: 3000,
            });
            this.loadClientes();
          },
          error: () =>
            this.messageService.add({
              severity: "error",
              summary: "Error",
              detail: "No se pudo eliminar el cliente",
              life: 3000,
            }),
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
            this.messageService.add({
              severity: "success",
              summary: "Éxito",
              detail: "Clientes eliminados",
              life: 3000,
            });
            this.selectedClientes = null;
            this.loadClientes();
          })
          .catch(() => {
            this.messageService.add({
              severity: "error",
              summary: "Error",
              detail: "Error al eliminar uno o más clientes",
              life: 3000,
            });
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
