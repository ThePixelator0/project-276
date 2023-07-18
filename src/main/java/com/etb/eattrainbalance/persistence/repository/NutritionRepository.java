package com.etb.eattrainbalance.persistence.repository;

import com.etb.eattrainbalance.persistence.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import java.util.List;


@Controller
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    List<Nutrition> findByUserIdAndMealType(Long userId, String mealType);
}
