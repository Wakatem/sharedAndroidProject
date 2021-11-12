package com.example.project242;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project242.Home.HomeSection;
import com.example.project242.MonetaryRates.MonetaryRatesSection;
import com.example.project242.Settings.SettingsSection;
import com.example.project242.Students.StudentsSection;
import com.example.project242.Transactions.TransactionsSection;
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
        toolbar     = (Toolbar)   includer.findViewById(R.id.app_toolbar);
        screenTitle = (TextView)  includer.findViewById(R.id.screenTitle);
        menuButton  = (ImageView) includer.findViewById(R.id.menu_button);

        navView = (NavigationView) drawer.findViewById(R.id.navigation_view);

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
                boolean status = false;

                switch (item.getItemId()) {
                    case R.id.HomeSection:
                        if (!(activity instanceof HomeSection))
                            //destroy current activity and go back to home automatically
                            activity.finish();
                        status = true;
                        break;


                    case R.id.StudentsSection:
                        if (!(activity instanceof StudentsSection)) {
                            activity.startActivity(new Intent(activity, StudentsSection.class));
                        }
                        status = true;
                        break;


                    case R.id.TransactionsSection:
                        if (!(activity instanceof TransactionsSection)) {
                            activity.startActivity(new Intent(activity, TransactionsSection.class));
                        }
                        status = true;
                        break;


                    case R.id.MonetaryRatesSection:
                        if (!(activity instanceof MonetaryRatesSection)) {
                            activity.startActivity(new Intent(activity, MonetaryRatesSection.class));
                        }
                        status = true;
                        break;


                    case R.id.Settings:
                        if (!(activity instanceof SettingsSection)) {
                            activity.startActivity(new Intent(activity, SettingsSection.class));
                        }
                        status = true;
                        break;

                }//switch

                if (status == true) {
                    if (!(activity instanceof HomeSection)) {
                        activity.finish();
                    }
                }

                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


}
