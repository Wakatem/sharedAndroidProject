package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.R;

public class TransactionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        Transaction transaction = (Transaction) getIntent().getSerializableExtra("transaction");

        setupToolbar("     Transaction  " + String.valueOf(transaction.getTransactionID()));

        ImageView image = findViewById(R.id.transactionType);
        TextView transactionAmount_TV = findViewById(R.id.transactionAmount_TV);

        TextView senderNameTV = findViewById(R.id.tv1);
        TextView senderNumberTV = findViewById(R.id.tv2);
        TextView RecipientNameTV = findViewById(R.id.tv3);
        TextView RecipientNumberTV = findViewById(R.id.tv4);
        TextView TransactionTypeTV = findViewById(R.id.tv5);
        TextView ExpenseTV     = findViewById(R.id.ExpenseTypeTV);
        TextView ExpenseTypeTV = findViewById(R.id.tv6);
        TextView PaymentMethodTV = findViewById(R.id.tv7);


        if (transaction.getTransactionType() == TransactionTypes.INCOME){

            transactionAmount_TV.setTextColor(getResources().getColor(R.color.income));
            image.setImageResource(R.drawable.income);

            ExpenseTV.setVisibility(View.GONE);
            ExpenseTypeTV.setVisibility(View.GONE);

        }
        else {

            transactionAmount_TV.setTextColor(getResources().getColor(R.color.expense));
            image.setImageResource(R.drawable.expense);

        }


        senderNameTV.setText(transaction.getSender_AccountName());
        senderNumberTV.setText(String.valueOf(transaction.getSender_AccountNumber()));

        RecipientNameTV.setText(transaction.getRecipient_AccountName());
        RecipientNumberTV.setText(String.valueOf(transaction.getRecipient_AccountNumber()));

        TransactionTypeTV.setText(transaction.getTransactionType().toString());
        PaymentMethodTV.setText(transaction.getPaymentMethod().toString());



    }



    private void setupToolbar(String title) {
        View toolbarIncluder = findViewById(R.id.toolbar_includer);
        TextView screenTitle = (TextView) toolbarIncluder.findViewById(R.id.screenTitle);
        screenTitle.setText(title);

        ImageView backButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);
        backButton.setImageResource(R.drawable.back_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}