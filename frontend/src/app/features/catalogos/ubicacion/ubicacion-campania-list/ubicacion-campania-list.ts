import { Component, OnInit } from '@angular/core';
import { UbicacionCampaniaService } from '../ubicacion-campania.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-ubicacion-campania-list',
  templateUrl: './ubicacion-campania-list.html',
  styleUrl: './ubicacion-campania-list.css',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatCardModule, MatButtonModule, MatIconModule, MatProgressBarModule, NgIf, NgFor, RouterModule]
})
export class UbicacionCampaniaListComponent implements OnInit {
  ubicaciones: any[] = [];
  loading = true;
  error = '';

  constructor(private ubicacionCampaniaService: UbicacionCampaniaService) {}

  ngOnInit() {
    this.ubicacionCampaniaService.getAll().subscribe({
      next: (data) => {
        this.ubicaciones = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar las ubicaciones';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar esta ubicación?')) {
      this.ubicacionCampaniaService.delete(id).subscribe({
        next: () => {
          this.ubicaciones = this.ubicaciones.filter(u => u.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }
}
