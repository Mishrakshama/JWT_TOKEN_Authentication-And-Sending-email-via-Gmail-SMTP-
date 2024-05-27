package com.gym.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gym.SpringBoot.Entity.PasswordResetToken;
import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.Repository.PasswordResetTokenRepository;
import com.gym.SpringBoot.Repository.UserRepository;
import java.util.UUID;
@Service
public class PasswordResetService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void createPasswordResetToken(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        tokenRepository.save(passwordResetToken);
    }
    public void sendPasswordResetEmail(User user) {
        String token = UUID.randomUUID().toString();
        createPasswordResetToken(user, token);
        String recipientAddress = user.getEmail();
        String subject = "Password Reset Request";
        emailService.sendVerificationEmail(recipientAddress, subject, token);
    }

    public PasswordResetToken getPasswordResetToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void resetPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
