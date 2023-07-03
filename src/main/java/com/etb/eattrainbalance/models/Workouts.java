package com.etb.eattrainbalance.models;

import jakarta.persistence.*;

@Entity
@Table(name="workouts")

public class Workouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String workoutName;
    private String workoutType;
    private String workoutDifficulty;

    public Workouts() {
    }

    public Workouts(String workoutName, String workoutType, String workoutDifficulty) {
        this.workoutName = workoutName;
        this.workoutType = workoutType;
        this.workoutDifficulty = workoutDifficulty;
    }
    
    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
    }
}