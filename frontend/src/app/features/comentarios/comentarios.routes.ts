import { Routes } from '@angular/router';
import { ComentarioListComponent } from './comentario-list/comentario-list';
import { ComentarioFormComponent } from './comentario-form/comentario-form';
import { ComentarioDetailComponent } from './comentario-detail/comentario-detail';

export const COMENTARIOS_ROUTES: Routes = [
  { path: '', component: ComentarioListComponent },
  { path: 'nuevo', component: ComentarioFormComponent },
  { path: ':id/editar', component: ComentarioFormComponent },
  { path: ':id', component: ComentarioDetailComponent },
];

export default COMENTARIOS_ROUTES;
