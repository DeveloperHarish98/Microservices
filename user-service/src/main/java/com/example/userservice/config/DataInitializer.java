package com.example.userservice.config;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("!test")  // Don't run this in tests
public class DataInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    public void initData() {
        // Clear existing data
        userRepository.deleteAll();

        // Add test users
        userRepository.save(new User("john_doe", "John Doe", "john@example.com"));
        userRepository.save(new User("jane_smith", "Jane Smith", "jane@example.com"));
        userRepository.save(new User("bob_johnson", "Bob Johnson", "bob@example.com"));
    }
}
