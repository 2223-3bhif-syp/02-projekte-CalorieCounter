package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GoalTest {
    @Test
    void createGoal() {
        // arrange
        final LocalDateTime lcd = LocalDateTime.now().plusMonths(1);
        User user = new User("t.aichinger@gmx.at", "aichingert", "aichi123", 75, 170);
        Goal goal = new Goal(23.0, lcd, user);

        // act
        goal.setWeight(51.5);
        goal.setDeadline(goal.getDeadline().plusMonths(1));

        // assert
        assertThatThrownBy(() -> goal.setDeadline(LocalDateTime.of(2000,1,1,9,41)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Deadline cannot be in past");
        assertThat(goal.getDeadline()).isEqualTo(lcd.plusMonths(1));

        assertThatThrownBy(() -> goal.setWeight(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("weight cannot be 0 or less");
        assertThat(goal.getWeight()).isEqualTo(51.5);
    }
}