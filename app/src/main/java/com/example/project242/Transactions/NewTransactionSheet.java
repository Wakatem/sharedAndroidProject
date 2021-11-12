package com.example.project242.Transactions;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.Home.Home;
import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTransactionSheet {
    private BottomSheetDialog newSheetDialog;
    private Context context;
    private Transaction newTransaction;


    private TransactionsHandler newList;
    private EditText transactionID;
    private EditText senderID;
    private EditText senderName;
    private EditText recipientID;
    private EditText recipientName;
    private EditText amount;
    private EditText date;
    private EditText paymentMethod;
    private RadioGroup transactionType;
    private RadioButton Income;
    private RadioButton expense;
    private TextView expenseTypeQ;
    private Spinner expenseType;
    private Button save;
    private Button cancel;

    public NewTransactionSheet(Context ActivityContext, int sheetlayout){
        newList = Home.transactionsHandler;
        newSheetDialog = new BottomSheetDialog(ActivityContext);
        context=ActivityContext;
        newSheetDialog.setContentView(sheetlayout);
    }

    public void initializeTransactionView(){
        transactionID = newSheetDialog.findViewById(R.id.transactionIDA);
        senderID = newSheetDialog.findViewById(R.id.senderIDA);
        senderName = newSheetDialog.findViewById(R.id.senderNameA);
        recipientID = newSheetDialog.findViewById(R.id.recipientIDA);
        recipientName = newSheetDialog.findViewById(R.id.recipientNameA);
        amount = newSheetDialog.findViewById(R.id.amountA);
        date = newSheetDialog.findViewById(R.id.dateA);
        paymentMethod = newSheetDialog.findViewById(R.id.paymentMethodA);
        transactionType = newSheetDialog.findViewById(R.id.typeA);
        Income = newSheetDialog.findViewById(R.id.income);
        expense = newSheetDialog.findViewById(R.id.expense);
        expenseTypeQ = newSheetDialog.findViewById(R.id.expensetypeQ);
        expenseType = newSheetDialog.findViewById(R.id.expenseTypeA);
        save = newSheetDialog.findViewById(R.id.saveTransaction);
        cancel = newSheetDialog.findViewById(R.id.cancelTransaction);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.expenseType, android.R.layout.simple_spinner_dropdown_item);
        expenseType.setAdapter(adapter);

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseTypeQ.setVisibility(View.VISIBLE);
                expenseType.setVisibility(View.VISIBLE);
            }
        });
        Income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseType.setVisibility(View.INVISIBLE);
                expenseTypeQ.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void addTransaction(EditText transactionID, EditText sender_AccountNumber, EditText sender_AccountName, EditText recipient_AccountNumber, EditText recipient_AccountName, EditText amount, EditText date, Spinner expenseType, EditText paymentMethod){
        long transID = Long.parseLong(transactionID.getText().toString());
        int sendAcc = Integer.valueOf(sender_AccountNumber.getText().toString());
        String sendName = sender_AccountName.getText().toString();
        int receiptAcc = Integer.valueOf(recipient_AccountNumber.getText().toString());
        String receiptName = recipient_AccountName.getText().toString();
        int amountSent = Integer.valueOf(amount.getText().toString());

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date newdate=null;
        try {
            newdate = format.parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ExpenseTypes expensetype;
        if(expenseType.getSelectedItem().toString() == "Salary"){
            expensetype= ExpenseTypes.SALARY;
        }
        else if (expenseType.getSelectedItem().toString()=="Utility"){
            expensetype=ExpenseTypes.UTILITY;
        }
        else if (expenseType.getSelectedItem().toString()=="Rent"){
            expensetype=ExpenseTypes.RENT;
        }
        else {
            expensetype=ExpenseTypes.OTHER;
        }

        PaymentMethods payment;
        if(paymentMethod.getText().toString() == "Cash"){
            payment = PaymentMethods.CASH;
        }
        else if(paymentMethod.getText().toString()=="Cheque"){
            payment=PaymentMethods.CHEQUE;
        }
        else {
            payment=PaymentMethods.BANK_TRANSFER;
        }

        Transaction newtransaction = new Transaction(transID,sendAcc,sendName,receiptAcc, receiptName,amountSent,newdate,expensetype, payment);
        newList.add(newtransaction);

    }
    public void addTransactionWOExpense(EditText transactionID, EditText sender_AccountNumber, EditText sender_AccountName, EditText recipient_AccountNumber, EditText recipient_AccountName, EditText amount, EditText date,RadioGroup transaction_Type, EditText paymentMethod){
        long transID = Long.parseLong(transactionID.getText().toString());
        int sendAcc = Integer.valueOf(sender_AccountNumber.getText().toString());
        String sendName = sender_AccountName.getText().toString();
        int receiptAcc = Integer.valueOf(recipient_AccountNumber.getText().toString());
        String receiptName = recipient_AccountName.getText().toString();
        int amountSent = Integer.valueOf(amount.getText().toString());

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date newdate=null;
        try {
            newdate = format.parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TransactionTypes TType = TransactionTypes.INCOME;

        PaymentMethods payment;
        if(paymentMethod.getText().toString() == "Cash"){
            payment = PaymentMethods.CASH;
        }
        else if(paymentMethod.getText().toString()=="Cheque"){
            payment=PaymentMethods.CHEQUE;
        }
        else {
            payment=PaymentMethods.BANK_TRANSFER;
        }

        Transaction newtransaction = new Transaction(transID,sendAcc,sendName,receiptAcc, receiptName,amountSent,newdate,TType, payment);
        newList.add(newtransaction);
    }

    public void setTransactionListener(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(expenseType.getVisibility()==View.VISIBLE) {
                    addTransaction(transactionID, senderID, senderName, recipientID, recipientName, amount, date, expenseType, paymentMethod);
                    Toast.makeText(context, "new expense  added", Toast.LENGTH_SHORT).show();
                }
                else {
                    addTransactionWOExpense(transactionID, senderID, senderName, recipientID, recipientName, amount, date, transactionType, paymentMethod);
                    Toast.makeText(context, "new income  added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void show() {

        newSheetDialog.show();
    }
}
