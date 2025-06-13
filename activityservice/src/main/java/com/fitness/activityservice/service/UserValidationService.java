package com.fitness.activityservice.service;

import com.fitness.activityservice.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@AllArgsConstructor
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId){

        try{

            return userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate",userId)
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new CustomException("User Not found:" + userId);
            if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                throw new CustomException("Invalid UserId :" + userId);
        }
        return false;
    }

}
