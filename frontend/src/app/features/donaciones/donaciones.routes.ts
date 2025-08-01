import { Routes } from '@angular/router';
import { DonacionesListComponent } from './donacion-list/donacion-list';
import { DonacionesDetailComponent } from './donacion-detail/donacion-detail';
import { DonacionFormComponent } from './donacion-form/donacion-form';

export const DONACIONES_ROUTES: Routes = [
  { path: '', component: DonacionesListComponent },
  { path: 'nuevo', component: DonacionFormComponent },
  { path: ':id/editar', component: DonacionFormComponent },
  { path: ':id', component: DonacionesDetailComponent },
];

export default DONACIONES_ROUTES;
