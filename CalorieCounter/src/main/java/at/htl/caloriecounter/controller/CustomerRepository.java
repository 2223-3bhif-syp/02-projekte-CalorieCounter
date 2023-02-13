package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Customer;

import javax.sql.DataSource;
import java.sql.*;


public class CustomerRepository {
    private DataSource dataSource = Database.getDataSource();

    public void create(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO CUSTOMER  (EMAIL, USERNAME, PASSWORD) VALUES (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getPassword());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of ANSWER failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    customer.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into ANSWER failed, no ID obtained");
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
