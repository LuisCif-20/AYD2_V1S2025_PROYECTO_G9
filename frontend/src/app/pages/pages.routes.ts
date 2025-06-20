import {Routes} from '@angular/router';
import {Crud} from './crud/crud';
import {Empty} from './empty/empty';
import { VendorsListComponent } from './vendors/vendors-list/vendors-list.component';

export default [
    {path: 'crud', component: Crud},
    {path: 'vendors', component: VendorsListComponent},
    {path: 'empty', component: Empty},
    {path: '**', redirectTo: '/notfound'}
] as Routes;
