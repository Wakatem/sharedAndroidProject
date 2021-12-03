package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project242.CentralJSON;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.zNavigationMenu.SectionsMenu;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.User;

import java.util.ArrayList;

public class HomeSection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View includer = findViewById(R.id.includer);
        setupToolbarAndMenu(includer, "Home", 0);


    }


    private void setupToolbarAndMenu(View includer, String title, int checkedSection){

        //setup Toolbar
        TextView screenTitle = (TextView) includer.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) includer.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, includer);
        menu.initialize(checkedSection);
        menu.EnableMenu();
    }

}
