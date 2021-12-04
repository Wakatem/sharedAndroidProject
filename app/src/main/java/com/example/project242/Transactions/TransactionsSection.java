package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project242.R;
import com.example.project242.general.SectionsMenu;

public class TransactionsSection extends AppCompatActivity {

    private ImageButton button1;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        setupToolbarAndMenu("Transactions", 2);

        
        Button button = findViewById(R.id.showList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransactionsSection.this, DisplayTransactions.class));
            }
        });

        button1=findViewById(R.id.newtransaction);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTransactionSheet newsheet = new NewTransactionSheet(TransactionsSection.this , R.layout.add_transaction_sheet);
                newsheet.initializeTransactionView();
                newsheet.setTransactionListener();
                newsheet.show();
            }
        });
    }


    private void setupToolbarAndMenu(String title, int checkedSection) {

        //setup Toolbar
        View toolbarIncluder = findViewById(R.id.toolbar_includer);
        TextView screenTitle = (TextView) toolbarIncluder.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, toolbarIncluder);
        menu.initialize(checkedSection);
        menu.EnableMenu();
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}