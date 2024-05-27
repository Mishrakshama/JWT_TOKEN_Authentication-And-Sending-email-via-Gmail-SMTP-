package com.gym.SpringBoot.component;

import com.gym.SpringBoot.Entity.User;
import com.gym.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gym.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            admin.setEnabled(true);
            userService.save(admin);

            for (int i = 1; i <= 4; i++) {
                User trainer = new User();
                trainer.setUsername("trainer" + i);
                trainer.setEmail("trainer" + i + "@gym.com");
                trainer.setPassword(passwordEncoder.encode("trainer123"));
                trainer.setRole("ROLE_TRAINER");
                trainer.setEnabled(true);
                userService.save(trainer);
            }
        }
    }
}
