package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FoodTest {

    @Test
    void createFood() {
        // arrange
        Food food = new Food("tomate", 21.0);

        // act
        food.setCalories(50);

        // assert
        assertThatThrownBy(() -> food.setCalories(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("calories cannot be less than zero");
        assertThat(food.getCalories()).isEqualTo(50);
    }
}
