package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.Goal;
import at.htl.caloriecounter.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.time.LocalDateTime;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class GoalRepositoryTest {
    UserRepository userRepository = new UserRepository();
    GoalRepository goalRepository = new GoalRepository();

    @BeforeAll
    static void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);
    }

    @Test
    void insertGoal_ok() {
        // arrange
        DataSource ds = Database.getDataSource();
        User user = new User("t.aichinger@gmx.at", "aichingert", "aichi123", 75, 170);
        userRepository.save(user);

        Goal goal = new Goal(70.2, LocalDateTime.of(2023, 3, 20, 0, 0), user);
        Table table = new Table(ds, "CC_GOAL");

        // act
        goalRepository.save(goal);

        // assert
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("G_WEIGHT").value().isEqualTo(70.2);
    }

    @AfterAll
    static void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}