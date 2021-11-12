package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.project242.MonetaryRates.BottomSheet;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Transactions extends AppCompatActivity {

    ImageButton button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setToolbarTitle("Transactions");
        menu.setOptionSelectedListener();

        button1=findViewById(R.id.newtransaction);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTransactionSheet newsheet = new NewTransactionSheet(Transactions.this , R.layout.add_transaction_sheet);
                newsheet.initializeTransactionView();
                newsheet.setTransactionListener();
                newsheet.show();
            }
        });



    }
}