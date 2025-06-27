import {Component} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {Router, RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {StyleClassModule} from 'primeng/styleclass';
import {LayoutService} from '../services/layout/layout.service';
import {AuthService} from "../services/auth/auth.service";
import {UtilsService} from "../services/utils/utils.service";

@Component({
    selector: 'app-topbar',
    standalone: true,
    imports: [RouterModule, CommonModule, StyleClassModule],
    template: `
        <div class="layout-topbar">
            <div class="layout-topbar-logo-container">
                <button class="layout-menu-button layout-topbar-action" (click)="layoutService.onMenuToggle()">
                    <i class="pi pi-bars"></i>
                </button>
                <a class="layout-topbar-logo" routerLink="/">
                    <img src="assets/logo.png" style="width: 35px; height: 45px; margin-right: 10px;"/>
                    <span>IMPORCOMGUA</span>
                </a>
            </div>

            <div class="layout-topbar-actions">
                <div class="layout-config-menu">
                    <button type="button" class="layout-topbar-action" (click)="toggleDarkMode()">
                        <i [ngClass]="{ 'pi ': true, 'pi-moon': layoutService.isDarkTheme(), 'pi-sun': !layoutService.isDarkTheme() }"></i>
                    </button>
                    <!--<div class="relative">
                        <button
                                class="layout-topbar-action layout-topbar-action-highlight"
                                pStyleClass="@next"
                                enterFromClass="hidden"
                                enterActiveClass="animate-scalein"
                                leaveToClass="hidden"
                                leaveActiveClass="animate-fadeout"
                                [hideOnOutsideClick]="true"
                        >
                            <i class="pi pi-palette"></i>
                        </button>
                        <app-configurator />
                    </div>-->
                </div>

                <button type="button" class="layout-topbar-action" (click)="logout()">
                    <i class="pi pi-power-off"></i>
                    <span>Logout</span>
                </button>
                
            </div>
        </div>`
})
export class AppTopbar {
    items!: MenuItem[];

    constructor(public layoutService: LayoutService,
                private authService: AuthService,
                private router: Router,
                private utilsService: UtilsService) {
    }

    toggleDarkMode() {
        this.layoutService.layoutConfig.update((state) => ({...state, darkTheme: !state.darkTheme}));
    }

    logout() {
        this.authService.logout().subscribe({
            next: () => {
                this.utilsService.success('Hasta pronto!');
                setTimeout(async () => {
                    await this.router.navigateByUrl('/auth/login');
                }, 500)
            },
            error: () => this.utilsService.error('No es posible cerrar su sesión en este momento!')
        });
    }
}
