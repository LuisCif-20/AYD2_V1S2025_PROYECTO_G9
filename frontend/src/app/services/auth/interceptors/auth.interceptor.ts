import { Injectable } from '@angular/core';
import {
    HttpInterceptor,
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { switchMap, catchError, filter, take } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    private isRefreshing = false;
    private refreshTokenSubject = new BehaviorSubject<boolean | null>(null);

    constructor(
        private authService: AuthService,
        private router: Router
    ) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authReq = req.clone({ withCredentials: true });

        return next.handle(authReq).pipe(
            catchError((error: HttpErrorResponse) => {
                if (error.status === 401) {
                    return this.handle401Error(req, next);
                }
                return throwError(() => error);
            })
        );
    }

    private handle401Error(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!this.isRefreshing) {
            this.isRefreshing = true;
            this.refreshTokenSubject.next(null);

            return this.authService.refreshToken().pipe(
                switchMap(() => {
                    this.isRefreshing = false;
                    this.refreshTokenSubject.next(true);
                    return next.handle(req.clone({ withCredentials: true }));
                }),
                catchError(err => {
                    this.isRefreshing = false;
                    this.authService.logout();
                    this.router.navigate(['/auth/login']);
                    return throwError(() => err);
                })
            );
        } else {
           return this.refreshTokenSubject.pipe(
                filter(result => result === true),
                take(1),
                switchMap(() => next.handle(req.clone({ withCredentials: true })))
            );
        }
    }
}
