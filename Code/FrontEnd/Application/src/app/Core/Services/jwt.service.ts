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

  constructor() {
    this.validateStoredToken();
  }

  private validateStoredToken(): void {
    const token = this.getToken();
    if (token && this.isTokenExpired()) {
      this.removeToken();
    }
  }

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  decodeToken(token: string): DecodedToken | null {
    try {
      return jwtDecode(token);
    } catch (error) {
      console.error('Error decoding token:', error);
      this.removeToken();
      return null;
    }
  }

  getTokenExpirationTime(): number | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const decoded = this.decodeToken(token);
      return decoded?.exp || null;
    } catch (error) {
      console.error('Error getting token expiration time:', error);
      return null;
    }
  }

  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) return true;

    try {
      const decoded = this.decodeToken(token);
      if (!decoded) return true;

      const currentTime = Date.now() / 1000;
      return decoded.exp < currentTime;
    } catch (error) {
      console.error('Error checking token expiration:', error);
      return true;
    }
  }

  getUserInfo(): { username: string; authorities: string[] } | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const decoded = this.decodeToken(token);
      if (!decoded) return null;

      return {
        username: decoded.sub,
        authorities: decoded.authorities.split(' '),
      };
    } catch (error) {
      console.error('Error getting user info:', error);
      return null;
    }
  }
}
