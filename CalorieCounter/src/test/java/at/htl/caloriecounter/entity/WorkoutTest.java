package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkoutTest {
    @Test
    void createUser() {
        // arrange
        User user = new User("t.aichinger@gmx.at", "aichingert", "aichi123", 75, 170);
        Workout workout = new Workout("Sport", 10.0, 5.30, user);

        // act

        // assert
        assertThat(workout.getName().equals("Sport"));
    }
}
