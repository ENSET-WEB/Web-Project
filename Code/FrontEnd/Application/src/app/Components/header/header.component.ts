import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ThemeService } from '../../Core/services/theme.service';
import { AuthService } from '../../Core/services/auth.service';
import { IAppUser } from '../../Core/interface/iapp-user';

@Component({
  selector: 'app-header',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
// implements OnInit
export class HeaderComponent {
  // appUser: IAppUser | null = null;
  constructor(
    public themeService: ThemeService,
    public authService: AuthService
  ) {}

  // ngOnInit(): void {
  //   if (this.authService.isAuthenticated()) {
  //     this.appUser = this.authService.getUserInfo();
  //   }
  // }

  toggleTheme(): void {
    this.themeService.toggleTheme();
  }

  handleLogout(): void {
    this.authService.logout();
  }
}
