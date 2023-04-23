package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.Food;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class FoodRepositoryTest {
    FoodRepository foodRepository = new FoodRepository();
    DataSource dataSource = Database.getDataSource();

    @BeforeAll
    static void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);
    }

    @Test
    void testInsertFood() {
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

    @AfterAll
    static void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}
