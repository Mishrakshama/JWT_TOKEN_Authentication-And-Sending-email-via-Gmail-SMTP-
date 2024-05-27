package com.gym.SpringBoot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gym.SpringBoot.Entity.Feedback;
import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.Repository.FeedbackRepository;
import com.gym.SpringBoot.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback createFeedback(Long userId, Feedback feedbackDetails) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        feedbackDetails.setUser(optionalUser.get());
        return feedbackRepository.save(feedbackDetails);
    }

    public Feedback updateFeedback(Long feedbackId, Feedback feedbackDetails) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setComments(feedbackDetails.getComments());
        feedback.setRating(feedbackDetails.getRating());
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
