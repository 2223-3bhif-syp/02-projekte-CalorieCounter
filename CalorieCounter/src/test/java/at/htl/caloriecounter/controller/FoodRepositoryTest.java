package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.Food;
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
                .column("F_NAME").value().isEqualTo("tomato")
                .column("F_CALORIES").value().isEqualTo(21.0);
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
                .column("F_NAME").value().isEqualTo("orange")
                .column("F_CALORIES").value().isEqualTo(47.0)
                .row(1)
                .column("F_NAME").value().isEqualTo("banana")
                .column("F_CALORIES").value().isEqualTo(89.0);
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}
