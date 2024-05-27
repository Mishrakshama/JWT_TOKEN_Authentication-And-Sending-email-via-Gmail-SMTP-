package com.gym.SpringBoot.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gym.SpringBoot.Entity.GymSlot;

@Repository
public interface GymSlotRepository extends JpaRepository<GymSlot, Long> {
}
