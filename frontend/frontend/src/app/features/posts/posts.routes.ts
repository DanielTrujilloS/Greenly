import { Routes } from '@angular/router';
import { PostFormComponent } from './post-form/post-form';
import { PostDetailComponent } from './post-detail/post-detail';
import { PostListComponent } from './post-list/post-list';

export const POSTS_ROUTES: Routes = [
  { path: '', component: PostListComponent },
  { path: 'nuevo', component: PostFormComponent },
  { path: ':id/editar', component: PostFormComponent },
  { path: ':id', component: PostDetailComponent },
];

// ¡Obligatorio para módulo ES!
export default POSTS_ROUTES;
