package com.example.project242;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
        /*
        CostsHandler costsHandler;
        DiscountsHandler discountsHandler;
        TransactionsHandler transactionsHandler;
        ArrayList<Student> allStudentsArrayList;
        ArrayList<Student> currentStudentsArrayList;
        User currentUser;
        */

        //load database
        CentralJSON.loadJSON(getApplicationContext());


        /*
        //load data structures
        HomeSection.costsHandler              = CentralJSON.parseCosts();
        HomeSection.discountsHandler          = CentralJSON.parseDiscounts();
        HomeSection.transactionsHandler       = CentralJSON.parseTransactions();
        allStudentsArrayList      = CentralJSON.parseAllStudents();
        currentStudentsArrayList  = CentralJSON.parseCurrentStudents();

        //current user credentials
        View loginView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.login_page, new LinearLayout(getApplicationContext()));
        username = loginView.findViewById(R.id.username1);
        password = loginView.findViewById(R.id.password);

        //String userName = "Mcmillan586";
        //String passWord = "Henry1217";

        currentUser = CentralJSON.findCurrentUser(username.getText().toString(),password.getText().toString());


        // Synchronise checkedInFlag of allStudentsArrayList with currentStudentsArrayList
        for (int i = 0; i < allStudentsArrayList.size(); ++i) {
            for (int j = 0; j < currentStudentsArrayList.size(); ++j) {
                if (allStudentsArrayList.get(i).getStudentID() == currentStudentsArrayList.get(j).getStudentID()) {
                    allStudentsArrayList.get(i).setCheckedInFlag(currentStudentsArrayList.get(j).getCheckedInFlag());
                    break;
                }
            }
        }
    */

        return false;
    }



}