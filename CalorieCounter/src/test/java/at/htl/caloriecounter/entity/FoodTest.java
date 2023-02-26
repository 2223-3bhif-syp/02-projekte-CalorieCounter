package at.htl.caloriecounter.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FoodTest {

    @Test
    void createFood() {
        // arrange


        // act
        Food food = new Food("tomate", 21.0);

        // assert
        assertThat(food.getCalories()).isEqualTo(21.0);


    }
}
