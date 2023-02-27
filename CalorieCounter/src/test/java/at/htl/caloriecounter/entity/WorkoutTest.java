package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkoutTest {
    @Test
    void createUser() {
        // arrange
        Workout workout = new Workout("Sport", 10.0, 5.30);

        // act

        // assert
        assertThat(workout.getName().equals("Sport"));
    }
}
