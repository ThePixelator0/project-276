package com.etb.eattrainbalance.controller;

import org.springframework.ui.Model;

import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @Autowired
    private UserRepository userRepository;

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
    public String adminController(Model model){
        model.addAttribute("us", userRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/user/delete/{id}")
    public String userDelete(@PathVariable("id") Long id, Model model){
        //Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);;
        return "redirect:/admin";
    }
}
