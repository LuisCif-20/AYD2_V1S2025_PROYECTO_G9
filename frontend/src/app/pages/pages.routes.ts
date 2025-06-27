import {Routes} from '@angular/router';
import {Empty} from './empty/empty';
import {ClientesComponent} from './clientes/clientes.component';
import {StockComponent} from './stock/stock.component';
import {Sales} from "./sales/sales";
import {VendorsListComponent} from './vendors/vendors-list/vendors-list.component';
import {ProductListComponent} from './products/product-list/product-list.component';
import {EntradaInventarioComponent} from './entrada-inventario/entrada-inventario.component';
import {PagosComponent} from './pagos/pagos.component';
import {SalesOutletComponent} from './sales-outlet/sales-outlet/sales-outlet.component';
import {SalesOutletDetailComponent} from './sales-outlet/sales-outlet-detail/sales-outlet-detail.component';
import {BusinessListComponent} from './business/business-list/business-list.component';
import {Users} from "./user/users";
import {roleGuard} from "../services/auth/guards/role.guard";

export default [
    {
        path: 'users',
        component: Users,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL'] },
    },
    {
        path: 'products',
        component: ProductListComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_INVENTARIO'] },
    },
    {
        path: 'vendors',
        component: VendorsListComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL'] },
    },
    {
        path: 'sales',
        component: Sales,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS'] },
    },
    {
        path: 'client',
        component: ClientesComponent,
        canActivate: [roleGuard],
        data: { roles: ['GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS'] },
    },
    {
        path: 'pay',
        component: PagosComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS'] },
    },
    {
        path: 'stock',
        component: StockComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_INVENTARIO'] },
    },
    {
        path: 'entrada-inventario',
        component: EntradaInventarioComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_INVENTARIO'] },
    },
    {
        path: 'sales-outlet',
        component: SalesOutletComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_INVENTARIO'] },
    },
    {
        path: 'sales-outlet-detail',
        component: SalesOutletDetailComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL', 'GERENTE_INVENTARIO'] },
    },
    {
        path: 'business',
        component: BusinessListComponent,
        //canActivate: [roleGuard],
        //data: { roles: ['GERENTE_GENERAL'] },
    },
    {
        path: '**',
        redirectTo: '/notfound'
    }
] as Routes;
