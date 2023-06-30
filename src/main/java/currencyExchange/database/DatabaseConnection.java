package currencyExchange.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private String url = "jdbc:mariadb://127.0.0.1:3306/currency_exchange";
    private String username = "root";
    private String password = "mario";
    private Connection connection;
    private Statement statement;

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public DatabaseConnection() {
        try {
            connect();
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Błąd podczas tworzenia obiektu Statement dla klienta: " + e.getMessage());
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Błąd podczas łączenia z bazą danych: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Błąd podczas zamykania połączenia: " + e.getMessage());
            }
        }
    }
}
