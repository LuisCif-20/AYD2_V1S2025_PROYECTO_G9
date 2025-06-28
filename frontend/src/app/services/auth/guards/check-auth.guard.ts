import { inject } from '@angular/core';
import { CanActivateChildFn, Router } from '@angular/router';
import {AuthService} from "../auth.service";
import {AuthStatus} from "../../../models/models";

export const checkAuthGuard: CanActivateChildFn = (childRoute, state) => {
    const authService = inject(AuthService);
    if (authService.authStatus() === AuthStatus.AUTHENTICATED) return true;
    const router = inject(Router);
    return router.createUrlTree(['/auth']);
};
