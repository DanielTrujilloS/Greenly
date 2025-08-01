import { Routes } from '@angular/router';
import { DonanteListComponent } from './donante-list/donante-list';
import { DonanteFormComponent } from './donante-form/donante-form';
import { DonanteDetailComponent } from './donante-detail/donante-detail';

export const DONANTES_ROUTES: Routes = [
  { path: '', component: DonanteListComponent },
  { path: 'nuevo', component: DonanteFormComponent },
  { path: ':id/editar', component: DonanteFormComponent },
  { path: ':id', component: DonanteDetailComponent },
];

export default DONANTES_ROUTES;
