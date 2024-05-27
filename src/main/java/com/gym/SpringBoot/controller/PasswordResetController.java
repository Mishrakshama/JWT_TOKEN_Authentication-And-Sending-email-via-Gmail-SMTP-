//package com.gym.SpringBoot.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.gym.SpringBoot.Entity.PasswordResetToken;
//import com.gym.SpringBoot.Entity.User;
//import com.gym.SpringBoot.service.PasswordResetService;
//import com.gym.SpringBoot.service.UserService;
//
//@RestController
//@RequestMapping("/api/auth")
//public class PasswordResetController {
//
//    @Autowired
//    private PasswordResetService passwordResetService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody String email) {
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            return ResponseEntity.badRequest().body("Invalid email address");
//        }
//        passwordResetService.sendPasswordResetEmail(user);
//        return ResponseEntity.ok("Password reset email sent. Please check your email.");
//    }
//
//    @PostMapping("/change-password")
//    public ResponseEntity<String> changePassword(@RequestParam("token") String token, @RequestBody String newPassword) {
//        PasswordResetToken resetToken = passwordResetService.getPasswordResetToken(token);
//        if (resetToken == null) {
//            return ResponseEntity.badRequest().body("Invalid token");
//        }
//
//        User user = resetToken.getUser();
//        passwordResetService.resetPassword(user, newPassword);
//        return ResponseEntity.ok("Password changed successfully");
//    }
//}


package com.gym.SpringBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.SpringBoot.Entity.PasswordResetToken;
import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.service.PasswordResetService;
import com.gym.SpringBoot.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {

    private static final Logger logger = LoggerFactory.getLogger(PasswordResetController.class);

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private UserService userService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody java.util.Map<String, String> request) {
        String email = request.get("email");
        logger.info("Received password reset request for email: " + email);
        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                logger.warn("No user found with email: " + email);
                return ResponseEntity.badRequest().body("Invalid Email Address");
            }
            passwordResetService.sendPasswordResetEmail(user);
            return ResponseEntity.ok("Password reset email sent. Please check your email.");
        } catch (Exception e) {
            logger.error("Error resetting password for email: " + email, e);
            return ResponseEntity.badRequest().body("Error resetting password. Please try again.");
        }
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("token") String token, @RequestBody String newPassword) {
        try {
            PasswordResetToken resetToken = passwordResetService.getPasswordResetToken(token);
            if (resetToken == null) {
                return ResponseEntity.badRequest().body("Invalid token");
            }

            User user = resetToken.getUser();
            passwordResetService.resetPassword(user, newPassword);
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            logger.error("Error changing password for token: " + token, e);
            return ResponseEntity.badRequest().body("Error changing password. Please try again.");
        }
    }
}
