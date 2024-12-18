import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { Movie } from './../../models/movie';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [MatCardModule, CommonModule],
  template: `
    <div class="container">
      <div class="row">
        <div *ngFor="let movie of movies" class="col-md-4 mb-3">
          <mat-card>
            <mat-card-title>{{ movie.title }}</mat-card-title>
            <mat-card-content>{{ movie.description }}</mat-card-content>
          </mat-card>
        </div>
      </div>
    </div>
  `
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = [];

  constructor(private movieService: MovieService) {}

  ngOnInit() {
    this.movieService.getMovies().subscribe((data: Movie[]) => {
      this.movies = data;
    });
  }
}
