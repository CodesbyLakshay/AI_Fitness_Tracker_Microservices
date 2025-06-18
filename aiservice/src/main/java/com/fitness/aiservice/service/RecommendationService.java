package com.fitness.aiservice.service;

import com.fitness.aiservice.CustomException;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final UserValidationService userValidationService;

    public List<Recommendation> getUserRecommendation(String userId) {
        if(!userValidationService.validateUser(userId))
            throw new CustomException("Invalid User");
      return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new CustomException("No Recommendation Found for the ActivityId : {}"+ activityId));
    }
}
