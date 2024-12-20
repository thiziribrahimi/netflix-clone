package com.clone.netflix.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    
    @Column(name = "description" ,length = 1000) 
    private String description;
    private String videoUrl;
    
    private String imageUrl;
    
    @ElementCollection // Indique une liste simple (categories sera une table jointe)
    @CollectionTable(name = "movie_categories", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "category") // Nom de la colonne pour les catégories
    private List<String> categories  = new ArrayList<>(); // Initialisation ici; // Liste des catégories associées

    @Version // Hibernate gère automatiquement le champ version pour le verrouillage optimiste
    private Integer version;
    
    // Constructeur par défaut
    public Movie() {
    }

    // Constructeur avec paramètres
    public Movie(String title, String genre, String description, String videoUrl, List<String> categories) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.videoUrl = videoUrl;
        this.categories = categories;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
    
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}