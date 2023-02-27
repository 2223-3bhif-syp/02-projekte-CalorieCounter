package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class GoalTest {
    @Test
    void createGoal() {
        // arrange


        // act
        Goal goal = new Goal(23.0, LocalDateTime.of(2023, 2, 20, 0, 0));

        // assert
        assertThat(goal.getWeight()).isEqualTo(23.0);


    }
}