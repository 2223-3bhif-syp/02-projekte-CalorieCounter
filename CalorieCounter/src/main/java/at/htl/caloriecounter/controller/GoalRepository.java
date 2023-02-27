package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Goal;
import at.htl.caloriecounter.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GoalRepository implements Persistent<Goal> {
    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Goal goal) {
        if (goal.getId() == null) {
            this.insert(goal);
        } else {
            this.update(goal);
        }
    }

    @Override
    public void update(Goal goal) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE CC_GOAL SET G_WEIGHT=?, G_DEADLINE=? WHERE G_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDouble(1, goal.getWeight());
            statement.setTimestamp(2, Timestamp.valueOf(goal.getDeadline()));
            statement.setLong(3, goal.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_GOAL failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Goal goal) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO CC_GOAL (G_WEIGHT, G_DEADLINE) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setDouble(1, goal.getWeight());
            statement.setTimestamp(2, Timestamp.valueOf(goal.getDeadline()));

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_GOAL failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    goal.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into CC_GOAL failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CC_GOAL WHERE G_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Deletion of CC_GOAL failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Goal> findAll() {
        List<Goal> goals = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_GOAL";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Goal goal = new Goal(result.getDouble("G_WEIGHT"), result.getTimestamp("G_DEADLINE").toLocalDateTime());
                goal.setId(result.getLong("G_ID"));
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goals;
    }

    @Override
    public Goal findById(long id) {
        Goal goal = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_GOAL WHERE G_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                goal = new Goal(result.getDouble("G_WEIGHT"), result.getTimestamp("G_DEADLINE").toLocalDateTime());
                goal.setId(result.getLong("G_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goal;
    }
}
