import {HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';
import {ApplicationConfig} from '@angular/core';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {provideRouter, withEnabledBlockingInitialNavigation, withInMemoryScrolling} from '@angular/router';
import Aura from '@primeng/themes/aura';
import {providePrimeNG} from 'primeng/config';
import {appRoutes} from './app.routes';
import {MessageService} from "primeng/api";
import {addWithCredentialsInterceptor} from "./app/services/auth/interceptors/add-with-credentials.interceptor";
import {addAccessTokenInterceptor} from "./app/services/auth/interceptors/add-access-token.interceptor";
import {refreshTokenInterceptor} from "./app/services/auth/interceptors/refresh-token.interceptor";

export const appConfig: ApplicationConfig = {
    providers: [
        MessageService,
        provideRouter(appRoutes, withInMemoryScrolling({
            anchorScrolling: 'enabled',
            scrollPositionRestoration: 'enabled'
        }), withEnabledBlockingInitialNavigation()),
        provideHttpClient(withFetch()),
        provideAnimationsAsync(),
        providePrimeNG({theme: {preset: Aura, options: {darkModeSelector: '.app-dark'}}}),
        provideHttpClient(
            withFetch(),
            withInterceptors([
                addWithCredentialsInterceptor,
                addAccessTokenInterceptor,
                refreshTokenInterceptor
            ])
        ),
    ]
};
