package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    void createUser() {
        // arrange


        // act
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176, new Goal(70.0, LocalDate.of(2023, 3, 20)));

        // assert
        assertThat(user.getWeight()).isEqualTo(80.3);


    }
}
