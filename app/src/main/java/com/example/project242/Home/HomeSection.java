package com.example.project242.Home;

import static com.example.project242.R.drawable.new_color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.CentralJSON;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.SectionsMenu;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.User;

import java.util.ArrayList;

public class HomeSection extends AppCompatActivity {

    EditText username;
    EditText password;

    public static CostsHandler costsHandler;
    public static DiscountsHandler discountsHandler;
    public static TransactionsHandler transactionsHandler;
    public static ArrayList<Student> allStudentsArrayList;
    public static ArrayList<Student> currentStudentsArrayList;
    public static User currentUser;

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

        //current user credentials
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password);

        String userName = "Mcmillan586";
        String passWord = "Henry1217";

        currentUser = CentralJSON.parseUsers(userName,passWord);





        // Synchronise checkedInFlag of allStudentsArrayList with currentStudentsArrayList
        for (int i = 0; i < allStudentsArrayList.size(); ++i) {
            for (int j = 0; j < currentStudentsArrayList.size(); ++j) {
                if (allStudentsArrayList.get(i).getStudentID() == currentStudentsArrayList.get(j).getStudentID()) {
                    allStudentsArrayList.get(i).setCheckedInFlag(currentStudentsArrayList.get(j).getCheckedInFlag());
                    break;
                }
            }
        }
    }
}
