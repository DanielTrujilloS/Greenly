import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Donacion, Donante, Campania, TipoDonacion, EstadoDonacion } from './donacion.model';

@Injectable({ providedIn: 'root' })
export class DonacionesService {
  private apiUrl = 'http://localhost:8080/Greenly/donaciones';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Donacion[]> {
    return this.http.get<Donacion[]>(`${this.apiUrl}/listar`);
  }
  getById(id: number): Observable<Donacion> {
    return this.http.get<Donacion>(`${this.apiUrl}/obtener/${id}`);
  }
  create(data: Donacion): Observable<Donacion> {
    return this.http.post<Donacion>(`${this.apiUrl}/agregar`, data);
  }
  update(id: number, data: Donacion): Observable<Donacion> {
    return this.http.put<Donacion>(`${this.apiUrl}/modificar/${id}`, data);
  }
  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/eliminar/${id}`);
  }

  // Métodos para cargar combos
  listarDonantes(): Observable<Donante[]> {
    return this.http.get<Donante[]>('http://localhost:8080/Greenly/donantes/listar');
  }
  listarCampanias(): Observable<Campania[]> {
    return this.http.get<Campania[]>('http://localhost:8080/Greenly/campanias/listar');
  }
  listarTiposDonacion(): Observable<TipoDonacion[]> {
    return this.http.get<TipoDonacion[]>('http://localhost:8080/Greenly/tipodonacion/listar');
  }
  listarEstadosDonacion(): Observable<EstadoDonacion[]> {
    return this.http.get<EstadoDonacion[]>('http://localhost:8080/Greenly/estadodonacion/listar');
  }
}
