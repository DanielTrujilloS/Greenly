import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UsuariosService } from '../usuarios.service';
import { Usuario } from '../usuario.model';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-usuario-detail',
  standalone: true,
  templateUrl: './usuario-detail.html',
  styleUrl: './usuario-detail.css',
  imports: [CommonModule, MatCardModule, RouterLink]
})
export class UsuarioDetailComponent implements OnInit {
  usuario: Usuario | null = null;
  error = '';
  loading = true;

  constructor(
    private usuariosService: UsuariosService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.usuariosService.getById(id).subscribe({
      next: (data) => {
        this.usuario = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudo cargar el usuario';
        this.loading = false;
      }
    });
  }
}
