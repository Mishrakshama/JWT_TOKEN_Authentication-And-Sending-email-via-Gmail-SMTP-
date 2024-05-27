package com.gym.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.SpringBoot.Entity.Trainer;
import com.gym.SpringBoot.Repository.TrainerRepository;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer findById(Long trainerId) {
        return trainerRepository.findById(trainerId).orElse(null);
    }

    public Trainer save(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
}

