package com.example.project242.MonetaryRates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.project242.Home.Home;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MonetaryRates extends AppCompatActivity {

    private ViewPager2 pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetary_rates);

        //setup for sections menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MR_drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setupToolbar("Monetary Rates", R.drawable.menu_icon);
        menu.setOptionSelectedListener();

        //setup tabs
        setupTabs();


        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pager.getCurrentItem() == 0){
                    addCost();

                }else {
                    addDiscount();
                }
            }
        });


    }


    void setupTabs(){
        //link viewpager with pager adapter
        pager = findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle(), this);
        pager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tabs);

        // link tabs to viewpager
        new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Costs");
                }
                else{
                    tab.setText("Discounts");
                }

            }
        }).attach();
    }



    void addCost(){
        Cost cost = new Cost(1, 1, Cost.Type.LESS);

        //create bottom sheet to add cost details
        BottomSheet addingSheet = new BottomSheet(true, this, R.layout.cost_sheet_item, cost, Home.costsList, CostsFragment.costsListView, CostsFragment.adapter);
        addingSheet.initializeViews();
        addingSheet.setListeners();
        addingSheet.show();
    }


    void addDiscount(){

    }






}