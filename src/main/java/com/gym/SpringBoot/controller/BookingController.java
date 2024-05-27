package com.gym.SpringBoot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.SpringBoot.Entity.Booking;
import com.gym.SpringBoot.service.BookingService;


@RestController
@RequestMapping("/api/user/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestParam Long slotId, @RequestParam Long userId) {
        Booking newBooking = bookingService.createBooking(slotId, userId);
        return ResponseEntity.ok(newBooking);
    }
}



