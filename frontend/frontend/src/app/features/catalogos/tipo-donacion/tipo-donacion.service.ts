import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoDonacionDTO } from './tipo-donacion.model';

@Injectable({ providedIn: 'root' })
export class TipoDonacionService {
  private apiUrl = 'http://localhost:8080/Greenly/tipoDonaciones';

  constructor(private http: HttpClient) {}

  getAll(): Observable<TipoDonacionDTO[]> {
    return this.http.get<TipoDonacionDTO[]>(`${this.apiUrl}/listar`);
  }

  getById(id: number): Observable<TipoDonacionDTO> {
    return this.http.get<TipoDonacionDTO>(`${this.apiUrl}/obtener/${id}`);
  }

  create(data: TipoDonacionDTO): Observable<TipoDonacionDTO> {
    return this.http.post<TipoDonacionDTO>(`${this.apiUrl}/agregar`, data);
  }

  update(id: number, data: TipoDonacionDTO): Observable<TipoDonacionDTO> {
    return this.http.put<TipoDonacionDTO>(`${this.apiUrl}/modificar/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
