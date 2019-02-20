package com.example.bankapplication;

public class Client {
    String name;
    double balance;
    Transaction[] history;
    int not;
    final int MAX_NUM_TRANSACTION = 10;

    Client(String name, double balance){
        this.name = name;
        this.balance = balance;
        history = new Transaction[MAX_NUM_TRANSACTION];
        not = 0;
    }

    void addTransaction(Transaction tr){
        this.history[not] = tr;
        not++;
    }

    String getOutput(){
        String s = "";
        s += "Statement of client " + this.name + " (current balance $" + this.balance + "):\n";
        for(int i = 0; i < not; i++){
            s += "Transaction " + history[i].type + ": $" + history[i].amount + "\n";
        }
        return s;
    }
}
