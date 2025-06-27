import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import {ToastModule} from "primeng/toast";
import {CommonModule} from "@angular/common";
import {TableModule} from "primeng/table";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {ToolbarModule} from "primeng/toolbar";
import {RatingModule} from "primeng/rating";
import {InputTextModule} from "primeng/inputtext";
import {TextareaModule} from "primeng/textarea";
import {SelectModule} from "primeng/select";
import {RadioButtonModule} from "primeng/radiobutton";
import {InputNumberModule} from "primeng/inputnumber";
import {DialogModule} from "primeng/dialog";
import {TagModule} from "primeng/tag";
import {InputIconModule} from "primeng/inputicon";
import {IconFieldModule} from "primeng/iconfield";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DatePickerModule} from "primeng/datepicker";
import {PasswordModule} from "primeng/password";
import {DropdownModule} from "primeng/dropdown";

interface Role {
    id: string;
    name: string;
}

interface User {
    id: string;
    firstname: string;
    lastname: string;
    email: string;
    isActive: boolean;
    role: Role;
    password?: string
}

@Component({
    selector: 'app-users',
    templateUrl: 'users.html',
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
        PasswordModule,
        DropdownModule
    ],
    providers: [MessageService, ConfirmationService],
})
export class Users implements OnInit {
    users: User[] = [];
    selectedUsers: User[] = [];

    userForm: Partial<User> = {};
    userDialog = false;
    viewUserDialog = false;
    deleteUserDialog = false;

    userToDelete?: User;
    selectedUserForView?: User;
    submitted = false;
    saving = false;
    isEditMode = false;

    roles = [
        { label: 'Gerente General', value: { id: '1', name: 'GERENTE_GENERAL' } },
        { label: 'Gerente Inventario', value: { id: '2', name: 'GERENTE_INVENTARIO' } },
        { label: 'Gerente Ventas Finanzas', value: { id: '3', name: 'GERENTE_VENTAS_FINANZAS' } }
    ];

    constructor(
        private messageService: MessageService,
        private confirmationService: ConfirmationService
    ) {}

    ngOnInit(): void {
        this.users = [
            {
                id: '9f88381c-b809-4792-a5c9-0175cf026c66',
                firstname: 'Grupo',
                lastname: '9',
                email: 'gg@example.com',
                isActive: true,
                role: { id: '1', name: 'GERENTE_GENERAL' }
            },
            {
                id: 'c278faac-f2d4-4635-83be-11578cee39cd',
                firstname: 'Bilhan',
                lastname: 'Lopez',
                email: 'jehiel.lopez@example.com',
                isActive: true,
                role: { id: '2', name: 'GERENTE_INVENTARIO' }
            },
            {
                id: '66bce5d0-1e2d-4617-85f8-c7e1ef862ca7',
                firstname: 'Juan',
                lastname: 'Pérez',
                email: 'juan.perez@example.com',
                isActive: true,
                role: { id: '3', name: 'GERENTE_VENTAS_FINANZAS' }
            }
        ];
    }

    openNew() {
        this.userForm = { isActive: true };
        this.submitted = false;
        this.userDialog = true;
        this.isEditMode = false;
    }

    editUser(user: User) {
        this.userForm = { ...user };
        this.userDialog = true;
        this.isEditMode = true;
    }

    saveUser() {
        this.submitted = true;
        const form = this.userForm;

        if (!form.firstname || !form.lastname || !form.email || !form.role) return;

        this.saving = true;

        if (this.isEditMode && form.id) {
            const index = this.users.findIndex(u => u.id === form.id);
            if (index !== -1) {
                this.users[index] = form as User;
            }
        } else {
            const newUser: User = {
                id: crypto.randomUUID(),
                firstname: form.firstname!,
                lastname: form.lastname!,
                email: form.email!,
                isActive: form.isActive ?? true,
                role: form.role!
            };
            this.users.push(newUser);
        }

        this.userDialog = false;
        this.saving = false;
        this.userForm = {};
        this.messageService.add({
            severity: 'success',
            summary: 'Éxito',
            detail: 'Usuario guardado correctamente'
        });
    }

    deleteUser(user: User) {
        this.userToDelete = user;
        this.deleteUserDialog = true;
    }

    confirmDelete() {
        if (this.userToDelete) {
            this.users = this.users.filter(u => u.id !== this.userToDelete!.id);
            this.messageService.add({
                severity: 'success',
                summary: 'Eliminado',
                detail: 'Usuario eliminado correctamente'
            });
        }
        this.deleteUserDialog = false;
        this.userToDelete = undefined;
    }

    viewUser(user: User) {
        this.selectedUserForView = user;
        this.viewUserDialog = true;
    }

    editFromView() {
        if (this.selectedUserForView) {
            this.editUser(this.selectedUserForView);
            this.viewUserDialog = false;
        }
    }

    hideDialog() {
        this.userDialog = false;
        this.submitted = false;
    }

    hideDeleteDialog() {
        this.deleteUserDialog = false;
    }

    hideViewDialog() {
        this.viewUserDialog = false;
    }

    getRoleLabel(roleName: string): string {
        switch (roleName) {
            case 'GERENTE_GENERAL': return 'Gerente General';
            case 'GERENTE_INVENTARIO': return 'Gerente Inventario';
            case 'GERENTE_VENTAS_FINANZAS': return 'Gerente Ventas y Finanzas';
            default: return roleName;
        }
    }

    getRoleSeverity(roleName: string): string {
        switch (roleName) {
            case 'GERENTE_GENERAL': return 'success';
            case 'GERENTE_INVENTARIO': return 'info';
            case 'GERENTE_VENTAS_FINANZAS': return 'warning';
            default: return 'secondary';
        }
    }

    getStatusSeverity(isActive: boolean): string {
        return isActive ? 'success' : 'danger';
    }

    onGlobalFilter(dt: any, event: Event) {
        const input = event.target as HTMLInputElement;
        dt.filterGlobal(input.value, 'contains');
    }

    exportCSV() {
        const headers = ['Nombre', 'Apellido', 'Email', 'Rol', 'Estado'];
        const rows = this.users.map(u => [
            u.firstname,
            u.lastname,
            u.email,
            this.getRoleLabel(u.role.name),
            u.isActive ? 'Activo' : 'Inactivo'
        ]);

        const csvContent = [headers, ...rows].map(e => e.join(',')).join('\n');
        const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
        const link = document.createElement('a');
        const url = URL.createObjectURL(blob);

        link.setAttribute('href', url);
        link.setAttribute('download', 'usuarios.csv');
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
}
