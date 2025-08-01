import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { OngsService } from '../ongs.service';
import { UsuariosService } from '../../usuarios/usuarios.service';


@Component({
  selector: 'app-ong-form',
  templateUrl: './ong-form.html',
  styleUrl: './ong-form.css',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule
  ]
})
export class OngFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;
  usuarios: any[] = [];

  constructor(
    private fb: FormBuilder,
    private ongsService: OngsService,
    private usuariosService: UsuariosService,
    private route: ActivatedRoute,
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

    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.ongsService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar la ONG')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      this.ongsService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/ongs']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.ongsService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/ongs']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }
}
