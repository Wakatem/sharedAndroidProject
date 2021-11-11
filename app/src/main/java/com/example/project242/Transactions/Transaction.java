package com.example.project242.Transactions;

import java.util.Date;

public class Transaction {

    private final int transactionID;
    private int sender_AccountNumber;
    private String sender_AccountName;
    private int recipient_AccountNumber;
    private String recipient_AccountName;
    private int amount;
    private Date date;
    private TransactionTypes transactionType;
    private ExpenseTypes expenseType;
    private PaymentMethods paymentMethod;


    //without expense type specification
    public Transaction(int transactionID, int sender_AccountNumber, String sender_AccountName, int recipient_AccountNumber, String recipient_AccountName, int amount, Date date, TransactionTypes transactionType, PaymentMethods paymentMethod){
        this.transactionID = transactionID;
        this.sender_AccountNumber = sender_AccountNumber;
        this.sender_AccountName = sender_AccountName;
        this.recipient_AccountNumber = recipient_AccountNumber;
        this.recipient_AccountName = recipient_AccountName;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
        this.paymentMethod = paymentMethod;
    }

    //with expense type specification
    public Transaction(int transactionID, int sender_AccountNumber, String sender_AccountName, int recipient_AccountNumber, String recipient_AccountName, int amount, Date date, ExpenseTypes expenseType, PaymentMethods paymentMethod){
        this.transactionID = transactionID;
        this.sender_AccountNumber = sender_AccountNumber;
        this.sender_AccountName = sender_AccountName;
        this.recipient_AccountNumber = recipient_AccountNumber;
        this.recipient_AccountName = recipient_AccountName;
        this.amount = amount;
        this.date = date;
        this.transactionType = TransactionTypes.EXPENSE; //automatically assigned as EXPENSE if this constructor called
        this.expenseType = expenseType;
        this.paymentMethod = paymentMethod;
    }



    public int getTransactionID() {
        return transactionID;
    }

    public int getSender_AccountNumber() {
        return sender_AccountNumber;
    }

    public void setSender_AccountNumber(int sender_AccountNumber) {
        this.sender_AccountNumber = sender_AccountNumber;
    }

    public String getSender_AccountName() {
        return sender_AccountName;
    }

    public void setSender_AccountName(String sender_AccountName) {
        this.sender_AccountName = sender_AccountName;
    }

    public int getRecipient_AccountNumber() {
        return recipient_AccountNumber;
    }

    public void setRecipient_AccountNumber(int recipient_AccountNumber) {
        this.recipient_AccountNumber = recipient_AccountNumber;
    }

    public String getRecipient_AccountName() {
        return recipient_AccountName;
    }

    public void setRecipient_AccountName(String recipient_AccountName) {
        this.recipient_AccountName = recipient_AccountName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionTypes getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypes transactionType) {
        this.transactionType = transactionType;
    }

    public ExpenseTypes getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseTypes expenseType) {
        this.expenseType = expenseType;
    }

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}//class
