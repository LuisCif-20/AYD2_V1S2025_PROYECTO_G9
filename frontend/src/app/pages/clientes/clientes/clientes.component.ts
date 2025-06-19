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
import {Product, ProductService} from '../../../services/product/product.service';
import { DropdownModule } from 'primeng/dropdown';



interface Cliente {
    id?: string;
    numero?: number;
    codigo?: string;
    nombreContacto?: string;
    nombreNegocio?: string;
    departamento?: string;
    municipio?: string;
    direccion?: string;
    nit?: number;
    encargadoBodega?: string;
    telefono?: string;
    tipoVentaAutorizado?: 'Contado' | 'Crédito' | 'Ambas';
    observaciones?: string;
}

interface Column {
    field: string;
    header: string;
}

@Component({
    selector: 'app-clientes',
    standalone: true,
    templateUrl: './clientes.component.html',
    styleUrls: ['./clientes.component.scss'],
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
        ToastModule
    ],
    providers: [MessageService, ConfirmationService]
})
export class ClientesComponent implements OnInit {
    clienteDialog: boolean = false;
    clientes = signal<Cliente[]>([]);
    cliente!: Cliente;
    selectedClientes!: Cliente[] | null;
    submitted: boolean = false;
    cols!: Column[];

    departamentos = [
        { label: 'Guatemala', value: 'GT' },
        { label: 'Quetzaltenango', value: 'QZ' }
        // Agrega más departamentos aquí
    ];
    municipios: any[] = [];
    tiposVenta = [
        { label: 'Contado', value: 'Contado' },
        { label: 'Crédito', value: 'Crédito' },
        { label: 'Ambas', value: 'Ambas' }
    ];
    municipiosPorDepartamento: { [key: string]: string[] } = {
        'GT': ['Guatemala', 'Mixco', 'Villa Nueva'],
        'QZ': ['Quetzaltenango', 'Olintepeque']
    };

    @ViewChild('dt') dt!: Table;

    constructor(private messageService: MessageService, private confirmationService: ConfirmationService) { }

    ngOnInit() {
        this.cols = [
            { field: 'numero', header: 'No.' },
            { field: 'codigo', header: 'Código' },
            { field: 'nombreContacto', header: 'Contacto' },
            { field: 'nombreNegocio', header: 'Negocio' },
            { field: 'departamento', header: 'Departamento' },
            { field: 'municipio', header: 'Municipio' },
            { field: 'telefono', header: 'Teléfono' },
            { field: 'tipoVentaAutorizado', header: 'Tipo Venta' }
        ];
    }
    onGlobalFilter(event: Event) {
    const input = event.target as HTMLInputElement;
    this.dt.filterGlobal(input.value, 'contains');
}


    loadMunicipios() {
        const dep = this.cliente.departamento;
        this.municipios = this.municipiosPorDepartamento[dep!]?.map(m => ({ label: m, value: m })) || [];
    }

    openNew() {
        this.cliente = {
        nombreContacto: '',
        nombreNegocio: '',
        departamento: '',
        municipio: '',
        direccion: '',
        nit: undefined,
        encargadoBodega: '',
        telefono: '',
        tipoVentaAutorizado: undefined,
        observaciones: ''
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
        let _clientes = this.clientes();
        if (this.cliente.nombreContacto?.trim()) {
            if (this.cliente.id) {
                _clientes[this.findIndexById(this.cliente.id)] = this.cliente;
                this.clientes.set([..._clientes]);
                this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Cliente Actualizado', life: 3000 });
            } else {
                this.cliente.id = this.createId();
                this.cliente.numero = this.clientes().length + 1;
                this.cliente.codigo = this.generateCodigo();
                this.clientes.set([..._clientes, this.cliente]);
                this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Cliente Creado', life: 3000 });
            }
            this.clienteDialog = false;
            this.cliente = {};
        }
    }

    editCliente(cliente: Cliente) {
        this.cliente = { ...cliente };
        this.loadMunicipios();
        this.clienteDialog = true;
    }

    deleteCliente(cliente: Cliente) {
        this.confirmationService.confirm({
            message: '¿Está seguro que desea eliminar a ' + cliente.nombreContacto + '?',
            header: 'Confirmar',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.clientes.set(this.clientes().filter(val => val.id !== cliente.id));
                this.cliente = {};
                this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Cliente Eliminado', life: 3000 });
            }
        });
    }

    deleteSelectedClientes() {
        this.confirmationService.confirm({
            message: '¿Está seguro que desea eliminar los clientes seleccionados?',
            header: 'Confirmar',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.clientes.set(this.clientes().filter(val => !this.selectedClientes?.includes(val)));
                this.selectedClientes = null;
                this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Clientes Eliminados', life: 3000 });
            }
        });
    }

    findIndexById(id: string): number {
        return this.clientes().findIndex(c => c.id === id);
    }

    createId(): string {
        let id = '';
        const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for (let i = 0; i < 5; i++) {
            id += chars.charAt(Math.floor(Math.random() * chars.length));
        }
        return id;
    }

    generateCodigo(): string {
        const depCode = this.cliente.departamento;
        const correlativo = String(this.clientes().length + 1).padStart(2, '0');
        return `${depCode}${correlativo}`;
    }
}
