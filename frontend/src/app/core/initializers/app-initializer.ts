import { inject } from "@angular/core";
import { forkJoin, map, Observable } from "rxjs";
import { AuthService } from "../../services/auth/auth.service";

export const combinedInitializer = (): Observable<boolean> => {
  const authService = inject(AuthService);
  return forkJoin([
    authService.checkAuthStatus()
  ]).pipe(
    map(results => results.every(result => result === true))
  );
};