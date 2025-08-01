import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/Greenly/usuarios';

  constructor(public http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, {
      username,
      password,
    });
  }

  saveToken(token: string): void {
    if (typeof window === 'undefined') return;
    localStorage.setItem('jwtToken', token);
  }

  logout(): void {
    if (typeof window === 'undefined') return;
    localStorage.removeItem('jwtToken');
  }

  isLoggedIn(): boolean {
    if (typeof window === 'undefined') return false;
    return !!localStorage.getItem('jwtToken');
  }

  getDecodedToken(): any {
    if (typeof window === 'undefined') return null;
    const token = localStorage.getItem('jwtToken');
    if (!token) return null;
    try {
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload));
    } catch (e) {
      return null;
    }
  }

  getRoles(): string[] {
    const decoded = this.getDecodedToken();
    return decoded && decoded.authorities ? decoded.authorities : [];
  }

  hasRole(role: string): boolean {
    return this.getRoles().includes(role);
  }

  hasAnyRole(roles: string[]): boolean {
    return this.getRoles().some((role) => roles.includes(role));
  }

  register(
    username: string,
    password: string,
    authorities: string
  ): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, {
      username,
      password,
      authorities,
      enabled: true,
    });
  }
}
