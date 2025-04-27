import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { JwtService } from './jwt.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private loginUrl = `${this.apiUrl}/auth/login`;

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtService: JwtService
  ) {
    this.validateAuthenticationState();
  }

  private validateAuthenticationState(): void {
    if (this.jwtService.isTokenExpired()) {
      this.logout();
    }
  }

  public login(username: string, password: string): Observable<any> {
    // If there's an existing token, remove it before attempting new login
    this.jwtService.removeToken();

    let options = {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/x-www-form-urlencoded'
      ),
    };

    const params = new HttpParams()
      .set('username', username)
      .set('password', password);

    return this.http.post(this.loginUrl, params, options).pipe(
      tap((response: any) => {
        if (response) {
          const token = response['access_token'];
          this.jwtService.setToken(token);
          this.router.navigate(['/']);
        }
      })
    );
  }

  public logout(): void {
    console.log('User Logged out: ', this.getUserInfo());

    this.jwtService.removeToken();
    this.router.navigate(['/login']);
  }

  public isAuthenticated(): boolean {
    return !this.jwtService.isTokenExpired();
  }

  public getUserInfo() {
    return this.jwtService.getUserInfo();
  }
}
