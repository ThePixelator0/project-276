package com.etb.eattrainbalance.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WorkoutsController {
    @GetMapping("/workouts")
    public String getWorkoutsPage() {
        return "workouts";
    }

    @GetMapping("/exercises")
    public String getExercises(Model model) throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/exercises");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        String fact = root.path("fact").asText();
        model.addAttribute("fact", fact);
        return "exercises";
    }
}
