import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Import pour ngModel
import { RouterModule } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss'],
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = [];
  filteredMovies: Movie[] = [];
  searchQuery: string = '';
  selectedCategory: string = '';
  categories: string[] = ['Action', 'Drama', 'Comedy', 'Horror'];

  constructor(private movieService: MovieService) {}

  ngOnInit(): void {
    this.movieService.getMovies().subscribe((data: Movie[]) => {
      this.movies = data;
      this.filteredMovies = data;
    });
  }

  filterByCategory(): void {
    if (this.selectedCategory) {
      this.filteredMovies = this.movies.filter(
        (movie) => movie.genre === this.selectedCategory
      );
    } else {
      this.filteredMovies = this.movies;
    }
  }

  filterMovies(): void {
    this.filteredMovies = this.movies.filter((movie) =>
      movie.title.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  addToFavorites(movie: Movie): void {
    console.log(`Ajouté aux favoris : ${movie.title}`);
    // Implémentez la logique des favoris ici
  }
}
