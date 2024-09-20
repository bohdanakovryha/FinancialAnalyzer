package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class TransactionReportGenerator {
    private static final double UNIT_AMOUNT = 1000.0;

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printCategoryExpensesReport(List<Transaction> transactions) {
        List<String> categories = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                int index = categories.indexOf(category);
                if (index == -1) {
                    categories.add(category);
                    amounts.add(transaction.getAmount());
                } else {
                    double currentAmount = amounts.get(index);
                    amounts.set(index, currentAmount + transaction.getAmount());
                }
            }
        }

        System.out.println("Сумарні витрати по категоріях:");
        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);
            double amount = amounts.get(i);
            System.out.printf("%s: %.2f %s\n", category, Math.abs(amount), "*".repeat((int) (Math.abs(amount) / UNIT_AMOUNT)));
        }
    }

    public static void printMonthlyExpensesReport(List<Transaction> transactions) {
        List<String> months = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String monthYear = transaction.getDate().substring(3);
                int index = months.indexOf(monthYear);
                if (index == -1) {
                    months.add(monthYear);
                    amounts.add(transaction.getAmount());
                } else {
                    double currentAmount = amounts.get(index);
                    amounts.set(index, currentAmount + transaction.getAmount());
                }
            }
        }

        System.out.println("Сумарні витрати по місяцях:");
        for (int i = 0; i < months.size(); i++) {
            String monthYear = months.get(i);
            double amount = amounts.get(i);
            System.out.printf("%s: %.2f %s\n", monthYear, Math.abs(amount), "*".repeat((int) (Math.abs(amount) / UNIT_AMOUNT)));
        }
    }

    public static void printExtremeExpensesReport(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Немає транзакцій для аналізу.");
            return;
        }
        Transaction minExpense = Collections.min(transactions, Comparator.comparingDouble(Transaction::getAmount));
        Transaction maxExpense = Collections.max(transactions, Comparator.comparingDouble(Transaction::getAmount));

        System.out.println("Найменша витрата: " + minExpense);
        System.out.println("Найбільша витрата: " + maxExpense);
    }
}
