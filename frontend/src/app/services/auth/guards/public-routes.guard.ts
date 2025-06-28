import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { AuthStatus } from '../../../models/models';

export const publicRoutesGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  if (authService.authStatus() === AuthStatus.NOT_AUTHENTICATED) return true;
  const router = inject(Router);
  return router.createUrlTree(['/']);
};
