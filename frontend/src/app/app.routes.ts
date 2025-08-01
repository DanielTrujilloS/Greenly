import { Routes } from '@angular/router';
import { LayoutComponent } from './shared/layout/layout';
import { authGuard } from './core/guards/auth-guard';
import { roleGuard } from './core/guards/role-guard';

export const routes: Routes = [
  // Ruta de login pública
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login').then((m) => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./features/auth/register').then((m) => m.RegisterComponent),
  },
  {
    path: '',
    loadComponent: () =>
      import('./features/landing/index/index').then((m) => m.Index),
  },
  {
    path: 'about',
    loadComponent: () =>
      import('./features/landing/about/about').then((m) => m.About),
  },
  {
    path: 'contact',
    loadComponent: () =>
      import('./features/landing/contact/contact').then((m) => m.Contact),
  },
  // Rutas protegidas (todo lo demás)
  {
    path: '',
    component: LayoutComponent,
    canActivate: [authGuard], // <-- Protección global
    children: [
      {
        path: 'campañas',
        loadChildren: () =>
          import('./features/campañas/campañas.routes').then(
            (m) => m.CAMPANAS_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN', 'ROLE_DONANTE'] },
      },
      {
        path: 'donaciones',
        loadChildren: () =>
          import('./features/donaciones/donaciones.routes').then(
            (m) => m.DONACIONES_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN', 'ROLE_DONANTE'] },
      },
      {
        path: 'donantes',
        loadChildren: () =>
          import('./features/donantes/donantes.routes').then(
            (m) => m.DONANTES_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN'] },
      },
      {
        path: 'ongs',
        loadChildren: () =>
          import('./features/ongs/ongs.routes').then((m) => m.ONGS_ROUTES),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN', 'ROLE_DONANTE'] },
      },
      {
        path: 'posts',
        loadChildren: () =>
          import('./features/posts/posts.routes').then((m) => m.POSTS_ROUTES),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN', 'ROLE_DONANTE'] },
      },
      {
        path: 'comentarios',
        loadChildren: () =>
          import('./features/comentarios/comentarios.routes').then(
            (m) => m.COMENTARIOS_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN', 'ROLE_DONANTE'] },
      },
      {
        path: 'catalogos',
        loadChildren: () =>
          import('./features/catalogos/catalogos.routes').then(
            (m) => m.CATALOGOS_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN'] },
      },
      {
        path: 'usuarios',
        loadChildren: () =>
          import('./features/usuarios/usuarios.routes').then(
            (m) => m.USUARIOS_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN'] },
      },
      {
        path: 'perfil',
        loadChildren: () =>
          import('./features/perfil/perfil.routes').then(
            (m) => m.PERFIL_ROUTES
          ),
        canActivate: [roleGuard],
        data: { roles: ['ROLE_ONGADMIN', 'ROLE_ADMIN', 'ROLE_DONANTE'] },
      },
      { path: '', redirectTo: 'campañas', pathMatch: 'full' },
      { path: '**', redirectTo: 'campañas' },
    ],
  },

  { path: '**', redirectTo: 'login' },
];
