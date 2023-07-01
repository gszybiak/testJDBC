package currencyExchange.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> getTransactionById(int transactionId, Statement statement) {

        Map<String, Object> transactionMap = new HashMap<>();
        try {
            String sqlQuery = "SELECT * FROM " + tableName + " where Id = " + transactionId;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            statement.execute(sqlQuery);

            createTransactionMap(transactionMap, resultSet);
            resultSet.close();
            return transactionMap;

        } catch (SQLException e) {
            System.out.println("Błąd podczas pobierania danych transakcji: " + e.getMessage());
            return null;
        }
    }
    public void createTransactionMap(Map<String, Object> transactionMap, ResultSet resultSet) {
        try {
            transactionMap.put("ID", resultSet.getInt("id"));
            transactionMap.put("USER_ID", resultSet.getInt("userId"));
            transactionMap.put("TRANSACTION_DATE", resultSet.getDate("transactionDate"));
            transactionMap.put("AMOUNT", resultSet.getDouble("amount"));
            transactionMap.put("CURRENCY", resultSet.getString("currency"));
            transactionMap.put("TRANSACTION_TYPE", resultSet.getString("transactionType"));
            transactionMap.put("EXCHANGE_RATE", resultSet.getInt("exchangeRate"));
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania danych transakcji do mapy: " + e.getMessage());
        }
    }
}
