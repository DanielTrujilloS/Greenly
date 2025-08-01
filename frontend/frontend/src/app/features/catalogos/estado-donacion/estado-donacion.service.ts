import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EstadoDonacionDTO } from './estado-donacion.model';

@Injectable({ providedIn: 'root' })
export class EstadoDonacionService {
  private apiUrl = 'http://localhost:8080/Greenly/estadoDonaciones';

  constructor(private http: HttpClient) {}

  getAll(): Observable<EstadoDonacionDTO[]> {
    return this.http.get<EstadoDonacionDTO[]>(`${this.apiUrl}/listar`);
  }

  getById(id: number): Observable<EstadoDonacionDTO> {
    return this.http.get<EstadoDonacionDTO>(`${this.apiUrl}/obtener/${id}`);
  }

  create(data: EstadoDonacionDTO): Observable<EstadoDonacionDTO> {
    return this.http.post<EstadoDonacionDTO>(`${this.apiUrl}/agregar`, data);
  }

  update(id: number, data: EstadoDonacionDTO): Observable<EstadoDonacionDTO> {
    return this.http.put<EstadoDonacionDTO>(`${this.apiUrl}/modificar/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
