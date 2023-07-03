package com.etb.eattrainbalance.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public class WorkoutRepository extends JpaRepository<Workouts,Integer> {
    List<Workouts> ;
}
