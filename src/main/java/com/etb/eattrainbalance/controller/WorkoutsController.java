package com.etb.eattrainbalance.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.etb.eattrainbalance.models.Workouts;
import com.etb.eattrainbalance.models.WorkoutRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller

public class WorkoutsController {

    @Autowired
    private WorkoutRepository workoutRepo;

    @GetMapping("/workouts")
    public String getWorkoutsPage(Model model) {
        System.out.println("Getting all users");
        List<Workouts> workouts = workoutRepo.findAll();
        
        model.addAttribute("work", workouts);
        return "workouts";
    }

    @PostMapping("/workouts/add")
    public String addWorkout(@RequestParam Map<String, String> newworkout, HttpServletResponse response) {
        System.out.println("ADD workout");
        String newWorkoutName = newworkout.get("name");
        String newWorkoutType = newworkout.get("type");
        String newWorkoutDifficulty = newworkout.get("difficulty");
        workoutRepo.save(new Workouts(newWorkoutName, newWorkoutType, newWorkoutDifficulty));
        response.setStatus(201);
        return "workouts";
    }
}
