import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CampañasService {
  private apiUrl = 'http://localhost:8080/Greenly/campañas';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/listar`);
  }
  getById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/obtener/${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/agregar`, data);
  }
  update(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/modificar/${id}`, data);
  }
  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }
  buscarPorDescripcionYFecha(texto: string, fecha: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/descripcion-fecha?texto=${texto}&fecha=${fecha}`);
  }
  
  buscarPorTituloODescripcion(texto: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/buscar?texto=${texto}`);
  }
  
  getPorRangoFechas(fechaInicio: string, fechaFin: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/rango-fechas?fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`);
  }
  
  getCampañasPorOng(ongId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/obtenerCampañasPorOng/${ongId}`);
  }
  
}
