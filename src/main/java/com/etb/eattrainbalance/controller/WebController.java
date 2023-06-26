package com.etb.eattrainbalance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String defaultPage() {
        System.out.println("index");
        return "index";
    }
}
