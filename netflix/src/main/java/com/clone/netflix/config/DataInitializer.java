package com.clone.netflix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.clone.netflix.model.User;
import com.clone.netflix.repository.UserRepository;

@Component
public class DataInitializer implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Pour crypter les mots de passe

    @Override
    public void run(String... args) throws Exception {
        // Vérifie si l'administrateur existe déjà
        if (userRepository.findByUsername("admin").isEmpty()) {
            // Crée un utilisateur admin avec un mot de passe crypté
            User admin = new User(
                "admin", 
                passwordEncoder.encode("adminpassword"), // Crypte le mot de passe
                "ROLE_ADMIN"
            );
            userRepository.save(admin); // Sauvegarde dans la base de données
            System.out.println("Admin user created: admin / adminpassword");
        } else {
            System.out.println("Admin user already exists");
        }
    }
}