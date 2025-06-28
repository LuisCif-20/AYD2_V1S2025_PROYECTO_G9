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
import {CheckboxModule} from "primeng/checkbox";
import {UserService} from "../../services/users/user.service";
import {UtilsService} from "../../services/utils/utils.service";
import {Rol, User} from "../../models/models";

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
        DropdownModule,
        CheckboxModule
    ],
    providers: [ConfirmationService],
})
export class Users implements OnInit {
    users: User[] = [];
    selectedUsers: User[] = [];

    userForm: User = this.newUser();
    userDialog = false;
    viewUserDialog = false;
    deleteUserDialog = false;

    userToDelete?: User;
    selectedUserForView?: User;
    submitted = false;
    saving = false;
    isEditMode = false;

    roles: Rol[] = [];

    mode: 'view' | 'edit' | 'create' = 'view';

    constructor(
        private utilsService: UtilsService,
        private confirmationService: ConfirmationService,
        private userService: UserService
    ) {}

    ngOnInit(): void {
        this.getRoles()
        this.getUsers();
    }

    getUsers(){
        this.userService.getUsers().subscribe({
            next: (data) => this.users = data,
            error: (err) => {
                const detalle = err?.error?.detail || 'Error al cargar las ventas.';
                this.utilsService.error(detalle);
            }
        });
    }

    openNew() {
        this.userForm = this.newUser();
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

        if (!form.firstName || !form.lastName || !form.email || !form.role) return;

        this.saving = true;
        let newUser: any = {
            firstName: form.firstName!,
            lastName: form.lastName!,
            email: form.email!,
            password: form.password!,
            isActive: form.isActive!,
            roleId: form.role.id!,
        };
        if (this.isEditMode && form.id) {
            this.userService.updateUser(newUser, form.id)
                .subscribe({
                    next: (data) => {
                        console.log('data:', data);
                        this.userDialog = false;
                        this.saving = false;
                        this.userForm = this.newUser();
                        this.utilsService.success('Usuario editado correctamente');
                        this.getUsers();
                    },
                    error: (err) => {
                        this.saving = false;
                        const detalle = err?.error?.detail || 'Error al editar los usuarios.';
                        this.utilsService.error(detalle);
                    }
                });
        } else {

            this.userService.createUser(newUser)
                .subscribe({
                    next: (data) => {
                        console.log('data:', data);
                        this.userDialog = false;
                        this.saving = false;
                        this.userForm = this.newUser();
                        this.utilsService.success('Usuario guardado correctamente');
                        this.getUsers();
                    },
                    error: (err) => {
                        this.saving = false;
                        const detalle = err?.error?.detail || 'Error al cargar los usuarios.';
                        this.utilsService.error(detalle);
                    }
                });
        }
    }

    deleteUser(user: User) {
        this.userToDelete = user;
        this.deleteUserDialog = true;
    }

    confirmDelete() {
        this.saving = true;
        if (this.userToDelete) {
            this.userService.deleteUser(this.userToDelete.id).subscribe({
                next: () => {
                    this.saving = false;
                    this.utilsService.success(`Usuario anulado correctamente`);
                    this.deleteUserDialog = false;
                    this.userToDelete = undefined;
                    this.getUsers();
                },
                error: (err) => {
                    this.saving = false;
                    const detalle = err?.error?.detail || `No se pudo anular el usuario.`;
                    this.utilsService.error(detalle);
                }
            });
        }
    }

    viewUser(user: User) {
        this.userForm = { ...user };
        this.viewUserDialog = true;
    }


    hideDialog() {
        this.userDialog = false;
        this.submitted = false;
    }

    hideDeleteDialog() {
        this.deleteUserDialog = false;
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
            u.firstName,
            u.lastName,
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


    getRoles() {
        this.userService.getRoles().subscribe({
            next: (data) => this.roles = data,
            error: (err) => {
                const detalle = err?.error?.detail || 'Error al cargar los roles.';
                this.utilsService.error(detalle);
            }
        });
    }

    newUser() {
        return {
            email: "",
            firstName: "",
            id: "",
            isActive: false,
            lastName: "",
            password: '',
            role: {id: 0, name: ''}
        };
    }

    activateUser(user: User) {
        this.userService.activateUser(user.id).subscribe({
            next: () => {
                this.saving = false;
                this.utilsService.success(`Usuario activado correctamente`);
                this.getUsers();
            },
            error: (err) => {
                this.saving = false;
                const detalle = err?.error?.detail || `No se pudo anular el usuario.`;
                this.utilsService.error(detalle);
            }
        });
    }
}
