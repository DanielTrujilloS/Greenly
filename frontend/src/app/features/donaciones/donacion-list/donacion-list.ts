import { Component, OnInit } from '@angular/core';
import { DonacionesService } from '../donaciones.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-donacion-list',
  standalone: true,
  templateUrl: './donacion-list.html',
  styleUrl: './donacion-list.css',
  imports: [
    MatTableModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    RouterModule,
  ],
})
export class DonacionesListComponent implements OnInit {
  donaciones: any[] = [];
  loading = true;
  error = '';
  campañas: any[] = [];

  displayedColumns: string[] = [
    'name',
    'descripcion',
    'montoDonado',
    'metodoEntrega',
    'fechaDonacion',
    'idDonante',
    'idCampania',
    'idTipoDonacion',
    'idEstadoDonacion',
    'acciones',
  ];

  constructor(private donacionesService: DonacionesService) {}

  ngOnInit() {
    this.donacionesService.getAll().subscribe({
      next: (data) => {
        this.donaciones = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'No se pudieron cargar las donaciones';
        this.loading = false;
      },
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar esta donación?')) {
      this.donacionesService.delete(id).subscribe({
        next: () => {
          this.donaciones = this.donaciones.filter((d) => d.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        },
      });
    }
  }
}
