package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;
import com.example.project242.SectionsMenu;

public class DisplayTransactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transactions);

        //setup Toolbar
        View includer = findViewById(R.id.includer);
        setupToolbar(includer, "TransactionsList");

        ListView transactionsLV = (ListView) findViewById(R.id.transactionsLV);
        TransactionAdapter adapter = new TransactionAdapter(this, HomeSection.transactionsHandler);
        transactionsLV.setAdapter(adapter);

    }



    private void setupToolbar(View includer, String title){
        Toolbar toolbar       = (Toolbar)   includer.findViewById(R.id.app_toolbar);
        TextView screenTitle  = (TextView)  includer.findViewById(R.id.screenTitle);
        ImageView backButton  = (ImageView) includer.findViewById(R.id.menu_button);

        screenTitle.setText(title);
        backButton.setImageResource(R.drawable.back_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}