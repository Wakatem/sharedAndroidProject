package com.example.project242.general;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project242.Home.HomeSection;
import com.example.project242.MonetaryRates.MonetaryRatesSection;
import com.example.project242.R;
import com.example.project242.Settings.SettingsSection;
import com.example.project242.Students.StudentsSection;
import com.example.project242.Transactions.TransactionsSection;
import com.google.android.material.navigation.NavigationView;

/**
 * This class initializes and activates the necessary components for a navigation menu functionality.
 */

public class SectionsMenu {

    private DrawerLayout drawer;
    private View toolbarIncluder;
    private ImageView menuButton;
    private NavigationView navView;
    private final Activity activity;


    public SectionsMenu(Activity activity, DrawerLayout activityDrawer, View toolbarIncluder) {
        //provided layout must match the layout the provided activity uses
        this.drawer = activityDrawer;
        this.activity = activity;
        this.toolbarIncluder = toolbarIncluder;


    }

    public void initialize(int checkedSection) {
        menuButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);

        navView = (NavigationView) drawer.findViewById(R.id.navigation_view);
        navView.getMenu().getItem(checkedSection).setChecked(true);
    }


    public void EnableMenu() {
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
                        if (!(activity instanceof HomeSection)) {
                            activity.startActivity(new Intent(activity, HomeSection.class));
                            activity.finish();
                        }
                        break;

                    case R.id.StudentsSection:
                        if (!(activity instanceof StudentsSection)) {
                            activity.startActivity(new Intent(activity, StudentsSection.class));
                            activity.finish();
                        }
                        break;

                    case R.id.TransactionsSection:
                        if (!(activity instanceof TransactionsSection)) {
                            activity.startActivity(new Intent(activity, TransactionsSection.class));
                            activity.finish();
                        }
                        break;

                    case R.id.MonetaryRatesSection:
                        if (!(activity instanceof MonetaryRatesSection)) {
                            activity.startActivity(new Intent(activity, MonetaryRatesSection.class));
                            activity.finish();
                        }
                        break;

                    case R.id.SettingsSection:
                        if (!(activity instanceof SettingsSection)) {
                            activity.startActivity(new Intent(activity, SettingsSection.class));
                            activity.finish();
                        }
                        break;

                }

                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }



}
