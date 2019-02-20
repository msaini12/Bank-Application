package com.example.bankapplication;

public class BankTester {
    public static void main(String[] args){
        Transaction tr1 = new Transaction("withdraw", 500);
        Transaction tr2 = new Transaction("deposit", 200);
        Transaction tr3 = new Transaction("withdraw", 300);

        Client client1 = new Client("Tim", 5000);
        Client client2 = new Client("Bob", 4000);

        client1.addTransaction(tr1);
        client1.addTransaction(tr2);
        client2.addTransaction(tr3);

        Bank bank = new Bank();

        bank.addClient(client1);
        bank.addClient(client2);

        System.out.println(tr1.toString());
        System.out.println(tr2.toString());
        System.out.println(tr3.toString() + "\n");

        System.out.println(client1.toString());
        System.out.println(client2.toString() + "\n");

        System.out.println(bank.getOutput());

        bank.setTransaction("deposit", "", "Tim", 500);
        System.out.println("Deposit $500 into Tim's Account");
        System.out.println(bank.getTransaction());

        bank.setTransaction("withdraw", "Bob", "", 60);
        System.out.println("Withdraw $60 from Bob's Account");
        System.out.println(bank.getTransaction());

        bank.setTransaction("transfer", "Bob", "Tim", 100);
        System.out.println("Transfer $100 from Bob's Account to Tim's Account");
        System.out.println(bank.getTransaction());

        System.out.println(client1.toString());
        System.out.println(client2.toString());

        System.out.println("Client 1's Transaction History");
        System.out.println(client1.getOutput());

        System.out.println("Client 2's Transaction History");
        System.out.println(client2.getOutput());

        bank.setOutputStatement("Tim");
        System.out.println(bank.getOutputStatement());
        bank.setOutputStatement("Bob");
        System.out.println(bank.getOutputStatement());

        bank.getTransaction();
    }
}
