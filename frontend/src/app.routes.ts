import {Routes} from '@angular/router';
import {AppLayout} from './app/layout/app.layout';
import {Dashboard} from './app/pages/dashboard/dashboard';
import {Notfound} from './app/pages/notfound/notfound';
import { publicRoutesGuard } from './app/services/auth/guards/public-routes.guard';
import { authGuard } from './app/services/auth/guards/auth.guard';

export const appRoutes: Routes = [
    { 
        path: 'auth',
        canActivate: [publicRoutesGuard],
        loadChildren: () => import('./app/pages/auth/auth.routes'),
    },
    {
        path: '',
        component: AppLayout,
        canActivate: [authGuard],
        children: [
            {
                path: '', component: Dashboard
            },
            {
                path: 'pages',
                loadChildren: () => import('./app/pages/pages.routes')
            },
        ]
    },
    { 
        path: 'notfound',
        component: Notfound 
    },
    { 
        path: '**',
        redirectTo: '/notfound'
    }
];
