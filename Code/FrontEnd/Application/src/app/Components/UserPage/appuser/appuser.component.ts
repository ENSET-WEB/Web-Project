import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-appuser',
  imports: [CommonModule, FormsModule],
  templateUrl: './appuser.component.html',
  styleUrls: ['./appuser.component.css'],
})
export class AppUserComponent implements OnInit {
  user = {
    username: '',
    email: '',
  };

  constructor() {}

  ngOnInit(): void {
    // TODO: Fetch user data from service
  }

  onUpdateProfile(): void {
    // TODO: Implement profile update logic
    console.log('Profile update requested');
  }
}
