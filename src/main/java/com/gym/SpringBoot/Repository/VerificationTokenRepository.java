package com.gym.SpringBoot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.SpringBoot.Entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

}

