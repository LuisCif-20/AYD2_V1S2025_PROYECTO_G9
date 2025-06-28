import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {MenuItem} from 'primeng/api';
import {AppMenuitem} from './app.menuitem';
import {AuthService} from "../services/auth/auth.service";

@Component({
    selector: 'app-menu',
    standalone: true,
    imports: [CommonModule, AppMenuitem, RouterModule],
    template: `
        <ul class="layout-menu">
            <ng-container *ngFor="let item of model; let i = index">
                <li app-menuitem *ngIf="!item.separator" [item]="item" [index]="i" [root]="true"></li>
                <li *ngIf="item.separator" class="menu-separator"></li>
            </ng-container>
        </ul> `
})
export class AppMenu {
    model: MenuItem[] = [];

    constructor(private authService: AuthService) {}

    ngOnInit() {
        const role = this.authService.user()?.role?.name || 'ANONYMOUS';

        this.model = [
            {
                label: 'Inicio',
                items: [{ label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/'] }]
            },
            {
                label: 'Negocio',
                icon: 'pi pi-fw pi-briefcase',
                routerLink: ['/pages'],
                items: [
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_INVENTARIO']) ? [{
                        label: 'Inventario',
                        icon: 'pi pi-fw pi-warehouse',
                        routerLink: ['/pages/entrada-inventario']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_INVENTARIO']) ? [{
                        label: 'Stock',
                        icon: 'pi pi-fw pi pi-box',
                        routerLink: ['/pages/stock']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_INVENTARIO']) ? [{
                        label: 'Salidas',
                        icon: 'pi pi-fw pi-arrow-circle-right',
                        routerLink: ['/pages/sales-outlet']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS']) ? [{
                        label: 'Ventas',
                        icon: 'pi pi-fw pi-truck',
                        routerLink: ['/pages/sales']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS']) ? [{
                        label: 'Pagos',
                        icon: 'pi pi-fw pi-wallet',
                        routerLink: ['/pages/pay']
                    }] : [])

                ]
            },
            {
                label: 'Mantenimientos',
                icon: 'pi pi-fw pi-briefcase',
                routerLink: ['/pages'],
                items: [
                    ...(this.hasRole(role, ['GERENTE_GENERAL']) ? [{
                        label: 'Usuarios',
                        icon: 'pi pi-fw pi-user',
                        routerLink: ['/pages/users']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL']) ? [{
                        label: 'Vendedores',
                        icon: 'pi pi-fw pi-face-smile',
                        routerLink: ['/pages/vendors']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL']) ? [{
                        label: 'Negocios',
                        icon: 'pi pi-fw pi-shop',
                        routerLink: ['/pages/business']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS']) ? [{
                        label: 'Clientes',
                        icon: 'pi pi-fw pi-users',
                        routerLink: ['/pages/client']
                    }] : []),
                    ...(this.hasRole(role, ['GERENTE_GENERAL', 'GERENTE_INVENTARIO']) ? [{
                        label: 'Productos',
                        icon: 'pi pi-fw pi-list-check',
                        routerLink: ['/pages/products']
                    }] : [])
                ]
            }
        ];
    }

    private hasRole(role: string, allowedRoles: string[]): boolean {
        return allowedRoles.includes(role);
    }

}
