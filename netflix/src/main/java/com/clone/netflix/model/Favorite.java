package com.clone.netflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long movieId;
    
    public Favorite() {}

    public Favorite(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getId() { 
    	return id;
    }
    
    public Long getUserId() { 
    	return userId; 
    }
    
    public void setUserId(Long userId) { 
    	this.userId = userId; 
    }
    
    public Long getMovieId() {
    	return movieId; 
    }
    
    public void setMovieId(Long movieId) { 
    	this.movieId = movieId; 
    }
    
}