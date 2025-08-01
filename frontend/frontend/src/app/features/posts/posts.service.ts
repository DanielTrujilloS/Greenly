import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostDTO } from './post.model';

@Injectable({ providedIn: 'root' })
export class PostsService {
  private apiUrl = 'http://localhost:8080/Greenly/posts';

  constructor(private http: HttpClient) {}

  getAll(): Observable<PostDTO[]> {
    return this.http.get<PostDTO[]>(`${this.apiUrl}/listar`);
  }

  getById(id: number): Observable<PostDTO> {
    return this.http.get<PostDTO>(`${this.apiUrl}/obtener/${id}`);
  }

  create(data: PostDTO): Observable<PostDTO> {
    return this.http.post<PostDTO>(`${this.apiUrl}/agregar`, data);
  }

  update(id: number, data: PostDTO): Observable<PostDTO> {
    return this.http.put<PostDTO>(`${this.apiUrl}/modificar/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
}
