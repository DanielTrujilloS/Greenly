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
  selector: 'app-register',
  imports: [ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['./register.scss'],
})
export class RegisterComponent {
  registerForm: FormGroup;
  error: string | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      authorities: ['', Validators.required], // ejemplo: 'ROLE_DONANTE' o 'ROLE_ONGADMIN'
    });
  }

  onSubmit() {
    this.error = null;

    if (this.registerForm.invalid) {
      this.error = 'Completa todos los campos.';
      return;
    }

    this.loading = true;
    const { username, password, authorities } = this.registerForm.value;

    this.authService.register(username, password, authorities).subscribe({
      next: (response) => {
        this.authService.saveToken(response.jwtToken);
        this.router.navigate(['/campañas']); // redirección post registro
      },
      error: (err) => {
        this.error = 'Error al registrarte. Verifica los datos.';
        this.loading = false;
      },
    });
  }
}
