import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../Core/services/auth.service';
import { catchError, finalize, of } from 'rxjs';
import { JwtService } from '../../../Core/services/jwt.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginData = {
    username: '',
    password: '',
  };
  error: String = '';
  isLoading: boolean = false;
  constructor(
    private authService: AuthService,
    private jwtService: JwtService
  ) {}

  handleLogin() {
    this.isLoading = true;
    let username = this.loginData.username;
    let password = this.loginData.password;

    this.authService
      .login(username, password)
      .pipe(
        catchError((error) => {
          this.error = error;
          console.error(error);
          return of([]);
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe((response) => {
        if (response) {
          const userInfo = this.authService.getUserInfo();
          console.log('Logged in user: ', userInfo);
        }
      });
  }
}

// this.authService
//   .login(username, password)
//   .pipe(
//     catchError((error) => {
//       this.error = error;
//       console.error(error);
//       return of([]);
//     }),
//     finalize(() => {
//       this.isLoading = true;
//     })
//   )
//   .subscribe((accessToken) => {
//     console.log(accessToken);
//     console.log(jwtDecode(accessToken['access_token']));
//   });
