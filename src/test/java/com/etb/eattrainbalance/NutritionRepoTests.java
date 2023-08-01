package com.etb.eattrainbalance;

import com.etb.eattrainbalance.persistence.entity.Nutrition;
import com.etb.eattrainbalance.persistence.repository.NutritionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NutritionRepoTests {

    @Autowired
    private NutritionRepository nutritionRepo;

    @Test
    public void NutritionRepo_FindByUserIdAndMealType_ReturnsNutrition() {
        Nutrition nutrition = new Nutrition();
        nutrition.setUserId(1L);
        nutrition.setMealType("Breakfast");

        nutritionRepo.save(nutrition);

        List<Nutrition> foundNutrition = nutritionRepo.findByUserIdAndMealType(1L, "Breakfast");

        Assertions.assertThat(foundNutrition).isNotEmpty();
        Assertions.assertThat(foundNutrition.get(0).getMealType()).isEqualTo("Breakfast");
    }

    @Test
    public void NutritionRepo_FindByUserId_ReturnsNutrition() {
        Nutrition nutrition1 = new Nutrition();
        nutrition1.setUserId(1L);
        
        Nutrition nutrition2 = new Nutrition();
        nutrition2.setUserId(1L);

        nutritionRepo.save(nutrition1);
        nutritionRepo.save(nutrition2);

        List<Nutrition> foundNutrition = nutritionRepo.findByUserId(1L);

        Assertions.assertThat(foundNutrition).hasSize(2);
    }
}
