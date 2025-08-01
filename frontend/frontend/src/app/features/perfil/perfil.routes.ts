import { Routes } from '@angular/router';
import { PerfilViewComponent } from './perfil-view/perfil-view';
import { PerfilEditComponent } from './perfil-edit/perfil-edit';

export const PERFIL_ROUTES: Routes = [
  { path: '', component: PerfilViewComponent },         
  { path: 'editar', component: PerfilEditComponent },   
];

export default PERFIL_ROUTES;
