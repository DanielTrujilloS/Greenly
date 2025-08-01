import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Donante } from './donante.model';

@Injectable({ providedIn: 'root' })
export class DonantesService {
  private apiUrl = 'http://localhost:8080/Greenly/donantes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Donante[]> {
    return this.http.get<Donante[]>(`${this.apiUrl}/listar`);
  }
  getById(id: number): Observable<Donante> {
    return this.http.get<Donante>(`${this.apiUrl}/obtener/${id}`);
  }
  create(data: Donante): Observable<Donante> {
    return this.http.post<Donante>(`${this.apiUrl}/agregar`, data);
  }
  update(id: number, data: Donante): Observable<Donante> {
    return this.http.put<Donante>(`${this.apiUrl}/modificar/${id}`, data);
  }
  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
