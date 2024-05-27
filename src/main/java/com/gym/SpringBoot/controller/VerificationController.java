package com.gym.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.SpringBoot.Entity.VerificationToken;
import com.gym.SpringBoot.service.UserService;
import com.gym.SpringBoot.service.VerificationTokenService;

@RestController
@RequestMapping("/api/auth")
public class VerificationController {

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        verificationTokenService.confirmUser(verificationToken);
        return ResponseEntity.ok("Email verified successfully. You can now log in.");
        
    }
}

