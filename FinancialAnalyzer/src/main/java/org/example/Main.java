package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // Визначення найменших і найбільших витрат за певний період
        TransactionAnalyzer.printMinMaxExpenses(transactions);

        // Створення звіту по категоріях
        TransactionReportGenerator.printCategoryExpensesReport(transactions);

        // Створення звіту по місяцях
        TransactionReportGenerator.printMonthlyExpensesReport(transactions);
    }
}
