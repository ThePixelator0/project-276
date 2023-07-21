package com.etb.eattrainbalance.models;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="plans")

public class Plans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String title;
    private int userID;
    
    // add lists of days with each day of lists of food
    @ElementCollection
    @CollectionTable(name = "plan_foods", joinColumns = @JoinColumn(name = "plan_id"))
    private List<Food> foods;

    public Plans() {
    }

    public Plans(String title, int userID, List<Food> foods) {
        this.title = title;
        this.userID = userID;
        this.foods = foods;
    }

    public String getTitle() {
        return title;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    
    
}
