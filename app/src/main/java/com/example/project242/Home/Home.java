package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project242.CentralJSON;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public static CostsHandler costsHandler;
    public static DiscountsHandler discountsHandler;
    public static TransactionsHandler transactionsHandler;
    public static ArrayList<Student> allStudentsArrayList;
    public static ArrayList<Student> currentStudentsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setToolbarTitle("Home");
        menu.setOptionSelectedListener();


        //load database
        CentralJSON.loadJSON(this);


        //load data structures
        costsHandler              = CentralJSON.parseCosts();
        discountsHandler          = CentralJSON.parseDiscounts();
        transactionsHandler       = CentralJSON.parseTransactions();
        allStudentsArrayList      = CentralJSON.parseAllStudents();
        currentStudentsArrayList  = CentralJSON.parseCurrentStudents();



    }

}
