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

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @GetMapping("/register-admin")
    public String getRegisterAdminPage(){
        return "register-admin";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    //may need to move this to a dashboard controller, only here so page editor doesn't need to authenticate to access
    @GetMapping("/dashboard")
    public String homeDashboard(){
        return "home-dashboard";
    }

    //may need to move this to a admin controller, only here so page editor doesn't need to authenticate to access
    @GetMapping("/admin")
    public String adminController(){
        return "admin-dashboard";
    }
}
