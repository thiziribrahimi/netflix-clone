package com.clone.netflix.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clone.netflix.model.Movie;
import com.clone.netflix.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Gestion des films") // Tag pour catégoriser les endpoints	
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(summary = "Récupérer tous les films", description = "Retourne la liste complète des films disponibles.")
    @GetMapping
    public List<Movie> getMovies(@RequestParam(value = "category", required = false) String category) {
    	System.out.println("################Demande de la liste des films");
        if (category != null) {
            return movieService.getMoviesByCategory(category);
        }
        return movieService.getAllMovies();
    }

    @Operation(summary = "Récupérer un film par ID", description = "Retourne les détails d'un film spécifique.")
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }
    
    @Operation(summary = "Ajouter un nouveau film", description = "Permet aux administrateurs d'ajouter un nouveau film.")
    @Secured("ROLE_ADMIN")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Movie addMovie(
        @RequestPart("movie") String movieJson, 
        @RequestPart("image") MultipartFile image
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Movie movie = objectMapper.readValue(movieJson, Movie.class);

        // Sauvegarde de l'image
        String imagePath = "C:\\Users\\Lounes\\Documents\\tmp\\images\\" + image.getOriginalFilename();
        Files.copy(image.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);

        movie.setImageUrl(imagePath);
        return movieService.addMovie(movie);
    }


    // Réservé aux admins pour supprimer un film
    @Operation(summary = "Supprimer un film", description = "Supprime un film par son ID.")
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
    
    // Réservé aux admins pour supprimer un film
    @Operation(summary = "Mise à jour d'un film", description = "Met à jour un film par son ID.")
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        return movieService.updateMovie(id, movieDetails);
    }
}