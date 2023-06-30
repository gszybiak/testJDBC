package currencyExchange.database;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperationTransactions {
    private String tableName = "Transactions";

    public void addTransaction(int userId, Date transactionDate, double amount, String currency, String transactionType, double exchangeRate, Statement statement) {
        try {
            String sqlQuery = "INSERT INTO " + tableName  + " (UserID, TransactionDate, Amount, Currency, TransactionType, ExchangeRate) VALUES " +
                    "(" + userId + ", '" + transactionDate + "', " + amount + ", '" + currency + "', '" +
                    transactionType + "', " + exchangeRate + ")";
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania nowej transakcji: " + e.getMessage());
        }
    }
}
