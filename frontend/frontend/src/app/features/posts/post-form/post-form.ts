import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { PostsService } from '../posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { CampañasService } from '../../campañas/campañas.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-form',
  standalone: true,
  templateUrl: './post-form.html',
  styleUrl: './post-form.css',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule, //Modulo agregado
    MatOptionModule, //Modulo agregado
    MatSnackBarModule,
  ],
})
export class PostFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;
  campanias: any[] = [];

  constructor(
    private fb: FormBuilder,
    private postsService: PostsService,
    private route: ActivatedRoute,
    private router: Router,
    private campaniasService: CampañasService,
    private snackBar: MatSnackBar
  ) {
    this.form = this.fb.group({
      titulo: ['', Validators.required],
      contenido: ['', Validators.required],
      fechaPublicacion: ['', Validators.required],
      imagen: [null],
      campañaId: [null, Validators.required], // Campo obligatorio
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id')
      ? Number(this.route.snapshot.paramMap.get('id'))
      : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.postsService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar el post'),
      });
    }
    this.campaniasService.getAll().subscribe({
      next: (data) => {
        console.log('Campañas cargadas:', data);
        this.campanias = data;
      },
      error: (err) => {
        console.error('Error cargando campañas', err);
        alert('Error al cargar campañas');
        this.campanias = [];
      },
    });
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      this.postsService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;

          this.snackBar.open('Post actualizado correctamente', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'right',
            verticalPosition: 'top',
          });
          this.router.navigate(['/posts']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        },
      });
    } else {
      this.postsService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.snackBar.open('Post actualizado correctamente', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'right',
            verticalPosition: 'top',
          });
          this.router.navigate(['/posts']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        },
      });
    }
  }
  onImagenSeleccionada(evento: any) {
    const archivo: File = evento.target.files[0];
    if (archivo) {
      const lector = new FileReader();
      lector.onload = () => {
        const base64 = lector.result as string;
        // "data:image/png;base64,"
        this.form.patchValue({ imagen: base64.split(',')[1] });
      };
      lector.readAsDataURL(archivo);
    }
  }
  cancelar() {
    this.router.navigate(['/posts']);
  }
}
