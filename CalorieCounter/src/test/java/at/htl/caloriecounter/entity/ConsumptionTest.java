package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsumptionTest {
    @Test
    void createConsumption() {
        // arrange


        // act
        User user = new User("m.muster@gmail.com", "musti", "1234", 80.3, 176, new Goal(70.0, LocalDateTime.of(2023, 3, 20, 0, 0)));
        Food food = new Food("tomate", 21.0);
        Consumption consumption = new Consumption(user, food, 2);

        // assert
        assertThat(consumption.getUser().getWeight()).isEqualTo(80.3);


    }
}
