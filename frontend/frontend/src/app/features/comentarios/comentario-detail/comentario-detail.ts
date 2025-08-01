import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ComentariosService } from '../comentarios.service';
import { PostsService } from '../../posts/posts.service';
import { DonantesService } from '../../donantes/donantes.service';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-comentario-detail',
  standalone: true,
  templateUrl: './comentario-detail.html',
  styleUrl: './comentario-detail.css',
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
export class ComentarioDetailComponent {
  form: FormGroup;
  loading = false;
  error = '';
  posts: any[] = [];
  donantes: any[] = [];

  constructor(
    private fb: FormBuilder,
    private comentariosService: ComentariosService,
    private postsService: PostsService,
    private donantesService: DonantesService,
    private router: Router
  ) {
    this.form = this.fb.group({
      contenido: ['', Validators.required],
      fechaComentario: ['', Validators.required],
      postId: [null, Validators.required],
      donanteId: [null, Validators.required]
    });

    this.postsService.getAll().subscribe({ next: p => this.posts = p });
    this.donantesService.getAll().subscribe({ next: d => this.donantes = d });
  }

  onSubmit() {
    if (this.form.invalid) return;
    this.loading = true;
    this.comentariosService.create(this.form.value).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/comentarios']);
      },
      error: () => {
        this.loading = false;
        this.error = 'No se pudo registrar el comentario';
      }
    });
  }
}
