import {Routes} from '@angular/router';
import {AppLayout} from './app/layout/app.layout';
import {Dashboard} from './app/pages/dashboard/dashboard';
import {Notfound} from './app/pages/notfound/notfound';
import {checkAuthGuard} from "./app/services/auth/guards/check-auth.guard";

export const appRoutes: Routes = [
    {
        path: '',
        component: AppLayout,
        canActivateChild: [checkAuthGuard],
        children: [
            {path: '', component: Dashboard},
            {path: 'pages', loadChildren: () => import('./app/pages/pages.routes')}
        ]
    },
    { path: 'notfound', component: Notfound },
    { path: 'auth', loadChildren: () => import('./app/pages/auth/auth.routes') },
    { path: '**', redirectTo: '/notfound' }
];
