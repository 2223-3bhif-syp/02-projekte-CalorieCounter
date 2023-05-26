package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.repositories.Database;
import at.htl.caloriecounter.repositories.UserRepository;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    DataSource dataSource = Database.getDataSource();

    @BeforeEach
    void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);
    }

    @Test
    void test_insert_user() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);

        // act
        userRepository.save(user);

        // assert
        Table table = new Table(dataSource, "CC_USER");
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("U_EMAIL").value().isEqualTo(user.getEmail())
                .column("U_PASSWORD").value().isEqualTo(user.getPassword())
                .column("U_USERNAME").value().isEqualTo(user.getUsername())
                .column("U_WEIGHT").value().isEqualTo(user.getWeight())
                .column("U_HEIGHT").value().isEqualTo(user.getHeight());
    }

    @Test
    void test_delete_user() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);

        // act
        userRepository.save(user);
        userRepository.delete(user.getId());

        // assert
        Table table = new Table(dataSource, "CC_USER");
        output(table).toConsole();
        assertThat(table).isEmpty();
    }

    @Test
    void test_update_user() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);

        // act
        userRepository.save(user);
        user.setWeight(75.0);
        userRepository.update(user);

        // assert
        Table table = new Table(dataSource, "CC_USER");
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("U_EMAIL").value().isEqualTo(user.getEmail())
                .column("U_PASSWORD").value().isEqualTo(user.getPassword())
                .column("U_USERNAME").value().isEqualTo(user.getUsername())
                .column("U_WEIGHT").value().isEqualTo(user.getWeight())
                .column("U_HEIGHT").value().isEqualTo(user.getHeight());
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}