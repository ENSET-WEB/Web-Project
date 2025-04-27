import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

interface DecodedToken {
  sub: string;
  exp: number;
  iat: number;
  authorities: string;
}

@Injectable({
  providedIn: 'root',
})
export class JwtService {
  private readonly TOKEN_KEY = 'access_token';

  constructor() {}

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  decodeToken(token: string): DecodedToken {
    return jwtDecode(token);
  }

  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) return true;

    const decoded = this.decodeToken(token);
    const currentTime = Date.now() / 1000;
    return decoded.exp < currentTime;
  }

  getUserInfo(): { username: string; authorities: string[] } | null {
    const token = this.getToken();
    if (!token) return null;

    const decoded = this.decodeToken(token);
    return {
      username: decoded.sub,
      authorities: decoded.authorities.split(' '),
    };
  }
}
