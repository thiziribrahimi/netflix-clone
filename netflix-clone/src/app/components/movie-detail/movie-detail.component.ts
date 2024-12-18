import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  template: `
    <div *ngIf="movie" class="container mt-5">
      <mat-card>
        <mat-card-header>
          <mat-card-title>{{ movie.title }}</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <p><strong>Genre:</strong> {{ movie.genre }}</p>
          <p>{{ movie.description }}</p>
        </mat-card-content>
      </mat-card>
    </div>
    <div *ngIf="!movie" class="text-center">
      <p>Chargement...</p>
    </div>
  `
})
export class MovieDetailComponent implements OnInit {
  movie!: Movie;

  constructor(
    private route: ActivatedRoute,
    private movieService: MovieService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.movieService.getMovieById(id).subscribe((data: Movie) => {
      this.movie = data;
    });
  }
}
