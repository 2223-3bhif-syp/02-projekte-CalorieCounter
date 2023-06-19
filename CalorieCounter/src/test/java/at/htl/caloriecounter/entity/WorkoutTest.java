package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkoutTest {
    @Test
    void createUser() {
        // arrange
        User user = new User("t.aichinger@gmx.at", "aichingert", "aichi123", 75, 170, LocalDate.of(2006, 5, 5));
        Workout workout = new Workout("Sport", 10.0, 5.30, user);

        // act
        workout.setDuration(50);
        workout.setCalories(40000);

        // assert
        assertThat(workout.getName().equals("Sport"));
        assertThat(workout.getUser().getUsername()).isEqualTo("aichingert");

        assertThatThrownBy(() -> workout.setDuration(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("duration cannot be less than zero");
        assertThat(workout.getDuration()).isEqualTo(50);

        assertThatThrownBy(() -> workout.setCalories(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("calories cannot be less than zero");
        assertThat(workout.getCalories()).isEqualTo(40000);
    }
}
