package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        return (int) transactions.stream()
                .filter(transaction -> {
                    LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
                    return date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear);
                })
                .count();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static void printMinMaxExpenses(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Список транзакцій порожній.");
            return;
        }

        Transaction maxExpense = transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);

        Transaction minExpense = transactions.stream()
                .filter(transaction -> transaction.getAmount() < 0)
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);

        if (maxExpense != null) {
            System.out.println("Найменша витрата: " + maxExpense);
        } else {
            System.out.println("Немає витрат");
        }

        if (minExpense != null) {
            System.out.println("Найбільша витрата: " + minExpense);
        } else {
            System.out.println("Немає витрат");
        }
    }
}
