package com.fitness.activityservice.service;

import com.fitness.activityservice.CustomException;
import com.fitness.activityservice.dto.request.ActivityRequest;
import com.fitness.activityservice.dto.response.ActivityResponse;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import com.fitness.activityservice.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityService {

    private ActivityRepository activityRepository;

    public ActivityResponse trackActivity(ActivityRequest request) {
        try {
            Activity activity = Activity.builder()
                    .userId(request.getUserId())
                    .type(request.getType())
                    .duration(request.getDuration())
                    .caloriesBurned(request.getCaloriesBurned())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .additionalMetrics(request.getAdditionalMetrics())
                    .build();

            Activity savedActivity = activityRepository.save(activity);
            return mapToResponse(savedActivity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save activity: " + e.getMessage(), e);
        }
    }
    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setEndTime(activity.getEndTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }

    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivityById(String activityId) {

        return activityRepository.findById(activityId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new CustomException("Activity Not Found for the id:  "+ activityId));
    }
}
