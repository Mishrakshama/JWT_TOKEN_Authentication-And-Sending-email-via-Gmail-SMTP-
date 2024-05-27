package com.gym.SpringBoot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.SpringBoot.Entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmailIgnoreCase(String email);

}
