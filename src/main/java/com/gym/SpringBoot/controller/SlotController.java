package com.gym.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.SpringBoot.Entity.GymSlot;
import com.gym.SpringBoot.service.GymSlotService;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    @Autowired
    private GymSlotService gymSlotService;

    @GetMapping("/available")
    public List<GymSlot> getAvailableSlots() {
        return gymSlotService.getAllGymSlots(); 
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookSlot(@RequestParam Long slotId, @RequestParam Long userId) {
        // Booking logic here
        return ResponseEntity.ok("Slot booked successfully.");
    }
}

