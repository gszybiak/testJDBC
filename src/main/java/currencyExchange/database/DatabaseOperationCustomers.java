package currencyExchange.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DatabaseOperationCustomers {
    private String tableName = "Customers";
    public void addCustomer(String name, String surname, String email, String password, String address, int phoneNumber, Statement statement) {
        String passworBase64 = Base64.getEncoder().encodeToString(password.getBytes());
        try {
            String sqlQuery = "INSERT INTO " + tableName  + " (Name, Surname, Email, Password, Address, PhoneNumber) " +
                    "VALUES ('" + name + "', '" + surname + "', '" + email + "', '" + passworBase64 + "', '" +
                    address + "', " + phoneNumber + ")";
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania nowego klienta: " + e.getMessage());
        }
    }
    public Map<String, Object> getCustomerById(int customerId, Statement statement) {

        Map<String, Object> customerMap = new HashMap<>();
        try {
            String sqlQuery = "SELECT * FROM " + tableName + " where Id = " + customerId;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            statement.execute(sqlQuery);

            createCustomerMap(customerMap, resultSet);
            resultSet.close();
            return customerMap;

        } catch (SQLException e) {
            System.out.println("Błąd podczas pobierania danych klienta: " + e.getMessage());
            return null;
        }
    }
    public void createCustomerMap(Map<String, Object> customerMap, ResultSet resultSet) {
        try {
            customerMap.put("ID", resultSet.getInt("id"));
            customerMap.put("NAME", resultSet.getString("name"));
            customerMap.put("SURNAME", resultSet.getString("surname"));
            customerMap.put("EMAIL", resultSet.getString("email"));
            customerMap.put("PASSWORD", resultSet.getString("password"));
            customerMap.put("ADDRESS", resultSet.getString("address"));
            customerMap.put("PHONE_NUMBER", resultSet.getInt("phoneNumber"));
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania danych klienta do mapy: " + e.getMessage());
        }
    }
}
