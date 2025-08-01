// src/app/core/interceptors/token-interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { JwtHelper } from '../utilis/jwt-helper'; // ajusta el path si varía

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  // ── 1. Detectar rutas públicas ───────────────────────────────────────────────
  const isAuthRoute = /\/usuarios\/(login|agregar|register)/.test(req.url);

  // ── 2. Gestionar el token ────────────────────────────────────────────────────
  const token = localStorage.getItem('jwtToken');

  // a) Si existe y está EXPIRADO → lo borro para no enviarlo nunca más
  if (token && JwtHelper.isTokenExpired(token)) {
    localStorage.removeItem('jwtToken');
  }

  // b) Si NO es ruta pública y el token sigue válido → lo adjunto al header
  if (!isAuthRoute && token && !JwtHelper.isTokenExpired(token)) {
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` },
    });
  }

  return next(req);
};
