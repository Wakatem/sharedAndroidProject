package com.example.project242.MonetaryRates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MonetaryRatesSection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetary_rates);

        setupToolbarAndMenu("Monetary Rates", 3);

        ViewPager2 pager = findViewById(R.id.pager);
        setupTabs(pager);


    }

    private void setupToolbarAndMenu(String title, int checkedSection){

        //setup Toolbar
        View toolbarIncluder = findViewById(R.id.toolbar_includer);
        TextView screenTitle = (TextView) toolbarIncluder.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, toolbarIncluder);
        menu.initialize(checkedSection);
        menu.EnableMenu();
    }


    private void setupTabs(ViewPager2 pager){
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