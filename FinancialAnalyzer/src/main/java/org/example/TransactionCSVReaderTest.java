package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() throws IOException {

        // Створення тестового CSV-файлу
        File tempFile = File.createTempFile("pr2", ".csv");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("2023-01-01,100.0,Income\n");
            writer.write("2023-01-02,-50.0,Expense\n");
            writer.write("2023-01-03,150.0,Income\n");
        }

        // Виклик статичного методу для читання транзакцій
        List<Transaction> transactions = TransactionCSVReader.readTransactions(tempFile.getAbsolutePath());

        Assertions.assertNotNull(transactions, "Transactions list should not be null");
        Assertions.assertEquals(3, transactions.size(), "There should be 3 transactions");

        Assertions.assertEquals("2023-01-01", transactions.get(0).getDate(), "First transaction date is incorrect");
        Assertions.assertEquals(100.0, transactions.get(0).getAmount(), "First transaction amount is incorrect");
        Assertions.assertEquals("Income", transactions.get(0).getDescription(), "First transaction description is incorrect");
    }
}
