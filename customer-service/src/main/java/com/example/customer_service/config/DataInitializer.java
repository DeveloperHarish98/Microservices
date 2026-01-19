package com.example.customer_service.config;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("!test")  // Don't run this in tests
public class DataInitializer {

    private final CustomerRepository userRepository;

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
