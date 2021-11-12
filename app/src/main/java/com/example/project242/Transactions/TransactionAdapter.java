package com.example.project242.Transactions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.project242.R;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private final TransactionsHandler handler;
    private final Context context;

    public TransactionAdapter(Context context, TransactionsHandler handler) {
        super(context, 0, handler);
        this.context = context;
        this.handler = handler;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item==null)
            item = LayoutInflater.from(context).inflate(R.layout.transaction_listview_item, parent, false);

        //get Transaction object
        Transaction transaction = handler.get(position);

        //link views
        ImageView image      = item.findViewById(R.id.transactionType);
        TextView idTV        = item.findViewById(R.id.transactionID_TV);
        TextView amountTV    = item.findViewById(R.id.transactionAmount_TV);
        TextView accHolderTV = item.findViewById(R.id.transactionAccHolder_TV);
        TextView dateTV      = item.findViewById(R.id.transactionDate_TV);

        //retrieve values

        String accHolder="";
        if (transaction.getTransactionType() == TransactionTypes.INCOME) {
            image.setImageResource(R.drawable.income);
            accHolder = "From: " + String.valueOf(transaction.getSender_AccountName());
        }
        else {
            image.setImageResource(R.drawable.expense);
            accHolder = "To: " + String.valueOf(transaction.getRecipient_AccountName());
        }

        String id     = String.valueOf(transaction.getTransactionID());
        String amount = String.valueOf(transaction.getAmount());
        String date   = String.valueOf(transaction.getDate().getDay());


        //assign values to views
        idTV.setText(id);
        amountTV.setText(amount);
        accHolderTV.setText(accHolder);
        dateTV.setText(date);

        return item;
    }
}
