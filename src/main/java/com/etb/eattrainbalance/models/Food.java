package com.etb.eattrainbalance.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Food {
    String name;
    String color;
    String day;
    
    public Food() {
    }

    public Food(String name, String color, String day) {
        this.name = name;
        this.color = color;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    
}
