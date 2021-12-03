package com.example.project242.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project242.CentralJSON;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.Students.FragmentAdapter;
import com.example.project242.Students.FragmentCurrentStudents;
import com.example.project242.Students.RegisterStudentActivity;
import com.example.project242.Students.StudentsSection;
import com.example.project242.Transactions.NewTransactionSheet;
import com.example.project242.Transactions.TransactionsSection;
import com.example.project242.zNavigationMenu.SectionsMenu;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.User;

import java.util.ArrayList;

public class HomeSection extends AppCompatActivity {

    TextView username;
    TextView password;
    TextView allstudents;
    TextView currentStudents;
    FrameLayout box1;
    FrameLayout box2;
    FrameLayout box3;
    FrameLayout box4;

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

        View includer = findViewById(R.id.includer);
        setupToolbarAndMenu(includer, "Home", 0);



        //load database
        CentralJSON.loadJSON(this);


        //load data structures
        costsHandler              = CentralJSON.parseCosts();
        discountsHandler          = CentralJSON.parseDiscounts();
        transactionsHandler       = CentralJSON.parseTransactions();
        allStudentsArrayList      = CentralJSON.parseAllStudents();
        currentStudentsArrayList  = CentralJSON.parseCurrentStudents();

        //current user credentials
        View loginView = LayoutInflater.from(this).inflate(R.layout.login_page, new LinearLayout(this));
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
