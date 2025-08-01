import { Component, OnInit } from '@angular/core';
import { EstadoDonacionService } from '../estado-donacion.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-estado-donacion-list',
  templateUrl: './estado-donacion-list.html',
  styleUrl: './estado-donacion-list.css',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatCardModule, MatButtonModule, MatIconModule, MatProgressBarModule, NgIf, NgFor, RouterModule]
})
export class EstadoDonacionListComponent implements OnInit {
  estados: any[] = [];
  loading = true;
  error = '';

  constructor(private estadoDonacionService: EstadoDonacionService) {}

  ngOnInit() {
    this.estadoDonacionService.getAll().subscribe({
      next: (data) => {
        this.estados = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los estados';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este estado?')) {
      this.estadoDonacionService.delete(id).subscribe({
        next: () => {
          this.estados = this.estados.filter(e => e.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }
}
