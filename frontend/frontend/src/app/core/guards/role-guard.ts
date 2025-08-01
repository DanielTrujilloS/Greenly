import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router      = inject(Router);

  // Roles que exige la ruta (definidos en data.roles)
  const requiredRoles: string[] = route.data['roles'] ?? [];

  // Si la ruta no exige roles, deja pasar
  if (requiredRoles.length === 0) return true;

  // ¿El usuario posee alguno de los roles requeridos?
  const hasPermission = authService.hasAnyRole(requiredRoles);

  if (!hasPermission) {
    // Redirige o muestra un aviso; aquí lo redirigimos al home
    router.navigate(['/']);
  }

  return hasPermission;
};
