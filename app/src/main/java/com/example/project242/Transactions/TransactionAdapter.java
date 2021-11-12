package com.example.project242.Transactions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project242.R;

public class TransactionAdapter extends BaseAdapter {

    private final TransactionsHandler handler;
    private final Context context;

    public TransactionAdapter(Context context, TransactionsHandler handler){
    this.context = context;
    this.handler = handler;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = view;
        if (item==null)
            item = LayoutInflater.from(context).inflate(R.layout.transaction_listview_item, viewGroup, false);

        //get Transaction object
        Transaction transaction = handler.get(i);

        //link views
        ImageView image      = item.findViewById(R.id.transactionType);
        TextView idTV        = item.findViewById(R.id.transactionID_TV);
        TextView amountTV    = item.findViewById(R.id.transactionAmount_TV);
        TextView accHolderTV = item.findViewById(R.id.transactionAccHolder_TV);
        TextView dateTV      = item.findViewById(R.id.transactionDate_TV);

        //retrieve values

        idTV.setText(String.valueOf(transaction.getTransactionID()));

        //assign values to views

        return item;
    }



    @Override
    public int getCount() {
        return handler.size();
    }

    @Override
    public Object getItem(int i) {
        return handler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i; //return position
    }
}
