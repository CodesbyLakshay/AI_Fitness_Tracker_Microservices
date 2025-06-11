package com.fitness.activityservice;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
