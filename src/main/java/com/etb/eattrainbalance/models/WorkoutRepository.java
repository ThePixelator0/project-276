package com.etb.eattrainbalance.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workouts,Integer> {
    List<Workouts> findByWorkoutType(String workoutType);

    int countByUserID(Integer userID);
}
