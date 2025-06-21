import {Routes} from '@angular/router';
import {Crud} from './crud/crud';
import {Empty} from './empty/empty';
import { ClientesComponent } from './clientes/clientes/clientes.component';
import { StockComponent } from './stock/stock/stock.component';
import {Sales} from "./sales/sales";

export default [
    {path: 'crud', component: Crud},
    {path: 'sales', component: Sales},
    {path: 'empty', component: Empty},
    {path: 'client', component: ClientesComponent},
    { path: 'stock', component: StockComponent },
    {path: '**', redirectTo: '/notfound'}
] as Routes;
