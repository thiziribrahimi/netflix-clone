import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private baseUrl = 'http://localhost:9000/movies'; // URL du backend

  constructor(private http: HttpClient) {}

  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.baseUrl).pipe(
      map((movies) =>
        movies.map((movie) => {
          // Extraire uniquement le nom du fichier de l'URL
          const fileName = movie.imageUrl ? this.extractFileName(movie.imageUrl) : '';
          
          // Construire le chemin final
          movie.imageUrl = `/assets/images/${fileName}`;
          return movie;
        })
      )
    );
  }

// Méthode pour récupérer un film par ID
getMovieById(id: number): Observable<Movie> {
  return this.http.get<Movie>(`${this.baseUrl}/${id}`).pipe(
    map((movie) => {
      // Extraire uniquement le nom du fichier de l'URL
      const fileName = movie.imageUrl ? this.extractFileName(movie.imageUrl) : '';

      // Construire le chemin final
      movie.imageUrl = `/assets/images/${fileName}`;
      return movie;
    })
  );
}

  private extractFileName(imageUrl: string): string {
    const parts = imageUrl.split(/[/\\]/); // Gérer les séparateurs "/" et "\"
    return parts[parts.length - 1]; // Récupérer le dernier segment
  }
}