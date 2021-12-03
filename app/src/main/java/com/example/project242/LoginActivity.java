package com.example.project242;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project242.Home.HomeSection;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.general.CentralJSON;
import com.example.project242.general.DataContainer;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    volatile boolean dataLoaded = false;

    private TextView username;
    private TextView password;
    private CostsHandler costsHandler;
    private DiscountsHandler discountsHandler;
    private TransactionsHandler transactionsHandler;
    private ArrayList<Student> allStudentsArrayList;
    private ArrayList<Student> currentStudentsArrayList;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadData();

        while (!dataLoaded){
            //wait
            //loading screen here
        }


        Intent intent = new Intent(this, DataContainer.class);
        intent.putExtra("costsHandler", costsHandler);
        intent.putExtra("discountsHandler", discountsHandler);
        intent.putExtra("transactionsHandler", transactionsHandler);
        intent.putExtra("allStudentsArrayList", allStudentsArrayList);
        intent.putExtra("currentStudentsArrayList", currentStudentsArrayList);
        intent.putExtra("currentUser", currentUser);


        new Thread(new Runnable() {
            @Override
            public void run() {
                //start background service
                startService(intent);
            }
        }).start();

        //start home screen
        startActivity(new Intent(this, HomeSection.class));

        //terminate activity
        finish();

    }


    private void loadData(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                //load database
                CentralJSON.loadJSON(LoginActivity.this);


                //load data structures
                costsHandler              = CentralJSON.parseCosts();
                discountsHandler          = CentralJSON.parseDiscounts();
                transactionsHandler       = CentralJSON.parseTransactions();
                allStudentsArrayList      = CentralJSON.parseAllStudents();
                currentStudentsArrayList  = CentralJSON.parseCurrentStudents();

                //current user credentials
                username = findViewById(R.id.username1);
                password = findViewById(R.id.password);

                //String userName = "Mcmillan586";
                //String passWord = "Henry1217";

                currentUser = CentralJSON.findCurrentUser(username.getText().toString(),password.getText().toString());


                // Synchronise checkedInFlag of allStudentsArrayList with currentStudentsArrayList
                for (int i = 0; i <  allStudentsArrayList.size(); ++i) {
                    for (int j = 0; j < currentStudentsArrayList.size(); ++j) {
                        if ( allStudentsArrayList.get(i).getStudentID() == currentStudentsArrayList.get(j).getStudentID()) {
                            allStudentsArrayList.get(i).setCheckedInFlag(currentStudentsArrayList.get(j).getCheckedInFlag());
                            break;
                        }
                    }
                }


                    dataLoaded = true;


            }

        }).start();


        /*
        CostsHandler costsHandler;
        DiscountsHandler discountsHandler;
        TransactionsHandler transactionsHandler;
        ArrayList<Student> allStudentsArrayList;
        ArrayList<Student> currentStudentsArrayList;
        User currentUser;
        */



    }



}