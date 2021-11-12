package com.example.project242.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.project242.MonetaryRates.PagerAdapter;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class StudentsSection extends AppCompatActivity {

    ViewPager2 pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setToolbarTitle("Students");
        menu.setOptionSelectedListener();

        //setup tabs
        setupTabs();
    }



    private void setupTabs(){
        //link viewpager with pager adapter
        pager = findViewById(R.id.activity_main_viewPager2_1);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tabs);

        // link tabs to viewpager
        new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("All Students");
                }
                else{
                    tab.setText("Current Students");
                }

            }
        }).attach();
    }

}