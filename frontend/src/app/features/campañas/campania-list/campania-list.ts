import { Component, OnInit } from '@angular/core';
import { CampañasService } from '../campañas.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-campania-list',
  imports: [MatTableModule,
    CommonModule,          
    MatTableModule,        
    MatCardModule,         
    MatButtonModule,       
    MatIconModule,         
    MatProgressBarModule,  
    ReactiveFormsModule,   
    NgIf,                  
    NgFor,
    RouterModule,
    FormsModule,
    MatFormField,
    MatLabel
  
    ],
  templateUrl: './campania-list.html',
  styleUrl: './campania-list.css'
})
export class CampañasListComponent implements OnInit {
  campanias: any[] = [];
  loading = true;
  error = '';


tituloDescripcionFiltro: string = '';
descripcionFiltro: string = '';
fechaFiltro: string = '';
fechaInicio: string = '';
fechaFin: string = '';
ongId: number | null = null;

// columnas que la tabla mostrará
displayedColumns: string[] = [];

  constructor(private campañasService: CampañasService, public  authService: AuthService ) {}

  ngOnInit() {
    // 1. Definir columnas base:
    this.displayedColumns = ['titulo','descripcion','fechaInicio','fechaFin'];

    // 2. ¿el usuario NO es Donante? → añadir columna Acciones
    if (this.authService.hasAnyRole(['ROLE_ONGADMIN','ROLE_ADMIN'])) {
      this.displayedColumns.push('acciones');
    }
    this.listarTodo();
  }

  listarTodo() {
    this.loading = true;
    this.campañasService.getAll().subscribe({
      next: (data) => {
        this.campanias = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar las campañas';
        this.loading = false;
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar esta campaña?')) {
      this.campañasService.delete(id).subscribe({
        next: () => {
          this.campanias = this.campanias.filter(c => c.id !== id);
        },
        error: () => {
          alert('No se pudo eliminar');
        }
      });
    }
  }

  buscarPorDescripcionYFecha() {
    this.loading = true;
    this.campañasService.buscarPorDescripcionYFecha(this.descripcionFiltro, this.fechaFiltro).subscribe({
      next: (data) => {
        this.campanias = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudo buscar';
        this.loading = false;
      }
    });
  }
  
  buscarPorTituloODescripcion() {
    this.loading = true;
    this.campañasService.buscarPorTituloODescripcion(this.tituloDescripcionFiltro).subscribe({
      next: (data) => {
        this.campanias = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudo buscar';
        this.loading = false;
      }
    });
  }
  

  buscarPorRangoFechas() {
    this.loading = true;
    this.campañasService.getPorRangoFechas(this.fechaInicio, this.fechaFin).subscribe({
      next: (data) => {
        this.campanias = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudo buscar';
        this.loading = false;
      }
    });
  }

  buscarPorOngId() {
    if (!this.ongId) {
      alert('Debes ingresar un ID de ONG');
      return;
    }
    this.loading = true;
    this.campañasService.getCampañasPorOng(this.ongId).subscribe({
      next: (data) => {
        this.campanias = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudo buscar por ONG';
        this.loading = false;
      }
    });
  }
}
