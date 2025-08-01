import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ComentarioDTO } from './comentario.model';

@Injectable({ providedIn: 'root' })
export class ComentariosService {
  private apiUrl = 'http://localhost:8080/Greenly/comentarios';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ComentarioDTO[]> {
    return this.http.get<ComentarioDTO[]>(`${this.apiUrl}/listar`);
  }

  getById(id: number): Observable<ComentarioDTO> {
    return this.http.get<ComentarioDTO>(`${this.apiUrl}/obtener/${id}`);
  }

  create(data: ComentarioDTO): Observable<ComentarioDTO> {
    return this.http.post<ComentarioDTO>(`${this.apiUrl}/agregar`, data);
  }

  update(id: number, data: ComentarioDTO): Observable<ComentarioDTO> {
    return this.http.put<ComentarioDTO>(`${this.apiUrl}/modificar/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
