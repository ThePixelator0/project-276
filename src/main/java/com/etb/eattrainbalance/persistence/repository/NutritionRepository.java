package com.etb.eattrainbalance.persistence.repository;

import com.etb.eattrainbalance.persistence.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    List<Nutrition> findByUserIdAndMealType(Long userId, String mealType);
    List<Nutrition> findByUserId(Long userId);

    // Get all Nutrition data from the past year for the given userId
    List<Nutrition> findByUserIdAndCreationDateTimeAfter(Long userId, LocalDateTime date);

    // Get all Nutrition data within a specific date range for the given userId
    List<Nutrition> findByUserIdAndCreationDateTimeBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
