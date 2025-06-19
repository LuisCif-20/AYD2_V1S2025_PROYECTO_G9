import {Routes} from '@angular/router';
import {Crud} from './crud/crud';
import {Empty} from './empty/empty';
import { ClientesComponent } from './clientes/clientes/clientes.component';

export default [
    {path: 'crud', component: Crud},
    {path: 'empty', component: Empty},
    {path: 'client', component: ClientesComponent},
    {path: '**', redirectTo: '/notfound'}
] as Routes;
