package com.example.customer_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")  // Explicitly using uppercase to match H2 default
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    
    @NotBlank(message = "Name is required")
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    
    // Add a default constructor that takes no arguments for JPA
    public User(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }
}
