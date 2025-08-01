import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { UbicacionCampaniaService } from '../ubicacion-campania.service';

@Component({
  selector: 'app-ubicacion-campania-form',
  templateUrl: './ubicacion-campania-form.html',
  styleUrl: './ubicacion-campania-form.css',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule]
})
export class UbicacionCampaniaFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private ubicacionCampaniaService: UbicacionCampaniaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      departamento: ['', Validators.required],
      distrito: ['', Validators.required],
      direccion: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.ubicacionCampaniaService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar la ubicación')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      this.ubicacionCampaniaService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/catalogos/vista-catalogo']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.ubicacionCampaniaService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/catalogos/vista-catalogo']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }

  cancelar() {
    this.router.navigate(['/catalogos/vista-catalogo']);
  }
}
