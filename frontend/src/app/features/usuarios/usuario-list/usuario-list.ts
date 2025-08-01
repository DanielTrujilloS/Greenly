import { Component, OnInit } from '@angular/core';
import { UsuariosService } from '../usuarios.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Usuario } from '../usuario.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    NgFor,
    NgIf,
    RouterModule
  ],
  templateUrl: './usuario-list.html',
  styleUrl: './usuario-list.css'
})
export class UsuarioListComponent implements OnInit {
  usuarios: Usuario[] = [];
  loading = true;
  error = '';

  constructor(private usuariosService: UsuariosService) {}

  ngOnInit() {
    this.usuariosService.getAll().subscribe({
      next: (data) => {
        this.usuarios = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los usuarios';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este usuario?')) {
      this.usuariosService.delete(id).subscribe({
        next: () => {
          this.usuarios = this.usuarios.filter(u => u.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }
}
