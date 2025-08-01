import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { PostsService } from '../posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-post-detail',
  standalone: true,
  templateUrl: './post-detail.html',
  styleUrl: './post-detail.css',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
  ],
})
export class PostDetailComponent {
  post: any;
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private postsService: PostsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.postsService.getById(+id).subscribe({
        next: (data) => {
          this.post = data;
          this.loading = false;
        },
        error: () => {
          this.error = 'No se pudo cargar el post';
          this.loading = false;
        },
      });
    }
  }
  volverALista() {
    this.router.navigate(['/posts']);
  }
}
