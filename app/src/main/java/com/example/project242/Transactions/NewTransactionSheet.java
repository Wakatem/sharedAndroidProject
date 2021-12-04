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

import com.example.project242.general.DataContainer;
import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextView dateError;
    private EditText paymentMethod;
    private Spinner PaymentMethod;
    private RadioGroup transactionType;
    private RadioButton Income;
    private RadioButton expense;
    private TextView expenseTypeQ;
    private Spinner expenseType;
    private Button save;
    private Button cancel;

    public NewTransactionSheet(Context ActivityContext, int sheetlayout) {
        newList = DataContainer.transactionsHandler;
        newSheetDialog = new BottomSheetDialog(ActivityContext);
        context = ActivityContext;
        newSheetDialog.setContentView(sheetlayout);
    }

    public void initializeTransactionView() {
        transactionID = newSheetDialog.findViewById(R.id.transactionIDA);
        senderID = newSheetDialog.findViewById(R.id.senderIDA);
        senderName = newSheetDialog.findViewById(R.id.senderNameA);
        recipientID = newSheetDialog.findViewById(R.id.recipientIDA);
        recipientName = newSheetDialog.findViewById(R.id.recipientNameA);
        amount = newSheetDialog.findViewById(R.id.amountA);
        date = newSheetDialog.findViewById(R.id.dateA);
        // paymentMethod = newSheetDialog.findViewById(R.id.paymentMethodA);
        PaymentMethod = newSheetDialog.findViewById(R.id.paymentMethodA);
        transactionType = newSheetDialog.findViewById(R.id.typeA);
        Income = newSheetDialog.findViewById(R.id.income);
        expense = newSheetDialog.findViewById(R.id.expense);
        expenseTypeQ = newSheetDialog.findViewById(R.id.expensetypeQ);
        expenseType = newSheetDialog.findViewById(R.id.expenseTypeA);
        save = newSheetDialog.findViewById(R.id.saveTransaction);
        cancel = newSheetDialog.findViewById(R.id.cancelTransaction);
        dateError = newSheetDialog.findViewById(R.id.dateError);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.expenseType, android.R.layout.simple_spinner_dropdown_item);
        expenseType.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterz = ArrayAdapter.createFromResource(context, R.array.paymentType, android.R.layout.simple_spinner_dropdown_item);
        PaymentMethod.setAdapter(adapterz);

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


    public void addTransaction(EditText transactionID, EditText sender_AccountNumber, EditText sender_AccountName, EditText recipient_AccountNumber, EditText recipient_AccountName, EditText amount, EditText date, Spinner expenseType, Spinner PaymentMethod) {
        long transID = Long.parseLong(transactionID.getText().toString());
        int sendAcc = Integer.valueOf(sender_AccountNumber.getText().toString());
        String sendName = sender_AccountName.getText().toString();
        int receiptAcc = Integer.valueOf(recipient_AccountNumber.getText().toString());
        String receiptName = recipient_AccountName.getText().toString();
        int amountSent = Integer.valueOf(amount.getText().toString());

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date newdate = null;
        try {
            newdate = format.parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ExpenseTypes expensetype;
        if (expenseType.getSelectedItem().toString() == "Salary") {
            expensetype = ExpenseTypes.SALARY;
        } else if (expenseType.getSelectedItem().toString() == "Utility") {
            expensetype = ExpenseTypes.UTILITY;
        } else if (expenseType.getSelectedItem().toString() == "Rent") {
            expensetype = ExpenseTypes.RENT;
        } else {
            expensetype = ExpenseTypes.OTHER;
        }

        PaymentMethods payment;
        if (PaymentMethod.getSelectedItem().toString() == "Online Payment") {
            payment = PaymentMethods.ONLINE_PAYMENT;
        } else if (PaymentMethod.getSelectedItem().toString() == "Cheque") {
            payment = PaymentMethods.CHEQUE;
        } else {
            payment = PaymentMethods.BANK_TRANSFER;
        }
        Transaction newtransaction = new Transaction(transID, sendAcc, sendName, receiptAcc, receiptName, amountSent, newdate, expensetype, payment);
        newList.add(newtransaction);

    }

    public void addTransactionWOExpense(EditText transactionID, EditText sender_AccountNumber, EditText sender_AccountName, EditText recipient_AccountNumber, EditText recipient_AccountName, EditText amount, EditText date, RadioGroup transaction_Type, Spinner PaymentMethod) {
        long transID = Long.parseLong(transactionID.getText().toString());
        int sendAcc = Integer.valueOf(sender_AccountNumber.getText().toString());
        String sendName = sender_AccountName.getText().toString();
        int receiptAcc = Integer.valueOf(recipient_AccountNumber.getText().toString());
        String receiptName = recipient_AccountName.getText().toString();
        int amountSent = Integer.valueOf(amount.getText().toString());

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date newdate = null;
        try {
            newdate = format.parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TransactionTypes TType = TransactionTypes.INCOME;

        PaymentMethods payment;
        if (PaymentMethod.getSelectedItem().toString() == "Online Payment") {
            payment = PaymentMethods.ONLINE_PAYMENT;
        } else if (PaymentMethod.getSelectedItem().toString() == "Cheque") {
            payment = PaymentMethods.CHEQUE;
        } else {
            payment = PaymentMethods.BANK_TRANSFER;
        }

        Transaction newtransaction = new Transaction(transID, sendAcc, sendName, receiptAcc, receiptName, amountSent, newdate, TType, payment);
        newList.add(newtransaction);

    }

    public void setTransactionListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expenseType.getVisibility() == View.VISIBLE) {
                    if (transactionID.length() != 0 && senderID.length() != 0 && senderName.length() != 0 && recipientID.length() != 0 && recipientName.length() != 0 && amount.length() != 0 && date.length() != 0) {
                        if (isValidDate(date.getText().toString()) == true) {
                            addTransaction(transactionID, senderID, senderName, recipientID, recipientName, amount, date, expenseType, PaymentMethod);
                            Toast.makeText(context, "new expense  added", Toast.LENGTH_SHORT).show();
                            dateError.setText("");
                            newSheetDialog.dismiss();
                        } else {
                            dateError.setText("Invalid date format");
                        }
                    } else {
                        Toast.makeText(context, "Please fill all mandatory columns", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (transactionID.length() != 0 && senderID.length() != 0 && senderName.length() != 0 && recipientID.length() != 0 && recipientName.length() != 0 && amount.length() != 0 && date.length() != 0) {
                        if (isValidDate(date.getText().toString()) == true) {
                            addTransactionWOExpense(transactionID, senderID, senderName, recipientID, recipientName, amount, date, transactionType, PaymentMethod);
                            Toast.makeText(context, "new income  added", Toast.LENGTH_SHORT).show();
                            dateError.setText("");
                            newSheetDialog.dismiss();
                        }
                        else {
                            dateError.setText("Invalid date format");
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newSheetDialog.cancel();
            }
        });
    }


    public void show() {

        newSheetDialog.show();
    }

    public boolean isValidDate(String date) {
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();

    }
}
