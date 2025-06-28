import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    const user = authService.user();
    if (!user) {
        return router.createUrlTree(['/login']);
    }

    const allowedRoles: string[] = route.data?.['roles'] || [];

    if (allowedRoles.includes(<string>user.role?.name)) {
        return true;
    }

    return router.createUrlTree(['/auth/access']);
};
