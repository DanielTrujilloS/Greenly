import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ComentariosService } from '../comentarios.service';
import { PostsService } from '../../posts/posts.service';
import { DonantesService } from '../../donantes/donantes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-comentario-form',
  templateUrl: './comentario-form.html',
  styleUrl: './comentario-form.css',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule
  ]
})
export class ComentarioFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  id: number | null = null;
  loading = false;
  posts: any[] = [];
  donantes: any[] = [];

  constructor(
    private fb: FormBuilder,
    private comentariosService: ComentariosService,
    private postsService: PostsService,
    private donantesService: DonantesService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      contenido: ['', Validators.required],
      fechaComentario: ['', Validators.required],
      postId: [null, Validators.required],
      donanteId: [null, Validators.required]
    });
  }

  ngOnInit() {
    //this.postsService.getAll().subscribe({ next: p => this.posts = p });
    
    this.postsService.getAll().subscribe({
      next: (data) => {
        console.log('Posts cargados:', data);
        this.posts = data;
      },
      error: (err) => {
        console.error('Error cargando posts', err);
        alert('Error al cargar posts');
        this.posts = [];
      },
    });
    
    //this.donantesService.getAll().subscribe({ next: d => this.donantes = d });

    this.donantesService.getAll().subscribe({
      next: (data) => {
        console.log('Donantes cargados:', data);
        this.donantes = data;
      },
      error: (err) => {
        console.error('Error cargando donantes', err);
        alert('Error al cargar donantes');
        this.donantes = [];
      },
    });

    this.id = this.route.snapshot.paramMap.get('id') ? Number(this.route.snapshot.paramMap.get('id')) : null;
    this.isEdit = !!this.id;
    if (this.isEdit) {
      this.comentariosService.getById(this.id!).subscribe({
        next: (data) => this.form.patchValue(data),
        error: () => alert('No se pudo cargar el comentario')
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;
    this.loading = true;
    const data = this.form.value;
    if (this.isEdit && this.id) {
      this.comentariosService.update(this.id, data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/comentarios']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo actualizar');
        }
      });
    } else {
      this.comentariosService.create(data).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/comentarios']);
        },
        error: () => {
          this.loading = false;
          alert('No se pudo crear');
        }
      });
    }
  }

  cancelar() {
    this.router.navigate(['/comentarios']);
  }
}
