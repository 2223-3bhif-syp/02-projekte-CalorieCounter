package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Goal;
import at.htl.caloriecounter.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.time.LocalDateTime;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    GoalRepository goalRepository = new GoalRepository();

    @Test
    void insertUser_ok() {
        // arrange
        DataSource ds = Database.getDataSource();
        Goal goal = new Goal(70.2, LocalDateTime.of(2023, 3, 20, 0, 0));
        goalRepository.save(goal);

        User user = new User("abc", "test", "test", 10.0, 20.0, goal);
        Table table = new Table(ds, "CC_USER");

        // act
        userRepository.save(user);

        // assert
        output(table).toConsole();
        assertThat(table).exists()
                .row(2)
                //.column("CUSTOMER_ID").value().isEqualTo(4)
                .column("U_PASSWORD").value().isEqualTo("test");


    }
}