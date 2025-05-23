import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../Core/services/auth.service';
import { RegistrationService } from '../../../Core/services/registration.service';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  username: string = "";
  email: string = "";
  password: string = "";
  registrationSuccess: boolean = false;
  error : string = "";

  constructor(private registrationService: RegistrationService, private router: Router) { }

  onSubmit() {
    const user = {
      name: this.username,
      email: this.email,
      password: this.password
    };
    this.registrationService.register(user).pipe(
      catchError((err) => {
        this.error = "Error creating a new user"
        return of()
      })
    )
    .subscribe((response) => {
      if (response.status === 201) {
        this.registrationSuccess = true
        this.router.navigate(['/login']);
      }
    });
  }
}
