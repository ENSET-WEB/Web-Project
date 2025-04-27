import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ThemeService } from '../../Core/services/theme.service';
import { AuthService } from '../../Core/services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  constructor(
    public themeService: ThemeService,
    public authService: AuthService
  ) {}

  toggleTheme(): void {
    this.themeService.toggleTheme();
  }

  handleLogout(): void {
    this.authService.logout();
  }
}
