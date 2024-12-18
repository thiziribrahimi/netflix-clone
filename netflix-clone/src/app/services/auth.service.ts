import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:9000/auth/login'; // URL du backend
  private isLoggedIn = false;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<boolean> {
    return this.http.post<{ token: string }>(this.baseUrl, { username, password }).pipe(
      map((response) => {
        if (response && response.token) {
          localStorage.setItem('auth_token', response.token);
          this.isLoggedIn = true;
          return true; // Retourne "true" si la connexion est réussie
        }
        return false; // Retourne "false" sinon
      })
    );
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    this.isLoggedIn = false;
  }

  isAuthenticated(): boolean {
    return this.isLoggedIn;
  }
}
