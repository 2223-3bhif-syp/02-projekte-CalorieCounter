package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    void createUser() {
        // arrange
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176);

        // act

        // assert
        assertThat(user.getEmail().equals("m.muster@gmail.com"));
    }

    @Test
    void checkCurrentWeight() {
        // arrange
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176);

        // act

        // assert
        assertThat(user.getWeight()).isEqualTo(80.3);
    }

    @Test
    void checkCurrentGoal() {
        // arrange

        // act

        // assert

    }
}
