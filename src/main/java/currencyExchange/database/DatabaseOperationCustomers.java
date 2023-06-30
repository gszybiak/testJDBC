package currencyExchange.database;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperationCustomers {
    private String tableName = "Customers";


    public void addCustomer(String name, String surname, String email, String password, String address, int phoneNumber, Statement statement) {
        try {
            String sqlQuery = "INSERT INTO " + tableName  + " (Name, Surname, Email, Password, Address, PhoneNumber) " +
                    "VALUES ('" + name + "', '" + surname + "', '" + email + "', '" + password + "', '" +
                    address + "', " + phoneNumber + ")";
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania nowego klienta: " + e.getMessage());
        }
    }
}
