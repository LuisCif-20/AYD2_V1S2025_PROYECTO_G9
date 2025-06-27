import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, switchMap, throwError } from 'rxjs';
import {AuthService} from "../auth.service";

export const refreshTokenInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService);
    return next(req).pipe(
        catchError((error: HttpErrorResponse) => {
            if (error.status === 401 && authService.accessToken()) {
                return authService.refreshToken().pipe(
                    switchMap(() => {
                        const newReq = req.clone();
                        return next(newReq);
                    })
                );
            }
            return throwError(() => error);
        })
    );
};
