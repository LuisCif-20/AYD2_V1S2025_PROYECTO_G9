import {Routes} from '@angular/router';
import {AppLayout} from './app/layout/app.layout';
import {Dashboard} from './app/pages/dashboard/dashboard';
import {Notfound} from './app/pages/notfound/notfound';
import {AuthGuard} from "./app/services/auth/auth.guard";

export const appRoutes: Routes = [
    {
        path: '',
        component: AppLayout,
        children: [
            {path: '', component: Dashboard, canActivate: [AuthGuard]},
            {path: 'uikit', loadChildren: () => import('./app/components/components.routes')},
            {path: 'pages', loadChildren: () => import('./app/pages/pages.routes')}
        ]
    },
    { path: 'notfound', component: Notfound },
    { path: 'auth', loadChildren: () => import('./app/pages/auth/auth.routes') },
    { path: '**', redirectTo: '/notfound' }
];
