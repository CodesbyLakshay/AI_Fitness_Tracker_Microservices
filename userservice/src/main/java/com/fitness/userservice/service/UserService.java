package com.fitness.userservice.service;

import com.fitness.userservice.CustomException;
import com.fitness.userservice.dto.request.RegisterRequest;
import com.fitness.userservice.dto.response.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo  userRepo;

    public UserResponse register(@Valid RegisterRequest request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new CustomException("Email is Already Registered");
        }
        UserResponse userResponse = new UserResponse();

        try {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            User savedUser = userRepo.save(user);
            userResponse.setId(savedUser.getId());
            userResponse.setEmail(savedUser.getEmail());
            userResponse.setPassword(savedUser.getPassword());
            userResponse.setFirstname(savedUser.getFirstName());
            userResponse.setLastname(savedUser.getLastName());
            userResponse.setCreatedAt(savedUser.getCreatedAt());
            userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        } catch (Exception e) {
            throw new CustomException("Error Registering User" + e.getMessage());
        }
        return userResponse;
    }

    public UserResponse getUserProfile(String userId) {
       User user = userRepo.findById(userId).orElseThrow(() -> new CustomException("User does not Exist"));
        UserResponse userResponse = new UserResponse();
        try{
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setPassword(user.getPassword());
            userResponse.setFirstname(user.getFirstName());
            userResponse.setLastname(user.getLastName());
            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());
        } catch (Exception e) {
            throw new CustomException("Error Fetching User profile");
        }
        return userResponse;
    }

    public Boolean existByUserId(String userId) {
        return userRepo.existsById(userId);
    }
}
