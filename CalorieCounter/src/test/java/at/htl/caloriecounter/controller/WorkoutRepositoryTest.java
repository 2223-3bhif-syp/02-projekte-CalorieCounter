package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.entity.Workout;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class WorkoutRepositoryTest {
    UserRepository userRepository = new UserRepository();
    WorkoutRepository workoutRepository = new WorkoutRepository();
    DataSource dataSource = Database.getDataSource();

    @BeforeEach
    void createTables() {
        SqlRunner.runScript(SqlScript.CREATE);
    }

    @Test
    void test_insert_workout() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);
        Workout workout = new Workout("Laufen", 250, 1, user);

        // act
        userRepository.save(user);
        workoutRepository.save(workout);

        // assert
        Table table = new Table(dataSource, "CC_WORKOUT");
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                .column("W_U_ID").value().isEqualTo(workout.getId())
                .column("W_NAME").value().isEqualTo(workout.getName())
                .column("W_CALORIES").value().isEqualTo((workout.getCalories()))
                .column("W_DURATION").value().isEqualTo(workout.getDuration());
    }

    @Test
    void test_delete_workout() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);
        Workout workout = new Workout("Laufen", 250, 1, user);

        // act
        userRepository.save(user);
        workoutRepository.save(workout);

        workoutRepository.delete(workout.getId());

        // assert
        Table table = new Table(dataSource, "CC_WORKOUT");
        output(table).toConsole();
        assertThat(table).isEmpty();
    }

    @Test
    void test_update_workout() {
        // arrange
        User user = new User("f.stro@example.com", "f.stro", "123", 70, 175);
        Workout workout = new Workout("Laufen", 250, 1, user);

        // act
        userRepository.save(user);
        workoutRepository.save(workout);
        workout.setDuration(2);
        workout.setCalories(500);

        workoutRepository.update(workout);

        // assert
        Table table = new Table(dataSource, "CC_WORKOUT");
        output(table).toConsole();
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
