package com.etb.eattrainbalance.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="workouts")

public class Workouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String workoutName;
    private String workoutType;
    private String workoutDifficulty;
    private int userID; 
    
    @Column(name = "createdat", insertable = false) // Exclude from insert statement
    private Timestamp createdat;

    public Workouts() {
    }

    public Workouts(String workoutName, String workoutType, String workoutDifficulty, int userID) {
        this.workoutName = workoutName;
        this.workoutType = workoutType;
        this.workoutDifficulty = workoutDifficulty;
        this.userID = userID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Timestamp getCreatedat() {
        return createdat;
    }

    // public void setCreatedat(Timestamp createdat) {
    //     this.createdat = createdat;
    // }
    
}