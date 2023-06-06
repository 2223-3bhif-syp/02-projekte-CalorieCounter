package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.Consumption;
import at.htl.caloriecounter.entity.Food;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.repositories.ConsumptionRepository;
import at.htl.caloriecounter.repositories.Database;
import at.htl.caloriecounter.repositories.FoodRepository;
import at.htl.caloriecounter.repositories.UserRepository;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
public class ConsumptionRepositoryTest {
    UserRepository userRepository = new UserRepository();
    FoodRepository foodRepository = new FoodRepository();

    ConsumptionRepository consumptionRepository = new ConsumptionRepository();
    DataSource dataSource = Database.getDataSource();
    private static final String tableName = "CC_CONSUMPTION";
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
void test_save_save_consumption_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                17
        );
        Food food = new Food(
                "tomato",
                21.0
        );

        Consumption consumption = new Consumption(
                user,
                food,
                3
        );

        // act
        foodRepository.save(food);
        userRepository.save(user);
        consumptionRepository.save(consumption);

        // assert
        printTable();

        assertThat(table).exists()
                .row(0)
                .column("C_ID").value().isEqualTo(consumption.getId())
                .column("C_F_ID").value().isEqualTo(consumption.getFood().getId())
                .column("C_U_ID").value().isEqualTo(consumption.getUser().getId())
                .column("C_AMOUNT").value().isEqualTo(consumption.getAmount());
    }

    @Test
    void test_delete_delete_consumption_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                17
        );
        Food food = new Food(
                "tomato",
                21.0
        );

        Consumption consumption = new Consumption(
                user,
                food,
                3
        );

        // act
        foodRepository.save(food);
        userRepository.save(user);
        consumptionRepository.save(consumption);

        printTable();

        consumptionRepository.delete(consumption.getId());

        // assert
        printTable();

        assertThat(table).isEmpty();
    }

    @Test
    void test_update_update_consumption_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                17
        );
        Food food = new Food(
                "tomato",
                21.0
        );

        Consumption consumption = new Consumption(
                user,
                food,
                3
        );

        // act
        foodRepository.save(food);
        userRepository.save(user);
        consumptionRepository.save(consumption);
        consumption.setAmount(5);

        printTable();

        consumptionRepository.update(consumption);

        // assert
        printTable();

        assertThat(table).exists()
                .row(0)
                .column("C_ID").value().isEqualTo(consumption.getId())
                .column("C_F_ID").value().isEqualTo(consumption.getFood().getId())
                .column("C_U_ID").value().isEqualTo(consumption.getUser().getId())
                .column("C_AMOUNT").value().isEqualTo(consumption.getAmount());
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}
