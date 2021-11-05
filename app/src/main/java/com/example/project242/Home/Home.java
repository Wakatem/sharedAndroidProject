package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project242.MonetaryRates.Cost;
import com.example.project242.MonetaryRates.MonetaryRates;
import com.example.project242.R;
import com.example.project242.SectionsMenu;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public static ArrayList<Cost> costsList;

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

        costsList = new ArrayList<>();
        costsList.add(new Cost(120, 4, Cost.Type.EQUAL));
        costsList.add(new Cost(220, 5, Cost.Type.GREATER));
        costsList.add(new Cost(340, 6, Cost.Type.LESS));


    }


    private void loadFromJSON(){}

}
