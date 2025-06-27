import { inject } from '@angular/core';
import { CanMatchFn } from '@angular/router';
import {AuthService} from "../auth.service";

export const roleGuard: CanMatchFn = (route, segments) => {

    const authService = inject(AuthService);
    const user: any = authService.user();
    if (!user) return false;
    const allowedRole: string = route.data?.["role"];
    if (user.role.name === allowedRole) {
        return true;
    }
    return true;
};
