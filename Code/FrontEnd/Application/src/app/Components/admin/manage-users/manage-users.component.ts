import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, of } from 'rxjs';
import { AppUserService } from '../../../Core/services/app-user.service';
import { AuthService } from '../../../Core/services/auth.service';
import { IAppUser } from '../../../Core/interface/iapp-user';

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {
  users: any[] = [];
  isLoading = false;
  errorMessage = '';
  successMessage = '';
  showAddModal = false;
  showEditModal = false;
  selectedUser: any | null = null;
  userForm: FormGroup;
  searchQuery = '';
  appUserInfo: IAppUser | null = null;

  constructor(private fb: FormBuilder, private appUserService: AppUserService, private authService: AuthService) {
    this.userForm = this.fb.group({
      name: "",
      email: "",
      role: "",
      status: ""
    });
  }

  ngOnInit(): void {
    this.loadUsers();
    this.appUserInfo = this.authService.getUserInfo()
  }

  loadUsers() {
    this.appUserService.getAllAppUsers().pipe(
      catchError((err) => {
        this.errorMessage = "Error fetching app users" + err
        console.log(this.errorMessage);
        return of([])

      })
    )
      .subscribe((resp: any) => {
        this.users = resp
        console.log(resp);

      })
  }

  openAddModal() {
    this.showAddModal = true;
    this.userForm.reset();
  }

  openEditModal(user: any) {
    this.selectedUser = user;
    this.userForm.patchValue(user);
    this.showEditModal = true;
  }

  closeModal() {
    this.showAddModal = false;
    this.showEditModal = false;
    this.selectedUser = null;
    this.userForm.reset();
  }

  deleteUser(userId: string) {
    
  }

  clearMessages() {
    this.errorMessage = '';
    this.successMessage = '';
  }

  applyFilterOptions() {
    // To be implemented
  }
}
