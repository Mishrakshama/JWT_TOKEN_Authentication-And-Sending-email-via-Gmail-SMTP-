package com.gym.SpringBoot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.SpringBoot.Entity.GymSlot;
import com.gym.SpringBoot.service.GymSlotService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/slots")
public class GymSlotController {

    @Autowired
    private GymSlotService gymSlotService;

    @PostMapping("/create")
    public ResponseEntity<GymSlot> createGymSlot(@RequestBody GymSlot gymSlot) {
        GymSlot newSlot = gymSlotService.createGymSlot(gymSlot);
        return ResponseEntity.ok(newSlot);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GymSlot> updateGymSlot(@PathVariable Long id, @RequestBody GymSlot gymSlotDetails) {
        GymSlot updatedSlot = gymSlotService.updateGymSlot(id, gymSlotDetails);
        return ResponseEntity.ok(updatedSlot);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGymSlot(@PathVariable Long id) {
        gymSlotService.deleteGymSlot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<GymSlot> getAllGymSlots() {
        return gymSlotService.getAllGymSlots();
    }
}


