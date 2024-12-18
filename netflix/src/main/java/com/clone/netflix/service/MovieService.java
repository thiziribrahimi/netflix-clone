package com.clone.netflix.service;

import com.clone.netflix.model.Movie;
import com.clone.netflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    
    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie != null) {
            existingMovie.setTitle(movieDetails.getTitle());
            existingMovie.setGenre(movieDetails.getGenre());
            existingMovie.setDescription(movieDetails.getDescription());
            existingMovie.setVideoUrl(movieDetails.getVideoUrl());
            
            return movieRepository.save(existingMovie);
        }
        return null;
    }
}