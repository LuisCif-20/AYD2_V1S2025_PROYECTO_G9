import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {inject, Injectable, signal} from '@angular/core';
import {catchError, map, Observable, of, switchMap, throwError} from 'rxjs';

import {AuthResponse, AuthStatus, Login, User} from '../../models/models';
import {environment} from "../../../environments/environment";
import {tap} from "rxjs/operators";
import {Router} from "@angular/router";
import {sign} from "chart.js/helpers";
import {patchState} from "@ngrx/signals";
import {mapResponse} from "@ngrx/operators";

@Injectable({
    providedIn: 'root'
})

export class AuthService {

    private readonly AUTH_URL = `${environment.IMPORCOMGUA}/auth`;
    private readonly TOKEN = 'token';
    private token = signal<string>('');
    private _authStatus = signal<AuthStatus>(AuthStatus.NOT_AUTHENTICATED);
    private _authUser = signal<User | null>(null);

    constructor(private httpClient: HttpClient) { }

    login(body: Login): Observable<boolean> {
        const url = `${this.AUTH_URL}/login`;
        return this.httpClient.post<AuthResponse>(url, body).pipe(
            switchMap(({ token }) => {
                if (token) {
                    this.token.set(token);
                    return this.getUserInfo(token);
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
                this.token.set('');
            })
        );
    }

    refreshToken(): Observable<AuthResponse> {
        const url = `${this.AUTH_URL}/refresh`;
        return this.httpClient.post<AuthResponse>(url, null);
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

    public getUserInfo(token: string): Observable<boolean> {
        return this.httpClient.get<User>(`${this.AUTH_URL}/user-accounts/me`).pipe(
            map(authUser => {
                this._authStatus.set(AuthStatus.AUTHENTICATED);
                this._authUser.set(authUser);
                return true;
            }),
            catchError((error: HttpErrorResponse) => throwError(() => error))
        )
    }
}
