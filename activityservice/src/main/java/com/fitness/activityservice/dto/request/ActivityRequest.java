package com.fitness.activityservice.dto.request;

import lombok.Data;
import model.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Object> additionalMetrics;
}
