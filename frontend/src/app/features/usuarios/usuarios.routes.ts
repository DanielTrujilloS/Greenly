import { Routes } from '@angular/router';
import { UsuarioListComponent } from './usuario-list/usuario-list';
import { UsuarioFormComponent } from './usuario-form/usuario-form';
import { UsuarioDetailComponent } from './usuario-detail/usuario-detail';

export const USUARIOS_ROUTES: Routes = [
  { path: '', component: UsuarioListComponent },
  { path: 'nuevo', component: UsuarioFormComponent },
  { path: ':id/editar', component: UsuarioFormComponent },
  { path: ':id', component: UsuarioDetailComponent },
];
