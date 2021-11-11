package com.example.project242;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project242.Home.Home;
import com.example.project242.MonetaryRates.MonetaryRates;
import com.example.project242.Settings.Settings;
import com.example.project242.Students.Students;
import com.example.project242.Transactions.Transactions;
import com.google.android.material.navigation.NavigationView;

/**
 * This class initializes and activates the necessary components for a navigation menu functionality.
 * This class automatically initializes the custom toolbar with it.
 */

public class SectionsMenu {

    private DrawerLayout drawer;
    private View includer;
    private TextView screenTitle;
    private ImageView menuButton;
    private Toolbar toolbar;
    private NavigationView navView;
    private final Activity activity;


    public SectionsMenu(Activity activity, DrawerLayout activityDrawer) {
        //provided layout must match the layout the provided activity uses
        this.drawer = activityDrawer;
        this.activity = activity;
        includer = drawer.findViewById(R.id.includer);

    }

    public void initialize() {
        toolbar     = (Toolbar)    includer.findViewById(R.id.app_toolbar);
        screenTitle = (TextView)   includer.findViewById(R.id.screenTitle);
        menuButton  = (ImageView)  includer.findViewById(R.id.menu_button);

        navView     = (NavigationView) drawer.findViewById(R.id.navigation_view);

    }

    public void setToolbarTitle(String title) {
        screenTitle.setText(title);
    }

    public void setOptionSelectedListener() {
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);

            }
        });


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.HomeSection:
                        //if current activity is not Home
                        if (!(activity instanceof Home))
                            activity.startActivity(new Intent(activity, Home.class));

                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.StudentsSection:
                        if (!(activity instanceof Students))
                            activity.startActivity(new Intent(activity, Students.class));
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.TransactionsSection:
                        if (!(activity instanceof Transactions))
                            activity.startActivity(new Intent(activity, Transactions.class));
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.MonetaryRatesSection:
                        if (!(activity instanceof MonetaryRates))
                            activity.startActivity(new Intent(activity, MonetaryRates.class));
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.Settings:
                        if (!(activity instanceof Settings))
                            activity.startActivity(new Intent(activity, Settings.class));
                        drawer.closeDrawer(GravityCompat.START);
                        return true;

                }

                return false;
            }
        });
    }


}
