// donacion-detail.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DonacionesService } from '../donaciones.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-donacion-detail',
  templateUrl: './donacion-detail.html',
  styleUrl: './donacion-detail.css',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class DonacionesDetailComponent implements OnInit {
  form: FormGroup;
  loading = false;
  isEdit = false;
  id: number | null = null;

  constructor(
    private fb: FormBuilder,
    private donacionesService: DonacionesService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      descripcion: ['', Validators.required],
      montoDonado: [null, [Validators.required, Validators.min(0)]],
      metodoEntrega: ['', Validators.required],
      fechaDonacion: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.donacionesService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar la donación')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      this.donacionesService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/donaciones']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.donacionesService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/donaciones']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }
}
