import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { catchError, map, Observable, of, switchMap, throwError } from 'rxjs';

import { AuthResponse, AuthStatus, Login, User } from '../../models/models';
import { environment } from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})

export class AuthService {

    private readonly AUTH_URL = `${environment.IMPORCOMGUA}/auth`;
    private readonly USER_URL = `${environment.IMPORCOMGUA}`;
    private token = signal<string|null>(null);
    private _authStatus = signal<AuthStatus>(AuthStatus.CHECKING);
    private _authUser = signal<User | null>(null);

    constructor(private httpClient: HttpClient) { }

    login(body: Login): Observable<boolean> {
        const url = `${this.AUTH_URL}/login`;
        return this.httpClient.post<AuthResponse>(url, body).pipe(
            switchMap(({ token }) => {
                if (token) {
                    this.token.set(token);
                    return this.getUserInfo();
                }
                return of(false);
            }),
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }


    public logout(): Observable<void> {
        const url: string = `${this.AUTH_URL}/logout`;
        return this.httpClient.post<AuthResponse>(url, null).pipe(
            map(() => {
                this._authStatus.set(AuthStatus.NOT_AUTHENTICATED);
                this._authUser.set(null);
                this.token.set(null);
            })
        );
    }

    public refreshToken(): Observable<boolean> {
        const url: string = `${ this.AUTH_URL }/refresh`;
        return this.httpClient.post<AuthResponse>(url, null).pipe(
            switchMap(({ token }) => {
                this.token.set(token!);
                return this.getUserInfo();
            }),
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }

    accessToken() {
        return this.token();
    }

    authStatus() {
        return this._authStatus();
    }

    user() {
        return this._authUser();
    }

    public getUserInfo(): Observable<boolean> {
        return this.httpClient.get<User>(`${this.USER_URL}/user-accounts/me`).pipe(
            map(authUser => {
                this._authStatus.set(AuthStatus.AUTHENTICATED);
                this._authUser.set(authUser);
                return true;
            }),
            catchError((error: HttpErrorResponse) => throwError(() => error))
        )
    }

    public checkAuthStatus(): Observable<boolean> {
    return this.refreshToken().pipe(
        map(() => {
            console.log(AuthStatus.AUTHENTICATED, this.authStatus());
            return true;
        }),
        catchError((error: HttpErrorResponse) => {
            console.error('checkAuthStatus: refresh failed', error);
            this._authStatus.set(AuthStatus.NOT_AUTHENTICATED);
            this._authUser.set(null);
            this.token.set(null);
            return of(false);
        })
    );
}

}
