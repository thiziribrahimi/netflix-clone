package com.clone.netflix.repository;

import com.clone.netflix.model.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByCategoriesContaining(String category);
}