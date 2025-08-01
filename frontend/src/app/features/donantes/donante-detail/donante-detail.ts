import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DonantesService } from '../donantes.service';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-donante-detail',
  standalone: true,
  templateUrl: './donante-detail.html',
  styleUrl: './donante-detail.css',
  imports: [CommonModule, MatCardModule, CommonModule]
})
export class DonanteDetailComponent implements OnInit {
  donante: any = null;
  loading = true;
  error = '';

  constructor(
    private donantesService: DonantesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.donantesService.getById(id).subscribe({
        next: (data) => {
          this.donante = data;
          this.loading = false;
        },
        error: () => {
          this.error = 'No se pudo cargar el donante';
          this.loading = false;
        }
      });
    }
  }
}
