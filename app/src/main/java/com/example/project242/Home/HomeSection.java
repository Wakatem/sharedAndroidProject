package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.project242.general.SectionsMenu;


public class HomeSection extends AppCompatActivity {

    TextView allstudents;
    TextView currentStudents;
    FrameLayout box1;
    FrameLayout box2;
    FrameLayout box3;
    FrameLayout box4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbarAndMenu("Home", 0);

        allstudents =  findViewById(R.id.InNo);
        currentStudents = findViewById(R.id.OutNo);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);



        allstudents.setText(String.valueOf(allStudentsArrayList.size()));
        currentStudents.setText(String.valueOf(currentStudentsArrayList.size()));

        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterStudentActivity.class);
                startActivity(intent);
            }
        });



        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentsSection.class);
                intent.putExtra("getCurrentStudentsFragment", 1);
                startActivity(intent);
            }
        });

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

}
