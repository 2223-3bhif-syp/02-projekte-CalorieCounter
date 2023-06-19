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
    private static final String tableName = "CC_FOOD";
    Table table = new Table();

    void printTable() {
        table = new Table(dataSource, tableName);
        output(table).toConsole();
    }

    @BeforeEach
    void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);

        printTable();
    }

    @Test
    void test_save_save_food_and_check_database_ok() {
        // arrange
        Food food = new Food(
                "tomato",
                21.0
        );

        // act
        foodRepository.save(food);

        // assert
        printTable();

        assertThat(table).exists()
                .row(0)
                .column("F_NAME").value().isEqualTo(food.getName())
                .column("F_CALORIES").value().isEqualTo(food.getCalories());
    }

    @Test
    void test_save_save_multiply_food_and_check_database_ok() {
        // arrange
        Food food1 = new Food(
                "orange",
                47.0
        );
        Food food2 = new Food(
                "banana",
                89.0
        );

        // act
        foodRepository.save(food1);

        printTable();

        foodRepository.save(food2);

        // assert
        printTable();

        assertThat(table).exists().hasNumberOfRows(2)
                .row(0)
                .column("F_NAME").value().isEqualTo(food1.getName())
                .column("F_CALORIES").value().isEqualTo(food1.getCalories())
                .row(1)
                .column("F_NAME").value().isEqualTo(food2.getName())
                .column("F_CALORIES").value().isEqualTo(food2.getCalories());
    }
    @Test
    void test_delete_delete_food_and_check_database_ok() {
        // arrange
        Food food = new Food(
                "tomato",
                21.0
        );

        // act
        foodRepository.save(food);

        printTable();

        foodRepository.delete(food.getId());

        // assert
        printTable();

        assertThat(table).isEmpty();
    }

    @Test
    void test_update_update_food_and_check_database_ok() {
        // arrange
        Food food = new Food(
                "tomato",
                20.0
        );

        // act
        foodRepository.save(food);
        food.setCalories(21.0);

        printTable();

        foodRepository.update(food);

        // assert
        printTable();

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
