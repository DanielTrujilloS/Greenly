import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { DonacionesService } from '../donaciones.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { CampañasService } from '../../campañas/campañas.service';

@Component({
  selector: 'app-donacion-form',
  standalone: true,
  templateUrl: './donacion-form.html',
  styleUrl: './donacion-form.css',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ],
})
export class DonacionFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;
  error = '';

  donantes: any[] = [];
  campanias: any[] = [];
  tiposDonacion: any[] = [];
  estadosDonacion: any[] = [];

  constructor(
    private fb: FormBuilder,
    private donacionesService: DonacionesService,
    private router: Router,
    private route: ActivatedRoute,
    private campaniasService: CampañasService
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      descripcion: ['', Validators.required],
      montoDonado: ['', [Validators.required, Validators.min(1)]],
      metodoEntrega: ['', Validators.required],
      fechaDonacion: ['', Validators.required],
      campañaId: [null, Validators.required],
    });
  }

  ngOnInit() {
    // Cargar combos
    this.donacionesService
      .listarDonantes()
      .subscribe((d) => (this.donantes = d));
    // this.donacionesService
    //   .listarCampanias()
    //   .subscribe((c) => (this.campanias = c));
    this.campaniasService.getAll().subscribe({
      next: (data) => (this.campanias = data),
      error: () => {
        this.error = 'No se pudieron cargar campañas';
        this.campanias = [];
      },
    });
    this.donacionesService
      .listarTiposDonacion()
      .subscribe((t) => (this.tiposDonacion = t));
    this.donacionesService
      .listarEstadosDonacion()
      .subscribe((e) => (this.estadosDonacion = e));

    // Ver si es edición
    this.id = this.route.snapshot.paramMap.get('id')
      ? Number(this.route.snapshot.paramMap.get('id'))
      : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.donacionesService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => (this.error = 'No se pudo cargar la donación'),
      });
    }
  }

  onSubmit() {
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
          this.error = 'No se pudo actualizar';
        },
      });
    } else {
      this.donacionesService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/donaciones']);
        },
        error: () => {
          this.loading = false;
          this.error = 'No se pudo crear';
        },
      });
    }
  }
  cancelar() {
    this.router.navigate(['/donaciones']);
  }
}
