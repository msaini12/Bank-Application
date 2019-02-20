package com.example.bankapplication;

public class Transaction {
    String type;
    double amount;

    Transaction(double amount){
        this.amount = amount;
    }
    Transaction(String type){
        this.type = type;
    }
    Transaction(String type, double amount){
        this.type = type;
        this.amount = amount;
    }

    void setAmount(double amount){
        this.amount = amount;
    }

    void setType(String type){
        this.type = type;
    }
}
