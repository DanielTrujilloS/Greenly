import { Routes } from '@angular/router';
import { VistaCatalogo } from './vista-catalogo/vista-catalogo';
import { TipoDonacionFormComponent } from './tipo-donacion/tipo-donacion-form/tipo-donacion-form';
import { EstadoDonacionFormComponent } from './estado-donacion/estado-donacion-form/estado-donacion-form';
import { UbicacionCampaniaFormComponent } from './ubicacion/ubicacion-campania-form/ubicacion-campania-form';

export const CATALOGOS_ROUTES: Routes = [
  { path: 'vista-catalogo/tipos-donacion/nuevo', component: TipoDonacionFormComponent},
  { path: 'vista-catalogo/tipos-donacion/:id/editar', component: TipoDonacionFormComponent},
  { path: 'vista-catalogo/estados-donacion/nuevo', component: EstadoDonacionFormComponent},
  { path: 'vista-catalogo/estados-donacion/:id/editar', component: EstadoDonacionFormComponent},
  { path: 'vista-catalogo/ubicaciones/nuevo', component: UbicacionCampaniaFormComponent},
  { path: 'vista-catalogo/ubicaciones/:id/editar', component: UbicacionCampaniaFormComponent},
  { path: 'vista-catalogo', component: VistaCatalogo},
  { path: '', redirectTo: 'vista-catalogo', pathMatch: 'full' }
];

export default CATALOGOS_ROUTES;
