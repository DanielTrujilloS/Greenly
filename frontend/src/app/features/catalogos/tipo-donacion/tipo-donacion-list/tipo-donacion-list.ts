import { Component, OnInit } from '@angular/core';
import { TipoDonacionService } from '../tipo-donacion.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-tipo-donacion-list',
  templateUrl: './tipo-donacion-list.html',
  styleUrl: './tipo-donacion-list.css',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatCardModule, MatButtonModule, MatIconModule, MatProgressBarModule, NgIf, NgFor, RouterModule]
})
export class TipoDonacionListComponent implements OnInit {
  tipos: any[] = [];
  loading = true;
  error = '';

  constructor(private tipoDonacionService: TipoDonacionService) {}

  ngOnInit() {
    this.tipoDonacionService.getAll().subscribe({
      next: (data) => {
        this.tipos = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los tipos';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este tipo?')) {
      this.tipoDonacionService.delete(id).subscribe({
        next: () => {
          this.tipos = this.tipos.filter(t => t.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }
}
