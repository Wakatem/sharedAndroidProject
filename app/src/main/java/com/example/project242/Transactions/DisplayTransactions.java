package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.general.DataContainer;
import com.example.project242.R;


public class DisplayTransactions extends AppCompatActivity {


    private ListView transactionsLV;
    private TransactionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transactions);
        setupToolbar("Transactions List");

        transactionsLV = (ListView) findViewById(R.id.transactionsLV);
        adapter = new TransactionAdapter(this, DataContainer.transactionsHandler);
        transactionsLV.setAdapter(adapter);

        transactionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Transaction t = DataContainer.transactionsHandler.get(pos);

            }
        });


        TextView filterTV = findViewById(R.id.filteringValue);
        TextView sortTV   = findViewById(R.id.sortingValue);

        //initialize starting values
        filterTV.setText(DataContainer.transactionsHandler.getFilteringMethod());
        sortTV.setText(DataContainer.transactionsHandler.getSortingMethod());

        setupFilteringButton(filterTV);
        setupSortingButton(sortTV);



        transactionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Transaction transaction = DataContainer.transactionsHandler.get(position);
                Intent intent = new Intent(DisplayTransactions.this, TransactionDetails.class);
                intent.putExtra("transaction", transaction);
                startActivity(intent);
            }
        });


    }




    private void setupFilteringButton(TextView filterTV){
        //setup filter button with its options
        ImageButton filterButton = findViewById(R.id.filter_button);
        PopupMenu filterMenu = new PopupMenu(this, filterButton);
        MenuInflater filterMenuInflater = filterMenu.getMenuInflater();
        filterMenuInflater.inflate(R.menu.filter_transactions, filterMenu.getMenu());

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filterMenu.show();

            }
        });

        filterMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.transactions_all:
                        //All picked
                        DataContainer.transactionsHandler.chooseFilteringMethod( true, false, false);
                        filterTV.setText("All");
                        break;
                    case R.id.transactions_income:
                        //income picked
                        DataContainer.transactionsHandler.chooseFilteringMethod(false, true, false);
                        filterTV.setText("Income");
                        break;
                    case R.id.transactions_expense:
                        //expenses picked
                        DataContainer.transactionsHandler.chooseFilteringMethod(false, false, true);
                        filterTV.setText("Expense");
                        break;

                }

                DataContainer.transactionsHandler.filterTransactions();
                DataContainer.transactionsHandler.sortTransactions();
                adapter.notifyDataSetChanged();
                transactionsLV.invalidateViews();

                return false;
            }
        });
    }




    private void setupSortingButton(TextView sortTV){


        //setup sort button with its options
        ImageButton sortButton = findViewById(R.id.sort_button);
        PopupMenu sortMenu = new PopupMenu(this, sortButton);
        MenuInflater sortMenuInflater = sortMenu.getMenuInflater();
        sortMenuInflater.inflate(R.menu.sort_transactions, sortMenu.getMenu());

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortMenu.show();

            }
        });

        sortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.descendingDate:
                        DataContainer.transactionsHandler.chooseSortingMethod(true, false, false, false);
                        sortTV.setText("Descending Date");
                        break;
                    case R.id.ascendingDate:
                        DataContainer.transactionsHandler.chooseSortingMethod(false, true, false, false);
                        sortTV.setText("Ascending Date");
                        break;
                    case R.id.descendingAmount:
                        DataContainer.transactionsHandler.chooseSortingMethod(false, false, true, false);
                        sortTV.setText("Descending Amount");
                        break;
                    case R.id.ascendingAmount:
                        DataContainer.transactionsHandler.chooseSortingMethod(false, false, false, true);
                        sortTV.setText("Ascending Amount");
                        break;

                }

                DataContainer.transactionsHandler.filterTransactions();
                DataContainer.transactionsHandler.sortTransactions();
                adapter.notifyDataSetChanged();
                transactionsLV.invalidateViews();

                return false;
            }
        });
    }

    private void setupToolbar(String title) {
        View toolbarIncluder = findViewById(R.id.toolbar_includer);
        TextView screenTitle = (TextView) toolbarIncluder.findViewById(R.id.screenTitle);
        screenTitle.setText(title);

        ImageView backButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);
        backButton.setImageResource(R.drawable.back_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show all statistical details again
                DataContainer.transactionsHandler.chooseFilteringMethod( true, false, false);
                DataContainer.transactionsHandler.chooseSortingMethod(false, true, false, false);

                DataContainer.transactionsHandler.filterTransactions();
                DataContainer.transactionsHandler.sortTransactions();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        //show all statistical details again
        DataContainer.transactionsHandler.chooseFilteringMethod( true, false, false);
        DataContainer.transactionsHandler.chooseSortingMethod(false, true, false, false);

        DataContainer.transactionsHandler.filterTransactions();
        DataContainer.transactionsHandler.sortTransactions();

        super.onBackPressed();
    }
}