import { Component, OnInit } from '@angular/core';
import { ComentariosService } from '../comentarios.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-comentario-list',
  templateUrl: './comentario-list.html',
  styleUrl: './comentario-list.css',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    NgIf,
    NgFor,
    RouterModule
  ]
})
export class ComentarioListComponent implements OnInit {
  comentarios: any[] = [];
  loading = true;
  error = '';

  constructor(private comentariosService: ComentariosService) {}

  ngOnInit() {
    this.comentariosService.getAll().subscribe({
      next: (data) => {
        this.comentarios = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los comentarios';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este comentario?')) {
      this.comentariosService.delete(id).subscribe({
        next: () => {
          this.comentarios = this.comentarios.filter(c => c.idComentario !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }
}
