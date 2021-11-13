package com.example.project242.Transactions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;

import java.util.Calendar;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private final TransactionsHandler handler;
    private final Context context;
    private Calendar calendar;

    public TransactionAdapter(Context context, TransactionsHandler handler) {
        super(context, 0, handler);
        this.context = context;
        this.handler = handler;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null)
            item = LayoutInflater.from(context).inflate(R.layout.transaction_listview_item, parent, false);

        Transaction transaction = handler.get(position);


            //link views
            ImageView image = item.findViewById(R.id.transactionType);
            TextView idTV = item.findViewById(R.id.transactionID_TV);
            TextView amountTV = item.findViewById(R.id.transactionAmount_TV);
            TextView accHolderTV = item.findViewById(R.id.transactionAccHolder_TV);
            TextView dateTV = item.findViewById(R.id.transactionDate_TV);

            //retrieve values
            String accHolder = "";
            if (transaction.getTransactionType() == TransactionTypes.INCOME) {
                image.setImageResource(R.drawable.income);
                accHolder = "From: " + "\n" + String.valueOf(transaction.getSender_AccountName());
                amountTV.setTextColor(Color.parseColor("#00BFA5"));
            }
            else if (transaction.getTransactionType() == TransactionTypes.EXPENSE){
                image.setImageResource(R.drawable.expense);
                accHolder = "To: " + String.valueOf(transaction.getRecipient_AccountName());
                amountTV.setTextColor(Color.parseColor("#D84315"));
            }

            String id = "transaction id: " + String.valueOf(transaction.getTransactionID());
            String amount = String.valueOf(transaction.getAmount()) + " AED";

            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(transaction.getDate().getTime());
            String date = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/" +
                    String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                    String.valueOf(calendar.get(Calendar.YEAR));


            //assign values to views
            idTV.setText(id);
            amountTV.setText(amount);
            accHolderTV.setText(accHolder);
            dateTV.setText(date);



        return item;


    }


    public char[] getMatchingChars(int size, String ID) {
        return ID.substring(0, size).toCharArray();
    }

}
