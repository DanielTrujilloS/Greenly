import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsuariosService } from '../usuarios.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  templateUrl: './usuario-form.html',
  styleUrl: './usuario-form.css',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatOptionModule
  ]
})
export class UsuarioFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private usuariosService: UsuariosService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required], // Solo para crear
      authorities: ['', Validators.required],
      enabled: [true, Validators.required],
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.form.get('password')?.clearValidators();
      this.form.get('password')?.updateValueAndValidity();
      this.usuariosService.getById(this.id!).subscribe({
        next: (data) => {
          this.form.patchValue(data);
          this.form.get('password')?.setValue('');
        },
        error: () => alert('No se pudo cargar el usuario')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      // No enviar password si está vacío al editar
      if (!data.password) {
        delete data.password;
      }
      this.usuariosService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/usuarios']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.usuariosService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/usuarios']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }
}
