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
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GymSlotRepository gymSlotRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(Long slotId, Long userId) {
        Optional<GymSlot> optionalSlot = gymSlotRepository.findById(slotId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalSlot.isPresent() || !optionalUser.isPresent()) {
            throw new RuntimeException("Slot or User not found");
        }

        GymSlot slot = optionalSlot.get();
        User user = optionalUser.get();

        if (slot.getAvailableSlots() <= 0) {
            throw new RuntimeException("No available slots");
        }

        Booking booking = new Booking();
        booking.setSlot(slot);
        booking.setUser(user);
        booking.setBookingTime(LocalDateTime.now());

        slot.setAvailableSlots(slot.getAvailableSlots() - 1);
        gymSlotRepository.save(slot);

        return bookingRepository.save(booking);
    }
}


