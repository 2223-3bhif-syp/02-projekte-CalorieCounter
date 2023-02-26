package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class GoalTest {
    @Test
    void createGoal() {
        // arrange


        // act
        Goal goal = new Goal(id, 23.0, LocalDate.of(2023, 2, 20));

        // assert
        assertThat(goal.getWeight()).isEqualTo(23.0);


    }
}