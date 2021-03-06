package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.TextView;


import com.example.project242.R;
import com.example.project242.Students.RegisterStudentActivity;
import com.example.project242.Students.StudentsSection;
import com.example.project242.Transactions.TransactionsSection;
import com.example.project242.general.DataContainer;
import com.example.project242.general.SectionsMenu;


public class HomeSection extends AppCompatActivity {

    private TextView allstudents;
    private TextView currentStudents;
    private FrameLayout box2;
    private FrameLayout box1;
    private FrameLayout box3;
    private FrameLayout box4;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbarAndMenu("Home", 0);

        allstudents = findViewById(R.id.InNo);
        currentStudents = findViewById(R.id.OutNo);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);


        allstudents.setText(String.valueOf(DataContainer.allStudentsArrayList.size()));
        currentStudents.setText(String.valueOf(DataContainer.currentStudentsArrayList.size()));

        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeSection.this, RegisterStudentActivity.class);
                startActivity(intent);
            }
        });


        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeSection.this, StudentsSection.class);
                intent.putExtra("getCurrentStudentsFragment", 1);
                startActivity(intent);
            }
        });


        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeSection.this, TransactionsSection.class);
                startActivity(intent);
            }
        });

    }


    private void setupToolbarAndMenu(String title, int checkedSection) {

        //setup Toolbar
        View toolbarIncluder = findViewById(R.id.toolbar_includer);
        TextView screenTitle = (TextView) toolbarIncluder.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, toolbarIncluder);
        menu.initialize(checkedSection);
        menu.EnableMenu();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
