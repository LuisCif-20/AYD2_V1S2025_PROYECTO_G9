import {Routes} from '@angular/router';
import {Empty} from './empty/empty';
import { ClientesComponent } from './clientes/clientes/clientes.component';
import { StockComponent } from './stock/stock/stock.component';
import { VendorsListComponent } from './vendors/vendors-list/vendors-list.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { EntradaInventarioComponent } from './inventario/entrada-inventario/entrada-inventario.component';
import { PagosComponent } from './payments/pagos/pagos.component';

export default [
    {path: 'products', component: ProductListComponent},
    {path: 'vendors', component: VendorsListComponent},
    {path: 'empty', component: Empty},
    {path: 'client', component: ClientesComponent},
    { path: 'stock', component: StockComponent },
    { path: 'entrada-inventario', component: EntradaInventarioComponent },
    { path: 'pay', component: PagosComponent },
    {path: '**', redirectTo: '/notfound'}
] as Routes;
