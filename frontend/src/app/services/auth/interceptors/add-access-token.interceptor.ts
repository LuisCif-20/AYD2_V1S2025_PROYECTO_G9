import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import {AuthService} from "../auth.service";

export const addAccessTokenInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService);
    const token = authService.accessToken();
    if (token) {
        const clone = req.clone({
            headers: req.headers.set('Authorization', `Bearer ${token}`)
        });
        return next(clone);
    }
    return next(req);
};
