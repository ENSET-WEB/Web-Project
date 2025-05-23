import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl = environment.apiUrl;
  private registerUrl = `${this.apiUrl}/appUser/addAppUser`;

  constructor(private http: HttpClient,) { }

  register(user: any) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response' as const
    };

    return this.http.post(this.registerUrl, user, options);
  }
}
