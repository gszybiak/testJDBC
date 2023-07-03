package currencyExchange;

import currencyExchange.database.DatabaseConnection;
import currencyExchange.database.DatabaseOperationCustomers;
import currencyExchange.database.DatabaseOperationTransactions;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        DatabaseOperationCustomers databaseOperationCustomers = new DatabaseOperationCustomers();
        DatabaseOperationTransactions databaseOperationTransactions = new DatabaseOperationTransactions();

        databaseOperationCustomers.addCustomer("Gabriel", "Szybiak", "g_szybiak@wp.pl",
                "mario", "Ostrów 404", 733939777 , databaseConnection.getStatement());

        databaseOperationTransactions.addTransaction(1, Date.valueOf(LocalDate.now()), 120.0,
                "USD", "sell", 4.09, databaseConnection.getStatement());

        Customer customer = databaseOperationCustomers.getCustomerById(1, databaseConnection.getStatement());
        Transaction transaction = databaseOperationTransactions.getTransactionById(1, databaseConnection.getStatement());

        databaseConnection.disconnect();
    }
}