package com.fitness.userservice.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String createdAt;
    private String updatedAt;
}
