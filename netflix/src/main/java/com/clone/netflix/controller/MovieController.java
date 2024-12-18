package com.clone.netflix.controller;

import com.clone.netflix.model.Movie;
import com.clone.netflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getMovies(@RequestParam(value = "category", required = false) String category) {
        if (category != null) {
            return movieService.getMoviesByCategory(category);
        }
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }
    
    // Réservé aux admins pour ajouter un film
    @Secured("ROLE_ADMIN")
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    // Réservé aux admins pour supprimer un film
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
    
    // Réservé aux admins pour supprimer un film
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        return movieService.updateMovie(id, movieDetails);
    }
}