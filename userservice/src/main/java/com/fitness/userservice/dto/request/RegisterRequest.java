package com.fitness.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;
    @NotBlank(message = "Keep a Strong Password")
    @Size(min = 8,message = "Minimum Password length must be 8 characters")
    private String password;
    
    private String firstname;
    private String lastname;
}
