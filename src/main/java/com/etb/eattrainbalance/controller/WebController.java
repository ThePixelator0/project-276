package com.etb.eattrainbalance.controller;

import org.springframework.ui.Model;

import com.etb.eattrainbalance.modal.security.UserPrincipal;
import com.etb.eattrainbalance.models.WorkoutRepository;
import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepo;

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
    public String homeDashboard(Model model){
        Long userID =  ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        int workoutsCounter = workoutRepo.countByUserID(userID.intValue());
        model.addAttribute("workoutsCounter", workoutsCounter);
        return "home-dashboard";
    }

    //may need to move this to a admin controller, only here so page editor doesn't need to authenticate to access
    @GetMapping("/admin")
    public String adminController(Model model){
        model.addAttribute("us", userRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/user/delete/{id}")
    public String userDelete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("user/update/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.findById(id));
        return "edit-user";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @RequestParam Map <String, String> updateUser){
        String name = updateUser.get("name");
        String email = updateUser.get("email");

        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElse(null);
        if(user != null){
            user.setName(name);
            user.setEmail(email);
        }

        userRepository.save(user);
        return "redirect:/admin";
    }
}
