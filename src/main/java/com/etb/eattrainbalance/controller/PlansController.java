package com.etb.eattrainbalance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlansController {

    @GetMapping("/plans")
    public String getPlansPage() {
        return "plans";
    }
}

