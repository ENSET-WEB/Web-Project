import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../Core/services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  userInfo: {
    username: string;
    authorities: string[];
  } | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.userInfo = this.authService.getUserInfo();
  }

  getFormattedRole(role: string): string {
    return role.charAt(0) + role.slice(1).toLowerCase();
  }
}
