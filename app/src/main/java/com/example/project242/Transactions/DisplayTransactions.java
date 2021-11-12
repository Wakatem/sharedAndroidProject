package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.ListView;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;
import com.example.project242.SectionsMenu;

public class DisplayTransactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transactions);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setToolbarTitle("Transactions List");
        menu.setOptionSelectedListener();

        ListView transactionsLV = (ListView) findViewById(R.id.transactionsLV);
        TransactionAdapter adapter = new TransactionAdapter(this, HomeSection.transactionsHandler);
        transactionsLV.setAdapter(adapter);

    }
}