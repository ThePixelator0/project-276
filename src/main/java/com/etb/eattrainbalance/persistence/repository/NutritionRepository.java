package com.etb.eattrainbalance.persistence.repository;

import com.etb.eattrainbalance.persistence.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
