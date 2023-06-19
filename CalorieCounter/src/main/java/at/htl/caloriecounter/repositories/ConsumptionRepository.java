package at.htl.caloriecounter.repositories;

import at.htl.caloriecounter.entity.Consumption;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionRepository implements Persistent<Consumption> {
    private static final DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Consumption consumption) {
        if (consumption.getId() == null) {
            this.insert(consumption);
        } else {
            this.update(consumption);
        }
    }

    @Override
    public void update(Consumption consumption) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE CC_CONSUMPTION SET C_U_ID=?, C_F_ID=?, C_AMOUNT=? WHERE C_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, consumption.getUser().getId());
            statement.setLong(2, consumption.getFood().getId());
            statement.setInt(3, consumption.getAmount());
            statement.setLong(4, consumption.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_CONSUMPTION failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Consumption consumption) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO CC_CONSUMPTION (C_U_ID, C_F_ID, C_AMOUNT) VALUES (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, consumption.getUser().getId());
            statement.setLong(2, consumption.getFood().getId());
            statement.setInt(3, consumption.getAmount());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_CONSUMPTION failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    consumption.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into CC_CONSUMPTION failed, no ID obtained");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CC_CONSUMPTION WHERE C_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Deletion of CC_CONSUMPTION failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consumption> findAll() {
        List<Consumption> consumptions = new ArrayList<>();
        UserRepository userRepository = new UserRepository();
        FoodRepository foodRepository = new FoodRepository();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_CONSUMPTION";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            addConsumptionsFromResultSet(result, consumptions);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consumptions;
    }

    public List<Consumption> findAll(Long userId) {
        List<Consumption> userConsumptions = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_CONSUMPTION JOIN CC_USER C on C.U_ID = CC_CONSUMPTION.C_U_ID WHERE C_U_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet result = preparedStatement.executeQuery();

            addConsumptionsFromResultSet(result, userConsumptions);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userConsumptions;
    }

    private void addConsumptionsFromResultSet(ResultSet result, List<Consumption> userConsumptions) throws SQLException {
        UserRepository userRepository = new UserRepository();
        FoodRepository foodRepository = new FoodRepository();

        while(result.next()) {
            Consumption consumption = new Consumption();
            consumption.setId(result.getLong("C_ID"));
            consumption.setAmount(result.getInt("C_AMOUNT"));

            consumption.setUser(userRepository.findById(result.getLong("C_U_ID")));
            consumption.setFood(foodRepository.findById(result.getLong("C_F_ID")));

            userConsumptions.add(consumption);
        }
    }

    @Override
    public Consumption findById(long id) {
        Consumption consumption = new Consumption();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_CONSUMPTION WHERE C_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                consumption.setId((long) result.getInt("C_ID"));
                consumption.setAmount(result.getInt("C_AMOUNT"));

                UserRepository userRepository = new UserRepository();
                FoodRepository foodRepository = new FoodRepository();

                consumption.setUser(userRepository.findById(result.getInt("C_U_ID")));
                consumption.setFood(foodRepository.findById(result.getInt("C_F_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consumption;
    }
}
