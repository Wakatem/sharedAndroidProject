package com.example.project242.MonetaryRates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MonetaryRates extends AppCompatActivity {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggler;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetary_rates);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MR_drawer);

        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setupToolbar("Monetary Rates", R.drawable.menu_icon);
        menu.setOptionSelectedListener();

        setupTabs();


    }


    void setupTabs(){
        //link viewpager with pager adapter
        ViewPager2 pager = findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle(), this);
        pager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tabs);

        // link tabs to viewpager
        new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0)
                    tab.setText("Costs");
                else
                    tab.setText("Discounts");

            }
        }).attach();
    }



}