import {Routes} from '@angular/router';
import {Crud} from './crud/crud';
import {Empty} from './empty/empty';
import { ClientesComponent } from './clientes/clientes/clientes.component';
import { StockComponent } from './stock/stock/stock.component';

export default [
    {path: 'crud', component: Crud},
    {path: 'empty', component: Empty},
    {path: 'client', component: ClientesComponent},
    { path: 'stock', component: StockComponent },
    {path: '**', redirectTo: '/notfound'}
] as Routes;
