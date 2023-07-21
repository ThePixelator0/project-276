package com.etb.eattrainbalance.controller;

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

        Integer planUid = Integer.parseInt(newfood.get("planId"));

        Plans plan = plansRepo.findById(planUid).orElse(null);

        // save to database
        if(plan != null){
            plan.getFoods().add(new Food(newFoodName, newFoodColor, newFoodDay));
            plansRepo.save(plan);
        }

        System.out.println("succesfully added food: " + newFoodName);
        return "redirect:/plans";
    }
}
