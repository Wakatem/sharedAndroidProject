package com.example.project242;

import java.util.LinkedHashMap;

public class TransactionsHandler {

    private LinkedHashMap<Integer, Transaction> transactionsList;

    public TransactionsHandler(){
    }

    ////////////////////////////////////////////////////////////////////////

    public TransactionsHandler getTransactionsHandler(){
        return this;
    }

    public void createList(){
        transactionsList = new LinkedHashMap<>();
    }

    public LinkedHashMap<Integer, Transaction> getList(){
        return transactionsList;
    }

    public void clearList(){
        transactionsList.clear();
    }



    ////////////////////////////////////////////////////////////////////////


    public void addTransaction(int transactionID, Transaction transaction){
        transactionsList.put(transactionID, transaction);
    }

    public Transaction getTransaction(int transactionID){
        return transactionsList.get(transactionID);
    }

    public void removeTransaction(int transactionID){
        transactionsList.remove(transactionID);
    }



    ////////////////////////////////////////////////////////////////////////

    //TODO: here you create the functions you want (all will be merged after finishing)



}//class
