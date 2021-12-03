package com.example.project242.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.DataContainer;
import com.example.project242.Home.HomeSection;
import com.example.project242.R;

public class DisplayTransactions extends AppCompatActivity {

    private TransactionsHandler transactionsHandler = DataContainer.transactionsHandler;
    int searchSize = 0;
    public static String search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transactions);

        setupToolbar("Transactions List");

        ListView transactionsLV = (ListView) findViewById(R.id.transactionsLV);
        TransactionAdapter adapter = new TransactionAdapter(this, transactionsHandler);
        transactionsLV.setAdapter(adapter);

        transactionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Transaction t = transactionsHandler.get(pos);
                if (t.getTransactionType() == TransactionTypes.EXPENSE)
                    Toast.makeText(DisplayTransactions.this, "Expense", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DisplayTransactions.this, "Income", Toast.LENGTH_SHORT).show();
            }
        });



        ImageButton filetButton = findViewById(R.id.filter_button);
        filetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        /*

        Spinner sortingOptions = findViewById(R.id.sortingOptions);
        ArrayAdapter<CharSequence> SO_adapter = ArrayAdapter.createFromResource(this, R.array.sortingOptions, android.R.layout.simple_spinner_dropdown_item);
        sortingOptions.setAdapter(SO_adapter);
        sortingOptions.setSelection(1); //default value

        sortingOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                switch (pos){
                    case 0:
                        transactionsHandler.chooseSortingMethod(true, false, false, false);
                        break;
                    case 1:
                        transactionsHandler.chooseSortingMethod(false, true, false, false);
                        break;
                    case 2:
                        transactionsHandler.chooseSortingMethod(false, false, true, false);
                        break;
                    case 3:
                        transactionsHandler.chooseSortingMethod(false, false, false, true);
                        break;
                    default:
                        break;
                }

                transactionsHandler.sortTransactions();
                adapter.notifyDataSetChanged();
                transactionsLV.invalidateViews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Spinner trType = findViewById(R.id.transactionTypeSpinner);
        ArrayAdapter<CharSequence> trType_adapter = ArrayAdapter.createFromResource(this, R.array.transactionTypes, android.R.layout.simple_spinner_dropdown_item);
        trType.setAdapter(trType_adapter);
        trType.setSelection(0); //default value

        trType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    //All picked
                    transactionsHandler.chooseFilteringMethod(false, true, false, false);
                }
                else if (position == 1) {
                    //income picked
                    transactionsHandler.chooseFilteringMethod(false, false, true, false);
                }
                else {
                    //expenses picked
                    transactionsHandler.chooseFilteringMethod(false, false, false, true);
                }


                transactionsHandler.filterTransactions();
                transactionsHandler.sortTransactions();
                adapter.notifyDataSetChanged();
                transactionsLV.invalidateViews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
         */

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
                finish();
            }
        });

    }


    public char[] getMatchingChars(int size, String ID) {
        return ID.substring(0, size).toCharArray();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_transactions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}