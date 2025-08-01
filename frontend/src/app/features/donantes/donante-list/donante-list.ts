import { Component, OnInit } from '@angular/core';
import { DonantesService } from '../donantes.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-donante-list',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    CommonModule,
    NgFor,
    NgIf,
    RouterModule,
    CommonModule
  ],
  templateUrl: './donante-list.html',
  styleUrl: './donante-list.css'
})
export class DonanteListComponent implements OnInit {
  donantes: any[] = [];
  loading = true;
  error = '';

  constructor(private donantesService: DonantesService) {}

  ngOnInit() {
    this.donantesService.getAll().subscribe({
      next: (data) => {
        this.donantes = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los donantes';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este donante?')) {
      this.donantesService.delete(id).subscribe({
        next: () => {
          this.donantes = this.donantes.filter(d => d.id !== id);
        },
        error: () => alert('No se pudo eliminar')
      });
    }
  }
}
