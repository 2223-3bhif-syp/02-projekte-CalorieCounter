package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Customer;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

class CustomerRepositoryTest {
    CustomerRepository customerRepository = new CustomerRepository();

    @Test
    void insertCustomer_ok() {
        // arrange
        DataSource ds = Database.getDataSource();
        Customer customer = new Customer("abc", "test", "test", 10.0, 20.0);
        Table table = new Table(ds, "CUSTOMER");
        // act
        customerRepository.create(customer);

        // assert
        output(table).toConsole();
        assertThat(table).exists()
                .row(3)
                //.column("CUSTOMER_ID").value().isEqualTo(4)
                .column("USERNAME").value().isEqualTo("test");


    }
}