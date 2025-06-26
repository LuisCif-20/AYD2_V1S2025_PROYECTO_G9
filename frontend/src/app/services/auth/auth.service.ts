import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private tokenKey = 'authToken';

    constructor(private router: Router) {}

    login(token: string): void {
        localStorage.setItem(this.tokenKey, token);
    }

    logout(): void {
        localStorage.removeItem(this.tokenKey);
        this.router.navigate(['/auth/login']);
    }

    getToken(): string | null {
        return localStorage.getItem(this.tokenKey);
    }

    isAuthenticated(): boolean {
        /*const token = this.getToken();
        if (!token) return false;

        const payload = this.decodeToken(token);
        if (!payload) return false;

        const expiration = payload.exp;
        const now = Math.floor(Date.now() / 1000);
        return expiration > now;*/
        return !!this.getToken();
    }

    getUserPayload(): any | null {
        const token = this.getToken();
        if (!token) return null;
        return this.decodeToken(token);
    }

    private decodeToken(token: string): any | null {
        try {
            const payload = token.split('.')[1];
            return JSON.parse(atob(payload));
        } catch (e) {
            return null;
        }
    }
}
