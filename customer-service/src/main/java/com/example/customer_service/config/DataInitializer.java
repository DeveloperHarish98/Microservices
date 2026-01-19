package com.example.customer_service.config;


import com.example.customer_service.model.Customer;
import com.example.customer_service.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer {

    private final CustomerRepository customerRepository;

    @PostConstruct
    public void initData() {
        // Clear existing data
        customerRepository.deleteAll();

        customerRepository.save(new Customer("Jack", "Jack Doe", "jack@gmail.com"));
        customerRepository.save(new Customer("Watson", "Watson Smith", "watson@email.com"));
        customerRepository.save(new Customer("Keeth", "Keeth Johnson", "keeth@email.com"));
    }
}
