package com.gym.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String subject, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Dear User,\n\nThank you for registering. Please click the link below to verify your email address:\n" +
                        "http://localhost:8080/api/auth/confirm?token=" + token + "\n\nBest regards,\nYour Company");
        mailSender.send(message);
    }
}
