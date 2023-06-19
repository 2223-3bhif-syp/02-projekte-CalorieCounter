package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class ConsumptionTest {
    @Test
    void createConsumption() {
        // arrange
        String email = "m.muster@gmail.com";
        User user = new User(email,
                "musti",
                "1234",
                80.3,
                176,
                LocalDate.of(2006, 5, 5));
        Food food = new Food("tomate", 21.0);
        Consumption consumption = new Consumption(user, food, 2);

        // act
        consumption.setAmount(20);

        // assert
        assertThatThrownBy(() -> consumption.setAmount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("calories cannot be 0 or less");
        assertThat(consumption.getAmount()).isEqualTo(20);
    }
}
