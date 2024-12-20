package com.clone.netflix.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        // Sauvegarde dans la base de données
        return movieRepository.saveAndFlush(movie);
    }
    
    public String saveImage(MultipartFile image) {
        try {
            // Chemin de stockage
            String uploadDir = "uploads/images/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Nom unique pour le fichier
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

            // Sauvegarde du fichier
            File dest = new File(uploadDir + fileName);
            image.transferTo(dest);

            // Retourner l'URL relative du fichier
            return "/uploads/images/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Échec de l'enregistrement de l'image", e);
        }
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