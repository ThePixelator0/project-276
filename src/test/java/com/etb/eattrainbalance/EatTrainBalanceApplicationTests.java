package com.etb.eattrainbalance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import com.etb.eattrainbalance.models.Workouts;

@SpringBootTest
class EatTrainBalanceApplicationTests {

    @Test
    void contextLoads() {
    }

    //  @Test
    // public void testGetWorkoutsPage() {
    //     // Arrange
    //     List<Workouts> workouts = new ArrayList<>();
    //     when(workoutRepo.findAll()).thenReturn(workouts);
    //     when(model.addAttribute(eq("work"), eq(workouts))).thenReturn(model);
    //     Long userID = 123L;
    //     UserPrincipal userPrincipal = new UserPrincipal(userID, "username", "password");
    //     SecurityContextHolder.getContext().setAuthentication(userPrincipal);

    //     // Act
    //     String result = workoutsController.getWorkoutsPage(model);

    //     // Assert
    //     verify(workoutRepo, times(1)).findAll();
    //     verify(model, times(1)).addAttribute("work", workouts);
    //     assertEquals("workouts", result);
    // }

    // @Test
    // public void testAddWorkout_Success() {
    //     // Arrange
    //     Map<String, String> newWorkout = new HashMap<>();
    //     newWorkout.put("name", "Workout 1");
    //     newWorkout.put("type", "Strength");
    //     newWorkout.put("difficulty", "Intermediate");
    //     newWorkout.put("userid", "123");

    //     // Act
    //     String result = workoutsController.addWorkout(newWorkout, response);

    //     // Assert
    //     verify(workoutRepo, times(1)).save(any(Workouts.class));
    //     assertEquals(201, response.getStatus());
    //     assertEquals("workouts", result);
    // }

    // @Test
    // public void testDeleteWorkout_Success() {
    //     // Arrange
    //     String workoutId = "1";

    //     // Act
    //     String result = workoutsController.deleteWorkout(workoutId, response);

    //     // Assert
    //     verify(workoutRepo, times(1)).deleteById(Integer.parseInt(workoutId));
    //     assertEquals("redirect:/workouts", result);
    // }

}
