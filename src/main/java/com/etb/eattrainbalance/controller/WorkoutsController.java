package com.etb.eattrainbalance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkoutsController {
     @GetMapping("/workouts")
    public String getWorkoutsPage() {
        return "workouts";
    }
}
