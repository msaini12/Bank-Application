package com.example.bankapplication;

public class TransactionTester {
    public static void main (String[] args){
        Transaction tr1 = new Transaction("deposit");

        System.out.println(tr1.toString());

        tr1.setAmount(200);

        System.out.println(tr1.toString());

        Transaction tr2 = new Transaction("withdraw", 500);

        System.out.println(tr2.toString());

    }
}
