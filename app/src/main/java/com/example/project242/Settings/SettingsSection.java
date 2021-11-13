package com.example.project242.Settings;

import static com.example.project242.R.drawable.app_bar_color;
import static com.example.project242.R.drawable.ic_mtrl_chip_checked_black;
import static com.example.project242.R.drawable.new_color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;
import com.example.project242.SectionsMenu;

public class SettingsSection extends AppCompatActivity {

    EditText typehere;
    TextView newEmail;
   // Button change;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setToolbarTitle("Settings");
        menu.setOptionSelectedListener();
        typehere = findViewById(R.id.enterNew);
        newEmail = findViewById(R.id.newEmail);
        save = findViewById(R.id.saveIt);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String newEmails = typehere.getText().toString();
                //HomeSection.currentUser.setEmail(typehere.getText().toString());
                newEmail.setText(HomeSection.currentUser.getEmail());
            }
        });







    }
}