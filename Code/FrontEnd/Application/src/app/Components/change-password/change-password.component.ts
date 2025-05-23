import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../Core/services/auth.service';
import { IAppUser } from '../../Core/interface/iapp-user';
import { catchError, of, timeout } from 'rxjs';
import { Route, Router } from '@angular/router';

@Component({
    selector: 'app-change-password',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
    changePasswordForm: FormGroup;
    isLoading = false;
    errorMessage = '';
    successMessage = '';
    userInfo: IAppUser | null = null;

    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private route: Router
    ) {
        this.changePasswordForm = this.fb.group({
            currentPassword: "",
            newPassword: "",
            confirmPassword: ""
        },);
    }

    ngOnInit(): void {
        this.userInfo = this.authService.getUserInfo()
    }

    onSubmit() {
        if (this.changePasswordForm.valid) {
            this.isLoading = true;
            this.errorMessage = '';
            this.successMessage = '';

            const { currentPassword, newPassword } = this.changePasswordForm.value;

            const body = {
                id: this.userInfo?.id,
                newPassword: newPassword,
                oldPassword: currentPassword
            }

            this.authService.updateUserPassword(body).pipe(catchError((err) => {
                this.errorMessage = "Error Changing password, Password incorrect"
                console.log(err);
                return of(null)
            }
            )).subscribe((resp) => {
                this.isLoading = false
                if (resp.status !== 500) {
                    this.successMessage = 'Password changed successfully!';
                    this.changePasswordForm.reset()
                    setTimeout(() => {
                        this.authService.logout()
                        this.route.navigate(["/"])
                    }, 1000);
                    
                }

            })

        
        }
    }

    getErrorMessage(controlName: string): string {
        const control = this.changePasswordForm.get(controlName);
        if (control?.hasError('required')) {
            return 'This field is required';
        }
        if (control?.hasError('minlength')) {
            return 'Password must be at least 6 characters';
        }
        if (control?.hasError('passwordMismatch')) {
            return 'Passwords do not match';
        }
        return '';
    }
} 