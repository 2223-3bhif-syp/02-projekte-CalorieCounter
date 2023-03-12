package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Goal;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.entity.Workout;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

public class WorkoutRepositoryTest {
    UserRepository userRepository = new UserRepository();
    WorkoutRepository workoutRepository = new WorkoutRepository();

    @Test
    void insertWorkout_ok() {
        // arrange
        DataSource ds = Database.getDataSource();
        User user = new User("t.aichinger@gmx.at", "aichingert", "aichi123", 75, 170);
        userRepository.save(user);

        Workout workout = new Workout("Laufen", 250, 1, user);
        Table table = new Table(ds, "CC_WORKOUT");

        // act
        workoutRepository.save(workout);

        // assert
        output(table).toConsole();
        assertThat(table).exists()
                .row(0)
                //.column("CUSTOMER_ID").value().isEqualTo(4)
                .column("W_NAME").value().isEqualTo("Laufen");
    }
}
