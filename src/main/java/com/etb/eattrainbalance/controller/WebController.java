package com.etb.eattrainbalance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String defaultPage() {
        return "index";
    }
    //may need to move this to Nutrition controller, only here so page editor doesn't need to authenticate to access
    @GetMapping("/nutrition")
    public String getNutritionPage() {
        return "nutrition-tracker";
    }

    //may need to move this to a dashboard controller, only here so page editor doesn't need to authenticate to access
    @GetMapping("/dashboard")
    public String homeDashboard(){
        return "home-dashboard";
    }

    @GetMapping("/login-page")
    public String getLoginPage(){
        return "login";
    }
}
