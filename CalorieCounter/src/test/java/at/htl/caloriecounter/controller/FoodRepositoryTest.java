package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.Food;
import at.htl.caloriecounter.repositories.Database;
import at.htl.caloriecounter.repositories.FoodRepository;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class FoodRepositoryTest {
    FoodRepository foodRepository = new FoodRepository();
    DataSource dataSource = Database.getDataSource();

    @BeforeEach
    void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);
    }

    @Test
    void test_insert_food() {
        // arrange

        Food food = new Food("tomato", 21.0);

        // act
        foodRepository.save(food);

        // assert
        Table table = new Table(dataSource, "CC_FOOD");
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("F_NAME").value().isEqualTo(food.getName())
                .column("F_CALORIES").value().isEqualTo(food.getCalories());
    }

    @Test
    void test_insert_multiply_food() {
        // arrange

        Food food1 = new Food("orange", 47.0);
        Food food2 = new Food("banana", 89.0);

        // act
        foodRepository.save(food1);
        foodRepository.save(food2);

        // assert
        Table table = new Table(dataSource, "CC_FOOD");
        output(table).toConsole();
        assertThat(table).exists().hasNumberOfRows(2)
                .row(0)
                .column("F_NAME").value().isEqualTo(food1.getName())
                .column("F_CALORIES").value().isEqualTo(food1.getCalories())
                .row(1)
                .column("F_NAME").value().isEqualTo(food2.getName())
                .column("F_CALORIES").value().isEqualTo(food2.getCalories());
    }
    @Test
    void delete_food() {
        // arrange
        Food food = new Food("tomato", 21.0);
        foodRepository.save(food);
        System.out.println(food.getId());

        // act
        foodRepository.delete(food.getId());

        // assert
        Table table = new Table(dataSource, "CC_FOOD");
        output(table).toConsole();
        assertThat(table).isEmpty();
    }

    @Test
    void test_update_food() {
        // arrange
        Food food = new Food("tomato", 20.0);

        // act
        foodRepository.save(food);
        food.setCalories(21.0);
        foodRepository.update(food);

        // assert
        Table table = new Table(dataSource, "CC_FOOD");
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("F_NAME").value().isEqualTo(food.getName())
                .column("F_CALORIES").value().isEqualTo(food.getCalories());
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}
