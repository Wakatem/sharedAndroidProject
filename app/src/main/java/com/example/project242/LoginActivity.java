package com.example.project242;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            boolean guardiansSet = intent.getBooleanExtra("dataLoaded", false);
            if (guardiansSet) {
                //start home screen
                startActivity(new Intent(LoginActivity.this, HomeSection.class));
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(this, DataContainer.class);

        //current user credentials
        TextView username = findViewById(R.id.username1);
        TextView password = findViewById(R.id.password);
        intent.putExtra("username", username.getText().toString());
        intent.putExtra("password", password.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                //start background service
                startService(intent);
            }
        }).start();


    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("LoadData"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);

        //terminate login activity
        finish();
    }

}