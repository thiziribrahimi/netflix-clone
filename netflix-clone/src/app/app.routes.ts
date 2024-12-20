import { Routes } from '@angular/router';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieDetailComponent } from './components/movie-detail/movie-detail.component';

export const routes: Routes = [
  { path: '', component: MovieListComponent }, // Page d'accueil (liste de films)
  { path: 'movies/:id', component: MovieDetailComponent }, // Détail d'un film
  { path: '**', redirectTo: '' } // Redirection par défaut si route invalide
];
