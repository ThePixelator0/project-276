package com.etb.eattrainbalance.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.lang.Integer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.etb.eattrainbalance.models.Workouts;
import com.etb.eattrainbalance.modal.security.UserPrincipal;
import com.etb.eattrainbalance.models.WorkoutRepository;

import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.repository.UserRepository;


import jakarta.servlet.http.HttpServletResponse;

@Controller

public class WorkoutsController {

    @Autowired
    private WorkoutRepository workoutRepo;

    @Autowired
    private UserRepository userRepo;


    @GetMapping("/workouts")
    public String getWorkoutsPage(Model model) {
        System.out.println("Getting all users");
        List<Workouts> workouts = workoutRepo.findAll();
        Long userID =  ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        int workoutsCounter = workoutRepo.countByUserID(userID.intValue());
        model.addAttribute("work", workouts);
        model.addAttribute("workoutsCounter", workoutsCounter);
        return "workouts";
    }

    @PostMapping("/workouts/add")
    public String addWorkout(@RequestParam Map<String, String> newworkout, HttpServletResponse response) {
        System.out.println("ADD workout");
        String newWorkoutName = newworkout.get("name");
        String newWorkoutType = newworkout.get("type");
        String newWorkoutDifficulty = newworkout.get("difficulty"); 
        int newWorkoutuid = Integer.parseInt(newworkout.get("userid"));
        workoutRepo.save(new Workouts(newWorkoutName, newWorkoutType, newWorkoutDifficulty, newWorkoutuid));
        response.setStatus(201);
        return "workouts";
    }

    // @PostMapping("/workouts/{uid}/delete")
    // public String deleteWorkout(@PathVariable("uid") String uid) {
    //     System.out.println("workout being deleted");
    //     Workouts workout = workoutRepo.findById(Integer.parseInt(uid)).orElse(null);
    //     workoutRepo.delete(workout);
    //     return "workouts";
    // }

    @PostMapping("/workouts/delete")
    public String deleteWorkout(@RequestParam("workoutId") String workoutId, HttpServletResponse response){
        System.out.println("delete workout " + workoutId);
        workoutRepo.deleteById(Integer.parseInt(workoutId));
        // int newUID = Integer.parseInt(newuser.get("uid"));
        // studentRepo.deleteById(newUID);
        // response.setStatus(201);
        return "redirect:/workouts";
    }
}
