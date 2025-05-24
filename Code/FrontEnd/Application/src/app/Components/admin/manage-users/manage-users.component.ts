import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { catchError, of } from 'rxjs';
import { AppUserService } from '../../../Core/services/app-user.service';
import { AuthService } from '../../../Core/services/auth.service';
import { IAppUser } from '../../../Core/interface/iapp-user';
import { RegistrationService } from '../../../Core/services/registration.service';

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css'],
})
export class ManageUsersComponent implements OnInit {
  users: any[] = [];
  private allUsers: any[] = [];
  isLoading = false;
  errorMessage = '';
  successMessage = '';
  showAddModal = false;
  showEditModal = false;
  selectedUser: any | null = null;
  userForm: FormGroup;
  searchQuery = '';
  appUserInfo: IAppUser | null = null;

  constructor(
    private fb: FormBuilder,
    private appUserService: AppUserService,
    private authService: AuthService,
    private registrationService: RegistrationService
  ) {
    this.userForm = this.fb.group({
      name: '',
      email: '',
      // role: '',
      // status: '',
      password: '',
    });
  }

  ngOnInit(): void {
    this.loadUsers();
    this.appUserInfo = this.authService.getUserInfo();
  }

  loadUsers() {
    this.appUserService
      .getAllAppUsers()
      .pipe(
        catchError((err) => {
          this.errorMessage = 'Error fetching app users' + err;
          console.log(this.errorMessage);
          return of([]);
        })
      )
      .subscribe((resp: any) => {
        this.allUsers = resp;
        this.users = resp;
        console.log(resp);
      });
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
    if (confirm('Are you sure you want to delete this user?')) {
      this.isLoading = true;

      this.appUserService
        .deleteAppUser(userId)
        .pipe(
          catchError((error) => {
            this.errorMessage = 'Failed to delete user: ' + error.message;
            this.isLoading = false;
            return of(null);
          })
        )
        .subscribe((response: any) => {
          this.successMessage = 'User deleted successfully';
          this.isLoading = false;
          this.loadUsers();
        });
    }
  }

  applyFilterOptions() {
    if (!this.searchQuery.trim()) {
      this.users = [...this.allUsers];
      return;
    }

    const query = this.searchQuery.toLowerCase().trim();
    this.users = this.allUsers.filter(
      (user) =>
        user.name?.toLowerCase().includes(query) ||
        user.email?.toLowerCase().includes(query) ||
        user.appRoles?.toString().toLowerCase().includes(query) ||
        user.id?.toLowerCase().includes(query)
    );
  }
  onSubmitNewUser() {
    const user = {
      name: this.userForm.value.name,
      email: this.userForm.value.email,
      password: this.userForm.value.password,
    };
    console.log(user);

    this.registrationService
      .register(user)
      .pipe(
        catchError((err) => {
          this.errorMessage = 'Error creating a new user';
          return of();
        })
      )
      .subscribe((response) => {
        if (response.status === 201) {
          console.log(response);
          this.loadUsers();
        }
      });
  }
}
