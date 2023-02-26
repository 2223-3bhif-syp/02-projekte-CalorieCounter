package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Food;
import at.htl.caloriecounter.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class FoodRepositoryTest {
    FoodRepository foodRepository = new FoodRepository();

    @Test
    void insertFood_ok() {
        // arrange
        DataSource ds = Database.getDataSource();
        Food food = new Food("tomate", 21.0);
        Table table = new Table(ds, "CC_FOOD");

        // act
        foodRepository.save(food);

        // assert
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("F_NAME").value().isEqualTo("tomate");


    }
}
