package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    @Test
    void createUser() {
        // arrange
        String email = "m.muster@gmail.com";
        User user = new User(email, "musti", "1234", 80.3, 176);

        // act
        user.setWeight(100);

        // assert
        String invalidEmail = ".some@.invalid..email!";
        assertThatThrownBy(() -> user.setEmail(invalidEmail))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid email " + invalidEmail);
        assertThat(user.getEmail()).isEqualTo(email);

        assertThatThrownBy(() -> user.setHeight(-1)).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("height cannot be 0 or less");
        assertThat(user.getHeight()).isEqualTo(176);

        assertThatThrownBy(() -> user.setWeight(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("weight cannot be 0 or less");
        assertThat(user.getWeight()).isEqualTo(100);
    }
}
