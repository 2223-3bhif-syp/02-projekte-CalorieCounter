package at.htl.caloriecounter.repositories;

import at.htl.caloriecounter.entity.Food;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements Persistent<Food> {
    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Food food) {
        if (food.getId() == null) {
            this.insert(food);
        } else {
            this.update(food);
        }
    }

    @Override
    public void update(Food food) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE CC_FOOD SET F_NAME=?, F_CALORIES=? WHERE F_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, food.getName());
            statement.setDouble(2, food.getCalories());
            statement.setLong(3, food.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_FOOD failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Food food) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO CC_FOOD (F_NAME, F_CALORIES) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, food.getName());
            statement.setDouble(2, food.getCalories());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_FOOD failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    food.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into CC_FOOD failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CC_FOOD WHERE F_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Deletion of CC_FOOD failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> findAll() {
        List<Food> foodList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_FOOD";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Food food = new Food(result.getString("F_NAME"), result.getDouble("F_CALORIES"));
                food.setId(result.getLong("F_ID"));

                foodList.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodList;
    }

    @Override
    public Food findById(long id) {
        Food food = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_FOOD WHERE F_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                food = new Food(result.getString("F_NAME"), result.getDouble("F_CALORIES"));;
                food.setId(result.getLong("F_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return food;
    }
}
