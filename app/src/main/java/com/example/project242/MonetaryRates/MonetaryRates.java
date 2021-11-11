package com.example.project242.MonetaryRates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.project242.Home.Home;
import com.example.project242.MonetaryRates.Discount.DiscountsFragment;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MonetaryRates extends AppCompatActivity {

    private ViewPager2 pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetary_rates);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.MR_drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setupToolbar("Monetary Rates", R.drawable.menu_icon);
        menu.setOptionSelectedListener();

        //setup tabs
        setupTabs();


    }


    void setupTabs(){
        //link viewpager with pager adapter
        pager = findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle());
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



    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}