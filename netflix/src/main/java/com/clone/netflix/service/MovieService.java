package com.clone.netflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.netflix.model.Movie;
import com.clone.netflix.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    // Ajouter un nouveau film avec validations
    @Transactional
    public Movie addMovie(Movie movie) {
        // Validation des champs obligatoires
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Le titre est obligatoire.");
        }
        if (movie.getGenre() == null || movie.getGenre().isEmpty()) {
            throw new IllegalArgumentException("Le genre est obligatoire.");
        }
        if (movie.getDescription() == null || movie.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La description est obligatoire.");
        }
        if (movie.getVideoUrl() == null || movie.getVideoUrl().isEmpty()) {
            throw new IllegalArgumentException("L'URL de la vidéo est obligatoire.");
        }

        // Vérification des catégories (si nécessaire)
        if (movie.getCategories() == null || movie.getCategories().isEmpty()) {
            throw new IllegalArgumentException("Le film doit appartenir à au moins une catégorie.");
        }

        // Sauvegarde dans la base de données
        return movieRepository.saveAndFlush(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    
    @Transactional // Gère la transaction automatiquement
    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id));
        
        // Mise à jour des champs
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setVideoUrl(updatedMovie.getVideoUrl());
        existingMovie.setCategories(updatedMovie.getCategories());
        
        // Sauvegarde
        return movieRepository.save(existingMovie);
    }

    public List<Movie> getMoviesByCategory(String category) {
        return movieRepository.findByCategoriesContaining(category);
    }
}