import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UbicacionCampaniaDTO } from './ubicacion-campania.model';

@Injectable({ providedIn: 'root' })
export class UbicacionCampaniaService {
  private apiUrl = 'http://localhost:8080/Greenly/ubicaciones-campaña';

  constructor(private http: HttpClient) {}

  getAll(): Observable<UbicacionCampaniaDTO[]> {
    return this.http.get<UbicacionCampaniaDTO[]>(`${this.apiUrl}/listar`);
  }

  getById(id: number): Observable<UbicacionCampaniaDTO> {
    return this.http.get<UbicacionCampaniaDTO>(`${this.apiUrl}/obtener/${id}`);
  }

  create(data: UbicacionCampaniaDTO): Observable<UbicacionCampaniaDTO> {
    return this.http.post<UbicacionCampaniaDTO>(`${this.apiUrl}/agregar`, data);
  }

  update(id: number, data: UbicacionCampaniaDTO): Observable<UbicacionCampaniaDTO> {
    return this.http.put<UbicacionCampaniaDTO>(`${this.apiUrl}/modificar/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
