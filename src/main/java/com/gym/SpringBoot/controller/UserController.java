package com.gym.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.service.UserService;
import com.gym.SpringBoot.service.VerificationTokenService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        User registeredUser = userService.registerUser(user);
		verificationTokenService.sendVerificationEmail(registeredUser);
        return ResponseEntity.ok("User registered successfully. Please check your email to verify your account.");
    }
}

