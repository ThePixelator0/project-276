package com.etb.eattrainbalance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.etb.eattrainbalance.models.Food;
import com.etb.eattrainbalance.models.Plans;
import com.etb.eattrainbalance.models.PlansRepository;
import com.etb.eattrainbalance.persistence.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class PlansController {

    @Autowired
    private PlansRepository plansRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/plans")
    public String getPlansPage(Model model) {
        System.out.println("getting all plans");
        List<Plans> plans = plansRepo.findAll();

        model.addAttribute("plans", plans);

        return "plans";
    }

    @GetMapping("/createplan")
    public String createPlanPage(){
        return"createplan";
    }

    @PostMapping("/plans/add")
    public String addPlan(@RequestParam Map<String, String> newplan, HttpServletResponse response){
        System.out.println("add new plan");
        
        String newtitle = newplan.get("planTitle");
        int newPlanUid = Integer.parseInt(newplan.get("userId"));
        List<Food> food = new ArrayList<>();

        // add error checking


        plansRepo.save(new Plans(newtitle,newPlanUid,food));
        response.setStatus(201);
        return"redirect:/plans";
    }

    @PostMapping("/plans/delete")
    public String deletePlan(@RequestParam("planId") String planId, HttpServletResponse response){
        System.out.println("delete user" + planId);
        plansRepo.deleteById(Integer.parseInt(planId));
        // int newUID = Integer.parseInt(newuser.get("uid"));
        // studentRepo.deleteById(newUID);
        // response.setStatus(201);
        return "redirect:/plans";
    }


}

