import { Component, OnInit } from '@angular/core';
import { PostsService } from '../posts.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CampañasService } from '../../campañas/campañas.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.html',
  styleUrl: './post-list.css',
  imports: [
    MatTableModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    NgIf,
    NgFor,
    RouterModule,
    MatSnackBarModule,
  ],
})
export class PostListComponent implements OnInit {
  posts: any[] = [];
  campañas: any[] = [];
  loading = true;
  error = '';

  constructor(
    private postsService: PostsService,
    private campañasService: CampañasService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit() {
    this.campañasService.getAll().subscribe({
      next: (campañasData) => {
        this.campañas = campañasData;

        this.postsService.getAll().subscribe({
          next: (postData) => {
            this.posts = postData.map((post: any) => ({
              ...post,
              fecha: post.fechaPublicacion, //normalizamos el campo para aplicar mismo formato que en el backend
              id: post.idPosts, // normalizamos el campo para uso uniforme
              nombreCampaña: this.getNombreCampaña(post.campañaId),
            }));
            this.loading = false;
          },
          error: () => {
            this.error = 'No se pudieron cargar los posts';
            this.loading = false;
          },
        });
      },
      error: () => {
        this.error = 'No se pudieron cargar las campañas';
        this.loading = false;
      },
    });
  }

  getNombreCampaña(id: number): string {
    const campaña = this.campañas.find((c) => c.id === id);
    return campaña ? campaña.nombre : 'Sin campaña';
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este post?')) {
      this.postsService.delete(id).subscribe({
        next: () => {
          this.posts = this.posts.filter((p) => p.id !== id);
          this.snackBar.open('Post eliminado correctamente', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'right',
            verticalPosition: 'top',
          });
        },
        error: () => {
          alert('No se pudo eliminar');
        },
      });
    }
  }

  mostrarDetalle(post: any) {
    console.log('Detalles del post seleccionado:', post);
    this.router.navigate(['/posts', post.id]);
  }
}
