package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project242.R;
import com.example.project242.general.DataContainer;
import com.example.project242.general.SectionsMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imangazaliev.circlemenu.CircleMenu;

public class TransactionsSection extends AppCompatActivity {

    private int savedSize = 0;
    private TextView profitTV;
    private TextView incomeTV;
    private TextView expenseTV;
    private DrawerLayout drawer;
    private final int duration = 250;
    private boolean menuClosed = true;

    private float originalX;
    private float originalY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        setupToolbarAndMenu("Transactions", 2);

        profitTV = findViewById(R.id.profitAmount);
        incomeTV = findViewById(R.id.incomeAmount);
        expenseTV = findViewById(R.id.expenseAmount);




        FloatingActionButton optionsButton = findViewById(R.id.transaction_optionsB);
        originalX = optionsButton.getTranslationX();
        originalY = optionsButton.getTranslationY();

        FloatingActionButton addButton = findViewById(R.id.transaction_add);
        FloatingActionButton listButton = findViewById(R.id.transaction_list);
        FloatingActionButton generateButton = findViewById(R.id.transaction_generate_statements);


        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuClosed) {
                    //open menu
                    animateFAB(addButton, duration, 0, -210, false);
                    animateFAB(listButton, duration, -290, 0, false);
                    animateFAB(generateButton, duration, 290, 0, false);

                    menuClosed = false;

                } else {
                    //close menu
                    animateFAB(addButton, duration, 0, 0, true);
                    animateFAB(listButton, duration, 0, 0, true);
                    animateFAB(generateButton, duration, 0, 0, true);

                    menuClosed = true;
                }

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTransactionSheet newsheet = new NewTransactionSheet(TransactionsSection.this, R.layout.add_transaction_sheet);
                newsheet.initializeTransactionView();
                newsheet.setTransactionListener();
                newsheet.show();

                //close menu
                animateFAB(addButton, duration, 0, 0, true);
                animateFAB(listButton, duration, 0, 0, true);
                animateFAB(generateButton, duration, 0, 0, true);

                menuClosed = true;
            }
        });


        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close menu
                animateFAB(addButton, duration, 0, 0, true);
                animateFAB(listButton, duration, 0, 0, true);
                animateFAB(generateButton, duration, 0, 0, true);

                menuClosed = true;
                startActivity(new Intent(TransactionsSection.this, DisplayTransactions.class));
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close menu
                animateFAB(addButton, duration, 0, 0, true);
                animateFAB(listButton, duration, 0, 0, true);
                animateFAB(generateButton, duration, 0, 0, true);

                menuClosed = true;


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


    private void animateFAB(FloatingActionButton fab, int duration, int pushHorizontally, int pushVertically, boolean resetPosition) {

        ObjectAnimator animator;
        float valueX;
        float valueY;

        if (resetPosition) {
            valueX = originalX;
            valueY = originalY;
        } else {
            valueX = pushHorizontally;
            valueY = pushVertically;
        }


        animator = ObjectAnimator.ofFloat(fab, "translationX", valueX);
        animator.setDuration(duration);
        animator.start();

        animator = ObjectAnimator.ofFloat(fab, "translationY", valueY);
        animator.setDuration(duration);
        animator.start();


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

        //update statistical details on list changes
        if (DataContainer.transactionsHandler.size() != savedSize){
            //save new size
            savedSize = DataContainer.transactionsHandler.size();

            //calculate total
            int incomeTotal=0, expenseTotal=0;
            for (int i = 0; i < DataContainer.transactionsHandler.size(); i++) {
                if (DataContainer.transactionsHandler.get(i).getTransactionType() == TransactionTypes.INCOME)
                    incomeTotal += DataContainer.transactionsHandler.get(i).getAmount();
                else
                    expenseTotal+= DataContainer.transactionsHandler.get(i).getAmount();
            }

            int profit = incomeTotal - expenseTotal;
            profitTV.setText(String.valueOf(profit)+" AED");
            incomeTV.setText(String.valueOf(incomeTotal)+" AED");
            expenseTV.setText(String.valueOf(expenseTotal)+" AED");
        }

    }
}