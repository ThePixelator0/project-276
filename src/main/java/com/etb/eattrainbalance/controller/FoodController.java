package com.etb.eattrainbalance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etb.eattrainbalance.models.Food;
import com.etb.eattrainbalance.models.Plans;
import com.etb.eattrainbalance.models.PlansRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodController {
    
    @Autowired
    private PlansRepository plansRepo;

    @PostMapping("/submitFood")
    public String submitFood(@RequestParam Map<String, String> newfood, HttpServletResponse response) {

        String newFoodName = newfood.get("foodName");
        String newFoodColor = newfood.get("foodColor");
        String newFoodDay = newfood.get("foodDay");
        Integer newFoodserve = Integer.parseInt(newfood.get("foodServe"));
        Integer newFoodcal = Integer.parseInt(newfood.get("foodCal"));

        Integer planUid = Integer.parseInt(newfood.get("planId"));
        Plans plan = plansRepo.findById(planUid).orElse(null);
        

        // save to database
        if(plan != null){
            plan.getFoods().add(new Food(newFoodName, newFoodColor, newFoodDay,newFoodserve,newFoodcal));
            plansRepo.save(plan);
        }

        System.out.println("succesfully added food: " + newFoodName);
        return "redirect:/plans";
    }

    @PostMapping("/deleteFoods")
    public String deleteFood(@RequestParam Map<String, String> newfood, HttpServletResponse response) {

        Integer plansId = Integer.parseInt(newfood.get("planId"));
        // Fetch the Plans entity from the database
        Plans plans = plansRepo.findById(plansId).orElse(null);
        System.out.println(plansId);
        if (plans != null) {
            // Get the List<Food> from the Plans entity
            List<Food> foods = plans.getFoods();

            // Find the Food object to delete by its id or any other criteria
            Food foodToDelete = null;
            for (Food food : foods) {
                if (food.getName().equals(newfood.get("foodName"))) {
                    foodToDelete = food;
                    break;
                }
            }

            if (foodToDelete != null) {
                // Remove the Food object from the List<Food>
                foods.remove(foodToDelete);

                // Save the updated Plans entity back to the database
                plansRepo.save(plans);
            }
        }
    
        return "redirect:/plans";
    }

}
