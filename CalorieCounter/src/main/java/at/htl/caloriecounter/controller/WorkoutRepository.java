package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Goal;
import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.entity.Workout;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRepository implements Persistent<Workout> {
    private DataSource dataSource = Database.getDataSource();


    @Override
    public void save(Workout workout) {
        if (workout.getId() == null) {
            this.insert(workout);
        } else {
            this.update(workout);
        }
    }

    @Override
    public void update(Workout workout) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE CC_WORKOUT SET  W_NAME=?, W_CALORIES=?, W_DURATION=?, W_U_ID=? WHERE W_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, workout.getName());
            statement.setDouble(2, workout.getCalories());
            statement.setDouble(3, workout.getDuration());
            statement.setLong(4, workout.getUser().getId());
            statement.setLong(5, workout.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_WORKOUT failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Workout workout) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO CC_WORKOUT (W_NAME, W_CALORIES, W_DURATION, W_U_ID) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, workout.getName());
            statement.setDouble(2, workout.getCalories());
            statement.setDouble(3, workout.getDuration());

            if (workout.getUser() != null || workout.getUser().getId() != null) {
                statement.setLong(4, workout.getUser().getId());
            }

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert into CC_WORKOUT failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    workout.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into CC_WORKOUT failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CC_WORKOUT WHERE W_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Deletion of CC_WORKOUT failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Workout> findAll() {
        List<Workout> workouts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_WORKOUT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            UserRepository userRepository = new UserRepository();

            while(result.next()) {
                Workout workout = new Workout(
                        result.getString("W_NAME"),
                        result.getDouble("W_CALORIES"),
                        result.getDouble("W_DURATION"),
                        userRepository.findById(result.getLong("W_U_ID")));
                workouts.add(workout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workouts;
    }

    @Override
    public Workout findById(long id) {
        Workout workout = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_WORKOUT WHERE W_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            UserRepository userRepository = new UserRepository();

            if (result.next()) {
                workout = new Workout(
                        result.getString("W_NAME"),
                        result.getDouble("W_CALORIES"),
                        result.getDouble("W_DURATION"),
                        userRepository.findById(result.getLong("W_U_ID")));
                workout.setId(result.getLong("W_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workout;
    }
}
