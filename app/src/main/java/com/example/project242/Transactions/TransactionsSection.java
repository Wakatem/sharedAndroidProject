package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project242.R;
import com.example.project242.SectionsMenu;

public class TransactionsSection extends AppCompatActivity {

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

        Button button = findViewById(R.id.showList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransactionsSection.this, DisplayTransactions.class));
            }
        });

    }
}