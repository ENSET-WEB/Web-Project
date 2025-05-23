import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../Core/services/auth.service';
import { catchError, finalize, of } from 'rxjs';
import { IAppUser } from '../../../Core/interface/iapp-user';

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
  authSuccess: boolean = false;
  userInfo: IAppUser | null = null;
  constructor(private authService: AuthService) {}

  handleLogin() {
    if (this.isLoading) return;
    if (this.loginData.username === "" || this.loginData.password === "") {
      this.error = "Please enter a username and password";
      return;
    }

    this.error = '';
    this.isLoading = true;

    this.authService
      .login(this.loginData.username, this.loginData.password)
      .pipe(
        catchError((error) => {
          console.log(error);
          this.error = "Invalid username or password";
          return of(null);
        }),
        finalize(() => {
          this.isLoading = false;
        }))
      .subscribe((response) => {
        console.log(response);
        if (response) {
          this.userInfo = this.authService.getUserInfo();
          console.log('Logged in user:', this.userInfo);
          this.authSuccess = true;
        }
      });
  }
}
