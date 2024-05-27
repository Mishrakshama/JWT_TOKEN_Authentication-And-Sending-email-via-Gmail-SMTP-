package com.gym.SpringBoot.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.SpringBoot.Entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
