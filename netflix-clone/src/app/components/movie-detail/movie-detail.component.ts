import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {
  movie!: Movie;

  constructor(private route: ActivatedRoute, private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    // Récupérer l'ID depuis les paramètres de la route
    const id = Number(this.route.snapshot.paramMap.get('id')); // Conversion en nombre

    if (!isNaN(id)) {
      // Appeler le service pour récupérer les données du film
      this.movieService.getMovieById(id).subscribe(
        (data: Movie) => {
          this.movie = data; // Assigner les données reçues
        },
        (error) => {
          console.error('Erreur lors de la récupération du film :', error);
        }
      );
    } else {
      console.error('ID invalide');
    }
  }

  goBack(): void {
    this.router.navigate(['/']); // Redirige vers l'accueil
  }
}
