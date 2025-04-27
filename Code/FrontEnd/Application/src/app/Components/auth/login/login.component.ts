import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../Core/services/auth.service';
import { catchError, finalize, of } from 'rxjs';

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

  constructor(private authService: AuthService) {}

  handleLogin() {
    if (this.isLoading) return;

    this.error = '';
    this.isLoading = true;

    this.authService
      .login(this.loginData.username, this.loginData.password)
      .pipe(
        catchError((error) => {
          this.error = error.error?.message || 'An error occurred during login';
          return of(null);
        }),
        finalize(() => {
          this.isLoading = false;
        }))
      .subscribe((response) => {
        if (response) {
          const userInfo = this.authService.getUserInfo();
          console.log('Logged in user:', userInfo);
        }
      });
  }
}
