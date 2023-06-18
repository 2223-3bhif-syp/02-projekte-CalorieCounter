package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.entity.Workout;
import at.htl.caloriecounter.repositories.Database;
import at.htl.caloriecounter.repositories.UserRepository;
import at.htl.caloriecounter.repositories.WorkoutRepository;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class WorkoutRepositoryTest {
    UserRepository userRepository = new UserRepository();
    WorkoutRepository workoutRepository = new WorkoutRepository();
    DataSource dataSource = Database.getDataSource();
    private static final String tableName = "CC_WORKOUT";

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
    void test_save_save_workout_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );
        Workout workout = new Workout(
                "Laufen",
                250,
                1,
                user
        );

        // act
        userRepository.save(user);
        workoutRepository.save(workout);

        // assert
        printTable();

        assertThat(table).exists()
                .row(0)
                .column("W_U_ID").value().isEqualTo(workout.getId())
                .column("W_NAME").value().isEqualTo(workout.getName())
                .column("W_CALORIES").value().isEqualTo((workout.getCalories()))
                .column("W_DURATION").value().isEqualTo(workout.getDuration());
    }

    @Test
    void test_delete_delete_workout_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );
        Workout workout = new Workout(
                "Laufen",
                250,
                1,
                user
        );

        // act
        userRepository.save(user);
        workoutRepository.save(workout);

        printTable();

        workoutRepository.delete(workout.getId());

        // assert
        printTable();

        assertThat(table).isEmpty();
    }

    @Test
    void test_update_update_workout_and_check_database_ok() {
        // arrange
        User user = new User(
                "f.stro@example.com",
                "f.stro",
                "123",
                70,
                175,
                LocalDate.of(2006, 5, 5)
        );
        Workout workout = new Workout(
                "Laufen",
                250,
                1,
                user
        );

        // act
        userRepository.save(user);
        workoutRepository.save(workout);
        workout.setDuration(2);
        workout.setCalories(500);

        printTable();

        workoutRepository.update(workout);

        // assert
        printTable();

        assertThat(table).exists()
                .row(0)
                .column("W_U_ID").value().isEqualTo(workout.getId())
                .column("W_NAME").value().isEqualTo(workout.getName())
                .column("W_CALORIES").value().isEqualTo((workout.getCalories()))
                .column("W_DURATION").value().isEqualTo(workout.getDuration());
    }

    @AfterEach
    void dropTables() {
        SqlRunner.runScript(SqlScript.DROP);
    }
}
