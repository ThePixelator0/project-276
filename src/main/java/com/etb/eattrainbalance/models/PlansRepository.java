package com.etb.eattrainbalance.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans,Integer>{
    List<Workouts> findByTitle(String title);
}
