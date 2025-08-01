import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario.model';

@Injectable({ providedIn: 'root' })
export class UsuariosService {
  private apiUrl = 'http://localhost:8080/Greenly/usuarios';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiUrl}/listar`);
  }
  getById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/obtener/${id}`);
  }
  create(data: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.apiUrl}/agregar`, data);
  }
  update(id: number, data: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/modificar/${id}`, data);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/eliminar/${id}`);
  }
}
