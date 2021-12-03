package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project242.R;
import com.example.project242.zNavigationMenu.SectionsMenu;

public class TransactionsSection extends AppCompatActivity {

    ImageButton button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        View includer = findViewById(R.id.includer);
        setupToolbarAndMenu(includer, "Transactions", 2);

        
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


    private void setupToolbarAndMenu(View includer, String title, int checkedSection){

        //setup Toolbar
        TextView screenTitle = (TextView) includer.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) includer.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, includer);
        menu.initialize(checkedSection);
        menu.EnableMenu();
    }


}