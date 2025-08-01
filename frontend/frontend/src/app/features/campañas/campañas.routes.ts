import { Routes } from '@angular/router';
import { CampañasFormComponent } from './campania-form/campania-form';
import { CampañasDetailComponent } from './campania-detail/campania-detail';
import { CampañasListComponent } from './campania-list/campania-list';

export const CAMPANAS_ROUTES: Routes = [
  { path: '', component: CampañasListComponent },
  { path: 'nuevo', component: CampañasFormComponent },
  { path: ':id/editar', component: CampañasFormComponent },
  { path: ':id', component: CampañasDetailComponent },
];
