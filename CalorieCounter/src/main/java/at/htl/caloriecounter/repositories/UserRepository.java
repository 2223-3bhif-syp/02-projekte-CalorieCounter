package at.htl.caloriecounter.repositories;

import at.htl.caloriecounter.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Persistent<User> {
    private static DataSource dataSource = Database.getDataSource();

   @Override
   public void save(User user) {
       if (user.getId() == null) {
           this.insert(user);
       } else {
           this.update(user);
       }
   }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE CC_USER SET U_EMAIL=?, U_USERNAME=?, U_PASSWORD=?, U_HEIGHT=?, U_WEIGHT=? WHERE U_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setDouble(4, user.getHeight());
            statement.setDouble(5, user.getWeight());
            statement.setLong(6, user.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_USER failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO CC_USER (U_EMAIL, U_USERNAME, U_PASSWORD, U_HEIGHT, U_WEIGHT) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setDouble(4, user.getHeight());
            statement.setDouble(5, user.getWeight());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of CC_USER failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into CC_USER failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CC_USER WHERE U_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Deletion of CC_USER failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_USER";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                User user = new User(result.getString("U_EMAIL"),
                        result.getString("U_USERNAME"),
                        result.getString("U_PASSWORD"),
                        result.getDouble("U_WEIGHT"),
                        result.getDouble("U_HEIGHT"));

                user.setId(result.getLong("U_ID"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(long id) {
        User user = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CC_USER WHERE U_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getString("U_EMAIL"),
                        result.getString("U_USERNAME"),
                        result.getString("U_PASSWORD"),
                        result.getDouble("U_WEIGHT"),
                        result.getDouble("U_HEIGHT"));
                user.setId(result.getLong("U_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static boolean isValidUser(String username, String password) {
       try (Connection connection = dataSource.getConnection()) {
           String sql = "SELECT COUNT(*) as \"cnt\" FROM CC_USER WHERE UPPER(U_USERNAME) = ? AND U_PASSWORD = ?";

           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, username.toUpperCase());
           statement.setString(2, password);

           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next() && resultSet.getInt("cnt") == 0) {
               return false;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return true;
    }
}
