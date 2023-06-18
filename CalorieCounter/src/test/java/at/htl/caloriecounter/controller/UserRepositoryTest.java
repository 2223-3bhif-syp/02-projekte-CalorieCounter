package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.repositories.Database;
import at.htl.caloriecounter.repositories.UserRepository;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    DataSource dataSource = Database.getDataSource();
    private static final String tableName = "CC_USER";
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
    void test_save_save_user_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );

        // act
        userRepository.save(user);

        // assert
        printTable();
        assertThat(table).exists()
                .row(0)
                .column("U_EMAIL").value().isEqualTo(user.getEmail())
                .column("U_PASSWORD").value().isEqualTo(user.getPassword())
                .column("U_USERNAME").value().isEqualTo(user.getUsername())
                .column("U_WEIGHT").value().isEqualTo(user.getWeight())
                .column("U_HEIGHT").value().isEqualTo(user.getHeight());
    }

    @Test
    void test_save_save_null_and_check_database_ok() {
        // arrange

        // act
        printTable();

        // assert
        printTable();
        assertThatThrownBy(() -> {
            userRepository.save(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void test_delete_delete_user_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );

        // act
        userRepository.save(user);

        printTable();

        userRepository.delete(user.getId());

        // assert
        printTable();
        assertThat(table).isEmpty();
    }

    @Test
    void test_update_update_user_and_check_database_ok() {
        // arrange

        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );

        // act
        userRepository.save(user);
        user.setWeight(75.0);

        printTable();

        userRepository.update(user);

        // assert
        printTable();
        assertThat(table).exists()
                .row(0)
                .column("U_EMAIL").value().isEqualTo(user.getEmail())
                .column("U_PASSWORD").value().isEqualTo(user.getPassword())
                .column("U_USERNAME").value().isEqualTo(user.getUsername())
                .column("U_WEIGHT").value().isEqualTo(user.getWeight())
                .column("U_HEIGHT").value().isEqualTo(user.getHeight());
    }

    @Test
    void test_update_update_null_ok() {
        // arrange

        // act
        printTable();

        // assert
        printTable();
        assertThatThrownBy(() -> {
            userRepository.update(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void test_findAll_find_every_user_ok() {
        // arrange
        User user1 = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );
        User user2 = new User(
                "m.grub@example.com",
                "mo",
                "123",
                68,
                179,
                LocalDate.of(2006, 5, 5)
        );
        User user3 = new User(
                "t.aich@example.com",
                "tobi",
                "123",
                73,
                170,
                LocalDate.of(2006, 5, 5)
        );
        User user4 = new User(
                "m.brein@example.com",
                "max",
                "123",
                77,
                174,
                LocalDate.of(2006, 5, 5)
        );

        // act
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        printTable();

        List<User> users = userRepository.findAll();

        // assert
        printTable();
        assertThat(users)
                .hasSize(4)
                .contains(user1, user2, user3, user4);
    }

    @Test
    void test_findById_find_specific_user_ok() {
        // arrange
        User user1 = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );
        User user2 = new User(
                "m.grub@example.com",
                "mo",
                "123",
                68,
                179,
                LocalDate.of(2006, 5, 5)
        );
        User user3 = new User(
                "t.aich@example.com",
                "tobi",
                "123",
                73,
                170,
                LocalDate.of(2006, 5, 5)
        );
        User user4 = new User(
                "m.brein@example.com",
                "max",
                "123",
                77,
                174,
                LocalDate.of(2006, 5, 5)
        );

        // act
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        User user1FromDb = userRepository.findById(1);
        User user2FromDb = userRepository.findById(2);
        User user3FromDb = userRepository.findById(3);
        User user4FromDb = userRepository.findById(4);

        // assert
        printTable();
        assertThat(user1)
                .usingRecursiveAssertion()
                .isEqualTo(user1FromDb);

        assertThat(user2)
                .usingRecursiveAssertion()
                .isEqualTo(user2FromDb);

        assertThat(user3)
                .usingRecursiveAssertion()
                .isEqualTo(user3FromDb);

        assertThat(user4)
                .usingRecursiveAssertion()
                .isEqualTo(user4FromDb);
    }

    @Test
    void test_findById_find_not_existing_user_ok() {
        // arrange

        // act
        printTable();

        User user1FromDb = userRepository.findById(1);

        // assert
        printTable();
        assertThat(user1FromDb).isNull();
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}