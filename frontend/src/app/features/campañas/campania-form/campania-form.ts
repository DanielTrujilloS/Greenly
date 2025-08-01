import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CampañasService } from '../campañas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-campania-form',
  templateUrl: './campania-form.html',
  styleUrl: './campania-form.css',
  // Si usas standalone, agrega imports aquí
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule,MatCardModule]
})
export class CampañasFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private campañasService: CampañasService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      fechaInicio: ['', Validators.required],
      fechaFin: ['', Validators.required],

    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.campañasService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar la campaña')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      this.campañasService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/campañas']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.campañasService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/campañas']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }
}
