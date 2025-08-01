import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OngDTO } from './ong.model';

@Injectable({ providedIn: 'root' })
export class OngsService {
  private apiUrl = 'http://localhost:8080/Greenly/ongs';

  constructor(private http: HttpClient) {}

  getAll(): Observable<OngDTO[]> {
    return this.http.get<OngDTO[]>(`${this.apiUrl}/listar`);
  }
  getById(id: number): Observable<OngDTO> {
    return this.http.get<OngDTO>(`${this.apiUrl}/obtener/${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/agregar`, data);
  }
  update(id: number, data: OngDTO): Observable<OngDTO> {
    return this.http.put<OngDTO>(`${this.apiUrl}/modificar/${id}`, data);
  }
  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
