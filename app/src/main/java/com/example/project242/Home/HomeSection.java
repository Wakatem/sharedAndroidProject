package com.example.project242.Home;

import static com.example.project242.R.drawable.new_color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    TextView username;
    TextView password;

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
        setupToolbarAndMenu(includer, "Home");



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
    }


    private void setupToolbarAndMenu(View includer, String title){

        //setup Toolbar
        TextView screenTitle = (TextView) includer.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) includer.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, includer);
        menu.initialize();
        menu.EnableMenu();
    }

}
