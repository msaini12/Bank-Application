package com.example.bankapplication;

public class Bank {
    Client[] clients;
    int noc;
    final int MAX_NUM_CLIENTS = 6;
    String service, from, to, outputName, errorMsg;
    double amount;
    boolean error;

    Bank(){
        clients = new Client[MAX_NUM_CLIENTS];
        noc = 0;
        error = false;
        errorMsg = "";
    }
    void setError(String errorMsg){
        this.error = true;
        this.errorMsg = errorMsg;
    }

    void resetError(){
        this.error = false;
        this.errorMsg = "";
    }

    void addClient(Client c){
        clients[noc] = c;
        noc++;
    }

    String getOutput(){
        String s = "";
        if(error){
            s = errorMsg;
        }
        else{
            s += "Updated Balances of Clients:\n";
            String round;
            for(int i = 0; i < noc; i++){
                round = String.format("%.2f", this.clients[i].balance);
                s += (this.clients[i].name + ": $" + round + "\n");
            }
        }
        return s;
    }

    boolean getEqualName(String name){
        boolean flag = false;
        for(int i = 0; i < noc; i++){
            if(clients[i].name.equals(name)){
                flag = true;
            }
        }
        return flag;
    }

    void addClient(String name, double balance){
        boolean flag = getEqualName(name);
        if(noc == MAX_NUM_CLIENTS){
            setError("Error: Maximum Number of Clients Reached");
        }
        else if(flag){
            setError("Error: Client " + name + " already exists.");
        }
        else if(balance <= 0){
            setError("Error: Non-Positive Initial Balance");
        }
        else{
            Client c = new Client(name, balance);
            this.addClient(c);
            resetError();
        }
    }

    int indexofPerson(String name){
        int index = -1;
        for(int i = 0; i < noc; i++){
            if(clients[i].name.equals(name)){
                index = i;
            }
        }
        return index;
    }

    void addTransaction(String name, Transaction tr){
        int index = indexofPerson(name);
        if(index >= 0){
            this.clients[index].addTransaction(tr);
        }
    }

    void setTransaction(String service, String from, String to, double amount){
        this.service = service;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    String getTransaction(){
        int fromPerson = indexofPerson(from);
        int toPerson = indexofPerson(to);

        Transaction tr = new Transaction(amount);
        Transaction tr2 = new Transaction(amount);

        if(service.equals("DEPOSIT")){
            if(toPerson != -1){
                if(amount > 0){
                    clients[toPerson].balance += amount;
                    this.addTransaction(clients[toPerson].name, tr);
                    tr.setType("DEPOSIT");
                    resetError();
                }
                else{
                    setError("Error: Non-Positive Amount");
                }
            }
            else{
                setError("Error: To-Client " + this.to + " does not exist.");
            }
        }
        else if(service.equals("WITHDRAW")){
            if(fromPerson != -1){
                if(amount > 0){
                    if(amount < clients[fromPerson].balance){
                        clients[fromPerson].balance -= amount;
                        this.addTransaction(clients[fromPerson].name, tr2);
                        tr2.setType("WITHDRAW");
                        resetError();
                    }
                    else{
                        setError("Error: Amount to large to withdraw.");
                    }
                }
                else{
                    setError("Error: Non-Positive Amount");
                }
            }
            else{
                setError("Error: From-Client " + this.from + " does not exist.");
            }
        }
        else if(service.equals("TRANSFER")){
            if(toPerson != -1 && fromPerson != -1){
                if(amount > 0){
                    if(amount < clients[fromPerson].balance){
                        clients[fromPerson].balance -= amount;
                        clients[toPerson].balance += amount;
                        this.addTransaction(clients[toPerson].name, tr);
                        this.addTransaction(clients[fromPerson].name, tr2);
                        tr.setType("DEPOSIT");
                        tr2.setType("WITHDRAW");
                        resetError();
                    }
                    else{
                        setError("Error: Amount too large to transfer.");
                    }
                }
                else{
                    setError("Error: Non-Positive Amount");
                }
            }
            else if(fromPerson == -1){
                setError("Error: From-Client " + this.from + " does not exist.");
            }
            else if(toPerson == -1){
                setError("Error: To-Client " + this.to + " does not exist.");
            }
        }
        if(error){
            return errorMsg;
        }
        else{
            return getOutput();
        }
    }

    void setOutputStatement(String outputName){
        this.outputName = outputName;
    }

    String getOutputStatement(){
        String s = "";
        boolean flag = getEqualName(outputName);
        int index = indexofPerson(outputName);
        if(!flag){
            setError("Error: Client " + outputName + " does not exist.");
        }
        else{
            String round = String.format("%.2f", this.clients[index].balance);
            s += "Statement of client " + this.clients[index].name + " (current balance $" + round + "):\n";
            for(int i = 0; i < clients[index].not; i++){
                String roundAmount = String.format("%.2f", clients[index].history[i].amount);
                s += "Transaction " + clients[index].history[i].type + ": $" + roundAmount + "\n";
            }
            resetError();
        }
        if(error){
            s = errorMsg;
        }
        return s;
    }
}
