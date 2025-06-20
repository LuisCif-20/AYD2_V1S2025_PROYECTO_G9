import {Routes} from '@angular/router';
import {Crud} from './crud/crud';
import {Empty} from './empty/empty';
import { ClientesComponent } from './clientes/clientes/clientes.component';
import { StockComponent } from './stock/stock/stock.component';
import { VendorsListComponent } from './vendors/vendors-list/vendors-list.component';
import { EntradaInventarioComponent } from './inventario/entrada-inventario/entrada-inventario.component';

export default [
    {path: 'crud', component: Crud},
    {path: 'vendors', component: VendorsListComponent},
    {path: 'empty', component: Empty},
    {path: 'client', component: ClientesComponent},
    { path: 'stock', component: StockComponent },
    { path: 'entrada-inventario', component: EntradaInventarioComponent },
    {path: '**', redirectTo: '/notfound'}
] as Routes;
