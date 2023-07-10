package com.etb.eattrainbalance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.etb.eattrainbalance.models.Plans;
import com.etb.eattrainbalance.models.PlansRepository;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class PlansController {

    @Autowired
    private PlansRepository plansRepo;

    @GetMapping("/plans")
    public String getPlansPage(Model model) {
        System.out.println("getting all plans");
        List<Plans> plans = plansRepo.findAll();

        model.addAttribute("plans", plans);

        return "plans";
    }

    @PostMapping("/plans/add")
    public String addPlan(@RequestParam Map<String, String> newplan, HttpServletResponse response){
        System.out.println("add new plan");
        
        String newtitle = newplan.get("title");

        // add error checking


        plansRepo.save(new Plans("My Meal Plan #1"));
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

