import {Routes} from '@angular/router';
import {Empty} from './empty/empty';
import { ClientesComponent } from './clientes/clientes.component';
import { StockComponent } from './stock/stock.component';
import {Sales} from "./sales/sales";
import { VendorsListComponent } from './vendors/vendors-list/vendors-list.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { EntradaInventarioComponent } from './entrada-inventario/entrada-inventario.component';
import { PagosComponent } from './pagos/pagos.component';
import { SalesOutletComponent } from './sales-outlet/sales-outlet/sales-outlet.component';
import { SalesOutletDetailComponent } from './sales-outlet/sales-outlet-detail/sales-outlet-detail.component';
import {Users} from "./user/users";

export default [
    { path: 'user', component: Users},
    { path: 'products', component: ProductListComponent},
    { path: 'vendors', component: VendorsListComponent},
    { path: 'sales', component: Sales},
    { path: 'empty', component: Empty},
    { path: 'client', component: ClientesComponent},
    { path: 'stock', component: StockComponent },
    { path: 'entrada-inventario', component: EntradaInventarioComponent },
    { path: 'pay', component: PagosComponent },
    { path: 'sales-outlet', component: SalesOutletComponent },
    { path: 'sales-outlet-detail', component: SalesOutletDetailComponent },
    {path: '**', redirectTo: '/notfound'}
] as Routes;
