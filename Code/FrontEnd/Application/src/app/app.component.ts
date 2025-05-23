import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AuthService } from './Core/services/auth.service';
import { ReactiveFormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HeaderComponent,
    FooterComponent,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'RockShop';
  logoutConfirmation: boolean = false;
  isAuthenticated: boolean = false;

  constructor(public authService: AuthService) { }

  ngOnInit(): void {
    console.log('Welcome to RockShop!');
    this.isAuthenticated = this.authService.isAuthenticated();
    if (this.isAuthenticated) {
      this.authService.logoutConfirmation$.subscribe((confirmation) => { this.logoutConfirmation = confirmation })
    }
  }

  cancelLogout() {
    this.authService.confirmateLogout(false)
  }

  handleLogout() {
    this.authService.logout()
    this.logoutConfirmation = false
  }

}
