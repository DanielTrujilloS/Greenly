import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OngsService } from '../ongs.service';

@Component({
  selector: 'app-ong-list',
  imports: [
    MatTableModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    NgIf,
    NgFor,
    RouterModule
  ],
  templateUrl: './ong-list.html',
  styleUrl: './ong-list.css'
})
export class OngListComponent implements OnInit {
  ongs: any[] = [];
  loading = true;
  error = '';

  constructor(private ongsService: OngsService) {}

  ngOnInit() {
    this.ongsService.getAll().subscribe({
      next: (data) => {
        this.ongs = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar las ONGs';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar esta ONG?')) {
      this.ongsService.delete(id).subscribe({
        next: () => {
          this.ongs = this.ongs.filter(o => o.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }
}
