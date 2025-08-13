package com._z.eum.artisan.repository;

import com._z.eum.artisan.entity.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtisanRepository extends JpaRepository<Artisan, Integer> {
    Optional<Artisan> findByEmail(String email);
}