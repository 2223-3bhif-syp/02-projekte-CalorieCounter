package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.Goal;
import at.htl.caloriecounter.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

import static java.time.Month.*;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.assertions.impl.AssertionsOnValueEquality.isEqualTo;
import static org.assertj.db.output.Outputs.output;

public class GoalRepositoryTest {
    GoalRepository goalRepository = new GoalRepository();
    DataSource dataSource = Database.getDataSource();
    UserRepository userRepository = new UserRepository();

    @BeforeEach
    void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);
    }

    @Test
    void insert_goal() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);
        userRepository.save(user);

        Goal goal = new Goal(70.2, LocalDateTime.of(2023, 3, 20, 0, 0), user);

        // act
        goalRepository.save(goal);

        // assert
        Table table = new Table(dataSource, "CC_GOAL");
        output(table).toConsole();
        assertThat(table).exists()
                .column("G_WEIGHT").value().isEqualTo(goal.getWeight())
                .column("G_DEADLINE").value().isEqualTo(goal.getDeadline())
                .column("G_U_ID").value().isEqualTo(user.getId());
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}
