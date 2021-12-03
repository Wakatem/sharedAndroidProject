package com.example.project242;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project242.Home.HomeSection;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        boolean dataLoaded = loadData();

        while (!dataLoaded){
            //start background service

            //start home screen

            //terminate activity
        }

        //start
        startActivity(new Intent(this, HomeSection.class));
        finish();

    }

    private boolean loadData(){
        TextView username;
        TextView password;

        /*
        CostsHandler costsHandler;
        DiscountsHandler discountsHandler;
        TransactionsHandler transactionsHandler;
        ArrayList<Student> allStudentsArrayList;
        ArrayList<Student> currentStudentsArrayList;
        User currentUser;
        */

        //load database
        CentralJSON.loadJSON(this);



        //load data structures
        HomeSection.costsHandler              = CentralJSON.parseCosts();
        HomeSection.discountsHandler          = CentralJSON.parseDiscounts();
        HomeSection.transactionsHandler       = CentralJSON.parseTransactions();
        HomeSection.allStudentsArrayList      = CentralJSON.parseAllStudents();
        HomeSection.currentStudentsArrayList  = CentralJSON.parseCurrentStudents();

        //current user credentials
        View loginView = LayoutInflater.from(this).inflate(R.layout.login_page, new LinearLayout(this));
        username = loginView.findViewById(R.id.username1);
        password = loginView.findViewById(R.id.password);

        //String userName = "Mcmillan586";
        //String passWord = "Henry1217";

        HomeSection.currentUser = CentralJSON.findCurrentUser(username.getText().toString(),password.getText().toString());


        // Synchronise checkedInFlag of allStudentsArrayList with currentStudentsArrayList
        for (int i = 0; i <  HomeSection.allStudentsArrayList.size(); ++i) {
            for (int j = 0; j < HomeSection.currentStudentsArrayList.size(); ++j) {
                if ( HomeSection.allStudentsArrayList.get(i).getStudentID() == HomeSection.currentStudentsArrayList.get(j).getStudentID()) {
                    HomeSection.allStudentsArrayList.get(i).setCheckedInFlag(HomeSection.currentStudentsArrayList.get(j).getCheckedInFlag());
                    break;
                }
            }
        }


        return false;

    }



}