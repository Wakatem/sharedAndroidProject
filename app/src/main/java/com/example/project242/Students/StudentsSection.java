package com.example.project242.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project242.R;
import com.example.project242.zNavigationMenu.SectionsMenu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class StudentsSection extends AppCompatActivity {

    private ImageView addStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        View includer = findViewById(R.id.includer);
        setupToolbarAndMenu(includer, "Students", 1);

        ViewPager2 pager = findViewById(R.id.pager);
        setupTabs(pager);


        addStudentButton = findViewById(R.id.addStudentButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterStudentActivity.class);
                startActivity(intent);
            }
        });

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



    private void setupTabs(ViewPager2 pager){
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