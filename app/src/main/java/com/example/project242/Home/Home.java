package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.project242.CentralJSON;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.example.project242.Transactions.TransactionsHandler;

public class Home extends AppCompatActivity {

    public static CostsHandler costsHandler;
    public static DiscountsHandler discountsHandler;
    public static TransactionsHandler transactionsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawer);

        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setupToolbar("Home", R.drawable.menu_icon);
        menu.setOptionSelectedListener();

        //home activity will remain active throughout the entire application lifecycle
        //TODO here, create all lists will be used across different sections

        CentralJSON.loadJSON(this);


        costsHandler = CentralJSON.parseCosts();
        discountsHandler = CentralJSON.parseDiscounts();
        transactionsHandler = CentralJSON.parseTransactions();



    }

}
