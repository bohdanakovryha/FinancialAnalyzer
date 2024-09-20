package org.example;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    public static List<Transaction> readTransactions(String path) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader br;
            if (path.startsWith("http://") || path.startsWith("https://")) {
                br = new BufferedReader(new InputStreamReader(new URL(path).openStream()));
            } else {
                br = new BufferedReader(new FileReader(new File(path)));
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
                transactions.add(transaction);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
