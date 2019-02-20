package com.example.bankapplication;

public class ClientTester {
    public static void main(String[] args){
        Client c1 = new Client("Tim", 5000);

        Transaction tr1 = new Transaction("deposit", 500);
        Transaction tr2 = new Transaction("withdraw", 400);

        c1.addTransaction(tr1);
        c1.addTransaction(tr2);

        System.out.println(c1.toString());

    }
}
