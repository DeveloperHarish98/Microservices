package com.example.customer_service.service;

import com.example.customer_service.exception.ResourceNotFoundException;
import com.example.customer_service.model.Customer;
import com.example.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getUserById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public Customer createUser(Customer user) {
        if (customerRepository.existsByCustomername(user.getCustomername())) {
            throw new IllegalArgumentException("Customer name is already taken");
        }
        if (customerRepository.existsByCustomeremail(user.getCustomeremail())) {
            throw new IllegalArgumentException("Customer email is already in use");
        }
        return customerRepository.save(user);
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        if (!customer.getCustomername().equals(customerDetails.getCustomername()) &&
            customerRepository.existsByCustomername(customerDetails.getCustomername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        
        if (!customer.getCustomeremail().equals(customerDetails.getCustomeremail()) &&
            customerRepository.existsByCustomeremail(customerDetails.getCustomeremail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        customer.setName(customerDetails.getCustomername());
        customer.setCustomeremail(customerDetails.getCustomeremail());
        customer.setCustomername(customerDetails.getCustomername());
        
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }


}
