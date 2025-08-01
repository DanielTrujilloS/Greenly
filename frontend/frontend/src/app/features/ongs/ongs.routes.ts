import { Routes } from '@angular/router';
import { OngListComponent } from './ong-list/ong-list';
import { OngFormComponent } from './ong-form/ong-form';
import { OngDetailComponent } from './ong-detail/ong-detail';

export const ONGS_ROUTES: Routes = [
  { path: '', component: OngListComponent },
  { path: 'nuevo', component: OngFormComponent },
  { path: ':id/editar', component: OngFormComponent },
  { path: ':id', component: OngDetailComponent },
];

export default ONGS_ROUTES;
