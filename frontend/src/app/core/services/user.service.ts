import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
  private apiUrl = 'http://localhost:8080/Greenly/usuarios';

  constructor(private http: HttpClient) {}

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/obtener/${id}`);
  }

  // Puedes agregar aquí métodos para actualizar perfil, cambiar password, etc.
}
