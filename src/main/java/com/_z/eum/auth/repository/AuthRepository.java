package com._z.eum.auth.repository;

import com._z.eum.auth.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findTopByEmailOrderByCreatedAtDesc(String email);
}

