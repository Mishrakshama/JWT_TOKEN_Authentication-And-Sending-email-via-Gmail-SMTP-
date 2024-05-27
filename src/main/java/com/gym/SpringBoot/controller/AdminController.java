package com.gym.SpringBoot.controller;


import com.gym.SpringBoot.Entity.GymSlot;
import com.gym.SpringBoot.Entity.Trainer;
import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.service.GymSlotService;
import com.gym.SpringBoot.service.TrainerService;
import com.gym.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;

    @Autowired
    private GymSlotService gymSlotService;

    // User management endpoints
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Slot management endpoints
    @GetMapping("/slots")
    public List<GymSlot> getAllSlots() {
        return gymSlotService.getAllGymSlots();
    }

    @GetMapping("/slots/{id}")
    public ResponseEntity<GymSlot> getSlotById(@PathVariable Long id) {
        GymSlot slot = gymSlotService.getGymSlotById(id).orElseThrow(() -> new RuntimeException("Slot not found"));
        return ResponseEntity.ok(slot);
    }

    @PostMapping("/slots")
    public GymSlot createSlot(@RequestBody GymSlot slot) {
        return gymSlotService.createGymSlot(slot);
    }

    @PutMapping("/slots/{id}")
    public GymSlot updateSlot(@PathVariable Long id, @RequestBody GymSlot slotDetails) {
        return gymSlotService.updateGymSlot(id, slotDetails);
    }

    @DeleteMapping("/slots/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        gymSlotService.deleteGymSlot(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/slots/book")
    public ResponseEntity<String> bookSlot(@RequestParam Long slotId, @RequestParam Long userId) {
        String response = gymSlotService.bookSlot(slotId, userId);
        return ResponseEntity.ok(response);
    }

    // Trainer management endpoints
    @PostMapping("/assignTrainer")
    public ResponseEntity<String> assignTrainerToUser(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long trainerId = request.get("trainerId");

        User user = userService.findById(userId);
        Trainer trainer = trainerService.findById(trainerId);

        if (user != null && trainer != null) {
            user.setTrainer(trainer);
            userService.save(user);
            return ResponseEntity.ok("Trainer assigned to user successfully");
        }
        return ResponseEntity.badRequest().body("Invalid user or trainer ID");
    }

    @GetMapping("/allTrainers")
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAll();
        return ResponseEntity.ok(trainers);
    }
}

