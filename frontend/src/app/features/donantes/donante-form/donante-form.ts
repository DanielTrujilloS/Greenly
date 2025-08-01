import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { DonantesService } from '../donantes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-donante-form',
  standalone: true,
  templateUrl: './donante-form.html',
  styleUrl: './donante-form.css',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    CommonModule
  ]
})
export class DonanteFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private donantesService: DonantesService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      dni: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      direccion: [''],
      fechaNacimiento: ['', Validators.required],
      usuarioId: [null] // Puedes autogenerar o permitir seleccionar (opcional)
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.donantesService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar el donante')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    // Puedes poner lógica para autogenerar usuarioId aquí si quieres (random, mock, etc)
    if (!data.usuarioId) data.usuarioId = 1; // ejemplo: asignar usuarioId=1 por defecto
    if (this.isEdit && this.id) {
      this.donantesService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/donantes']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.donantesService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/donantes']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }
}
