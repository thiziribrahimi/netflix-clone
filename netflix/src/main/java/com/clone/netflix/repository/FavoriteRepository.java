package com.clone.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.netflix.model.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
}