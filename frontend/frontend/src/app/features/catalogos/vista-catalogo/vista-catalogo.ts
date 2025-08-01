import { Component } from '@angular/core';
import { TipoDonacionListComponent } from "../tipo-donacion/tipo-donacion-list/tipo-donacion-list";
import { UbicacionCampaniaListComponent } from "../ubicacion/ubicacion-campania-list/ubicacion-campania-list";
import { EstadoDonacionListComponent } from "../estado-donacion/estado-donacion-list/estado-donacion-list";

@Component({
  selector: 'app-vista-catalogo',
  imports: [TipoDonacionListComponent, UbicacionCampaniaListComponent, EstadoDonacionListComponent],
  templateUrl: './vista-catalogo.html',
  styleUrl: './vista-catalogo.css'
})
export class VistaCatalogo {

}
