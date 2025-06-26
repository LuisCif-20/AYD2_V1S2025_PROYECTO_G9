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
import {Client, ItemProduct, Sale, SaleDetailForm, SaleForm, Salesman, User, UserForm} from "../../models/models";
import {UserService} from "../../services/users/user.service";
import {Checkbox} from "primeng/checkbox";
import {Password} from "primeng/password";
import {UtilsService} from "../../services/utils/utils.service";

@Component({
    selector: 'app-users',
    standalone: true,
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
        Checkbox,
        Password
    ],
    providers: [ProductService, ConfirmationService, ClienteService]
})
export class Users  implements OnInit {
    users: User[] = []
    selectedUsers: User[] = []
    userDialog = false
    viewUserDialog = false
    deleteUserDialog = false
    submitted = false
    saving = false
    isEditMode = false

    userForm: UserForm = {
        id: 0,
        usuario: "",
        nombre: "",
        apellido: "",
        email: "",
        contrasena: "",
        rol: "USER",
        activo: true,
    }

    selectedUserForView: User | null = null
    userToDelete: User | null = null

    cols: any[] = []

    roles = [
        { label: "Administrador", value: "ADMIN" },
        { label: "Usuario", value: "USER" },
        { label: "Moderador", value: "MODERATOR" },
    ]

    constructor(
        private userService: UserService,
        private utilsService: UtilsService
    ) {}

    ngOnInit() {
        this.loadUsers()
        this.initializeColumns()
    }

    loadUsers() {
        this.userService.getUsers().subscribe({
            next: (users) => {
                this.users = users;
            },
            error: (err) => {
                console.error('Error al cargar los usuarios:', err);
                const detalle = err?.error?.detail || 'Error al cargar los usuarios. Por favor, intente más tarde.';
                this.utilsService.error(detalle);
            }
        });
    }

    initializeColumns() {
        this.cols = [
            { field: "usuario", header: "Usuario" },
            { field: "nombre", header: "Nombre" },
            { field: "apellido", header: "Apellido" },
            { field: "email", header: "Email" },
            { field: "rol", header: "Rol" },
            { field: "activo", header: "Estado" },
            { field: "fechaCreacion", header: "Fecha Creación" },
        ]
    }

    openNew() {
        this.userForm = {
            id: 0,
            usuario: "",
            nombre: "",
            apellido: "",
            email: "",
            contrasena: "",
            rol: "USER",
            activo: true,
        }
        this.submitted = false
        this.isEditMode = false
        this.userDialog = true
    }

    editUser(user: User) {
        this.userForm = {
            id: 0,
            usuario: user.usuario,
            nombre: user.nombre,
            apellido: user.apellido,
            email: user.email,
            contrasena: "",
            rol: user.rol,
            activo: user.activo,
        }
        this.selectedUserForView = user
        this.submitted = false
        this.isEditMode = true
        this.userDialog = true
    }

    deleteUser(user: User) {
        this.userToDelete = user
        this.deleteUserDialog = true
    }

    confirmDelete() {
        if (this.userToDelete) {
            this.userService.deleteUser(this.userToDelete.id!).subscribe(() => {
                this.utilsService.success("Usuario eliminado correctamente")
                this.loadUsers()
            })
            this.deleteUserDialog = false
            this.userToDelete = null
        }
    }

    viewUser(user: User) {
        this.selectedUserForView = user
        this.viewUserDialog = true
    }

    hideDialog() {
        this.userDialog = false
        this.submitted = false
        this.selectedUserForView = null
    }

    hideViewDialog() {
        this.viewUserDialog = false
        this.selectedUserForView = null
    }

    hideDeleteDialog() {
        this.deleteUserDialog = false
        this.userToDelete = null
    }

    saveUser() {
        this.submitted = true

        if (
            this.userForm.usuario?.trim() &&
            this.userForm.nombre?.trim() &&
            this.userForm.apellido?.trim() &&
            this.userForm.email?.trim() &&
            (this.isEditMode || this.userForm.contrasena?.trim())
        ) {
            this.saving = true

            let userData: UserForm = {
                id: 0,
                usuario: this.userForm.usuario,
                nombre: this.userForm.nombre,
                apellido: this.userForm.apellido,
                email: this.userForm.email,
                contrasena: this.userForm.contrasena || "********",
                rol: this.userForm.rol,
                activo: this.userForm.activo,
            }

            if (this.isEditMode && !!this.selectedUserForView) {

                this.userService.updateUser(userData).subscribe({
                    next: () => {
                        this.utilsService.success('Usuario actualizado correctamente');
                        this.loadUsers();
                        this.hideDialog();
                        this.saving = false;
                    },
                    error: (err) => {
                        this.saving = false;
                        const detalle = err?.error?.detail || 'Error al actualizar el usuario. Por favor, intente más tarde.';
                        this.utilsService.error(detalle);
                    }
                });
            } else {
                this.userService.createUser(userData).subscribe({
                    next: () => {
                        this.utilsService.success('Usuario creado correctamente');
                        this.loadUsers();
                        this.hideDialog();
                        this.saving = false;
                    },
                    error: (err) => {
                        this.saving = false;
                        const detalle = err?.error?.detail || 'Error al crear el usuario. Por favor, intente más tarde.';
                        this.utilsService.error(detalle);
                    }
                });

            }
        }
    }

    onGlobalFilter(table: any, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, "contains")
    }

    getRoleSeverity(rol: string): string {
        switch (rol) {
            case "ADMIN":
                return "danger"
            case "MODERATOR":
                return "warning"
            case "USER":
                return "info"
            default:
                return "info"
        }
    }

    getStatusSeverity(activo: boolean): string {
        return activo ? "success" : "secondary"
    }

    getRoleLabel(rol: string): string {
        const role = this.roles.find((r) => r.value === rol)
        return role ? role.label : rol
    }

    exportCSV() {
        const csvData = this.users.map((user) => ({
            ID: user.id,
            Usuario: user.usuario,
            Nombre: user.nombre,
            Apellido: user.apellido,
            Email: user.email,
            Rol: this.getRoleLabel(user.rol),
            Estado: user.activo ? "Activo" : "Inactivo"
        }))

        console.log("Exportando CSV:", csvData)
        this.utilsService.success("Funcionalidad de exportación CSV lista para implementar")
    }

    editFromView() {
        if (this.selectedUserForView) {
            this.hideViewDialog()
            this.editUser(this.selectedUserForView)
        }
    }
}
