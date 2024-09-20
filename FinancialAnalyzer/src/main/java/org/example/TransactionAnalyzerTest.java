package org.example;

import org.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних: три транзакції з різними сумами (доходи і витрата)
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testFindTopExpenses() {
        Transaction transaction1 = new Transaction("2023-01-01", -100.0, "Expense1");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Expense2");
        Transaction transaction3 = new Transaction("2023-01-03", -150.0, "Expense3");
        Transaction transaction4 = new Transaction("2023-01-04", -200.0, "Expense4");
        Transaction transaction5 = new Transaction("2023-01-05", -75.0, "Expense5");
        Transaction transaction6 = new Transaction("2023-01-06", -20.0, "Expense6");
        Transaction transaction7 = new Transaction("2023-01-07", -10.0, "Expense7");
        Transaction transaction8 = new Transaction("2023-01-08", -300.0, "Expense8");
        Transaction transaction9 = new Transaction("2023-01-09", -250.0, "Expense9");
        Transaction transaction10 = new Transaction("2023-01-10", -60.0, "Expense10");
        Transaction transaction11 = new Transaction("2023-01-11", -90.0, "Expense11");

        List<Transaction> transactions = Arrays.asList(
                transaction1, transaction2, transaction3, transaction4,
                transaction5, transaction6, transaction7, transaction8,
                transaction9, transaction10, transaction11
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевірка, що знайдено рівно 10 транзакцій у списку топ-10 витрат
        Assertions.assertEquals(10, topExpenses.size(), "There should be 10 top expenses");
        // Перевірка, що найбільша витрата - це transaction8 (сума -300.0)
        Assertions.assertEquals(transaction8, topExpenses.get(0), "Top expense is incorrect");
        // Перевірка, що друга найбільша витрата - це transaction9 (сума -250.0)
        Assertions.assertEquals(transaction9, topExpenses.get(1), "Second top expense is incorrect");
    }
}
