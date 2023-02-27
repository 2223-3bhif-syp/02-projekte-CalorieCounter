package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    void createUser() {
        // arrange
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176, new Goal(70.0, LocalDateTime.of(2023, 3, 20, 0, 0)));

        // act

        // assert
        assertThat(user.getWeight()).isEqualTo(80.3);
    }

    @Test
    void checkCurrentWeight() {
        // arrange
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176, new Goal(70.0, LocalDateTime.of(2023, 3, 20, 0, 0)));

        // act

        // assert
        assertThat(user.getWeight()).isEqualTo(80.3);
    }

    @Test
    void checkCurrentGoal() {
        // arrange
        Goal goal = new Goal(70.0, LocalDateTime.of(2023, 3, 20, 0, 0));
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176, goal);
        Goal otherGoal = new Goal(70.0, LocalDateTime.of(2023, 3, 20, 0, 0));
        // act

        // assert
        assertThat(user.getGoal() == goal);
        assertThat(user.getGoal() != otherGoal);
    }
}
