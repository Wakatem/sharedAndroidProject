package com.example.project242;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.TextView;

import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;

import java.util.ArrayList;

public class DataContainer extends Service {
    TextView username;
    TextView password;

    public static CostsHandler costsHandler;
    public static DiscountsHandler discountsHandler;
    public static TransactionsHandler transactionsHandler;
    public static ArrayList<Student> allStudentsArrayList;
    public static ArrayList<Student> currentStudentsArrayList;
    public static User currentUser;

    public DataContainer() { }



    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}