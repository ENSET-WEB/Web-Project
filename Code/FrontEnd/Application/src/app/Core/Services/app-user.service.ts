import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AppUserService {
  private apiUrl = environment.apiUrl;
  private appUsersUrl = `${this.apiUrl}/appUser`;

  constructor(private authService: AuthService, private http: HttpClient) {}

  getAllAppUsers() {
    return this.http.get(`${this.appUsersUrl}/managed`);
  }

  deleteAppUser(id: string) {
    return this.http.delete(`${this.appUsersUrl}/${id}`);
  }
}
