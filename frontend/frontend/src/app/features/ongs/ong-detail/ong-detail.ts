import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { OngsService } from '../ongs.service';
import { UsuariosService } from '../../usuarios/usuarios.service';


@Component({
  selector: 'app-ong-detail',
  standalone: true,
  templateUrl: './ong-detail.html',
  styleUrl: './ong-detail.css',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule
  ]
})
export class OngDetailComponent implements OnInit {
  form: FormGroup;
  loading = false;
  error = '';
  usuarios: any[] = [];

  constructor(
    private fb: FormBuilder,
    private ongsService: OngsService,
    private usuariosService: UsuariosService,
    private router: Router
  ) {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      direccion: ['', Validators.required],
      telefono: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.usuariosService.getAll().subscribe({
      next: users => this.usuarios = users,
      error: () => this.usuarios = []
    });
  }

  onSubmit() {
    if (this.form.invalid) return;
    this.loading = true;
    this.ongsService.create(this.form.value).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/ongs']);
      },
      error: () => {
        this.loading = false;
        this.error = 'No se pudo registrar la ONG';
      }
    });
  }
}
