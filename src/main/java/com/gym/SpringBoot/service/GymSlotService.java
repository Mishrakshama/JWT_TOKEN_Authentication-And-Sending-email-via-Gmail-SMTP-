package com.gym.SpringBoot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.SpringBoot.Entity.Booking;
import com.gym.SpringBoot.Entity.GymSlot;
import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.Repository.BookingRepository;
import com.gym.SpringBoot.Repository.GymSlotRepository;
import com.gym.SpringBoot.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GymSlotService {
	@Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GymSlotRepository gymSlotRepository;

    public GymSlot createGymSlot(GymSlot gymSlot) {
        return gymSlotRepository.save(gymSlot);
    }

    public GymSlot updateGymSlot(Long id, GymSlot gymSlotDetails) {
        GymSlot gymSlot = gymSlotRepository.findById(id).orElseThrow(() -> new RuntimeException("Slot not found"));
        gymSlot.setStartTime(gymSlotDetails.getStartTime());
        gymSlot.setEndTime(gymSlotDetails.getEndTime());
        gymSlot.setAvailableSlots(gymSlotDetails.getAvailableSlots());
        return gymSlotRepository.save(gymSlot);
    }

    public void deleteGymSlot(Long id) {
        gymSlotRepository.deleteById(id);
    }
    
    public Optional<GymSlot> getGymSlotById(Long id) {
        return gymSlotRepository.findById(id);
    }

    public List<GymSlot> getAllGymSlots() {
        return gymSlotRepository.findAll();
    }
    public String bookSlot(Long slotId, Long userId) {
        GymSlot gymSlot = gymSlotRepository.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (gymSlot.getAvailableSlots() <= 0) {
            return "No available slots.";
        }

        Booking booking = new Booking();
        booking.setSlot(gymSlot);
       
        booking.setUser(user);
        booking.setBookingTime(LocalDateTime.now());

        gymSlot.setAvailableSlots(gymSlot.getAvailableSlots() - 1);
        gymSlotRepository.save(gymSlot);
        bookingRepository.save(booking);

        return "Slot booked successfully.";
    }
}


