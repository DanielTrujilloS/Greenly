import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.html',
  styleUrls: ['./login.scss'],
  providers: [],
  imports: [ReactiveFormsModule, NgIf, RouterLink],
})
export class LoginComponent {
  loginForm: FormGroup;
  error: string | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.error = null;
    if (this.loginForm.invalid) {
      this.error = 'Debes ingresar usuario y contraseña.';
      return;
    }
    this.loading = true;
    const { username, password } = this.loginForm.value;
    this.authService.login(username, password).subscribe({
      next: (response) => {
        this.authService.saveToken(response.jwtToken);
        this.router.navigate(['/campañas']); // redirección post login
      },
      error: (err) => {
        this.error = 'Usuario o contraseña incorrectos';
        this.loading = false;
      },
    });
  }
}
