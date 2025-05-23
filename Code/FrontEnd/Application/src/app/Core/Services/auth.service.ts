import { Injectable, ɵDeferBlockConfig } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { JwtService } from './jwt.service';
import { IAppUser } from '../interface/iapp-user';
import { IAppRole } from '../interface/iapp-role';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private loginUrl = `${this.apiUrl}/auth/login`;
  private passwordChangeUrl = `${this.apiUrl}/appUser/changePassword`;

  private logoutConfirmationMessage = new BehaviorSubject<boolean>(false);
  logoutConfirmation$ = this.logoutConfirmationMessage.asObservable()

  confirmateLogout(confirm: boolean) {
    this.logoutConfirmationMessage.next(confirm)
  }

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
    this.jwtService.removeToken();

    const options = {
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
          setTimeout(() => {
            this.router.navigate(['/']);
          }, 1000);
        }
      })
    );
  }

  public logout(): void {
    console.log('User Logged out: ', this.getUserInfo());

    this.jwtService.removeToken();
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
    return !this.jwtService.isTokenExpired();
  }

  public getUserInfo() {
    return this.jwtService.getUserInfo();
  }

  public updateUserPassword(passwordUpdateDTO: any) {
    return this.http.post<any>(this.passwordChangeUrl, passwordUpdateDTO);
  }

  public isAdmin(): boolean {
    const userInfo = this.getUserInfo()
    const appRoles: IAppRole[] | undefined = userInfo?.appRoles;

    if (appRoles?.map(role => role.appRoleName).includes("ADMIN")) {
      return true
    }
    return false
  }
}
