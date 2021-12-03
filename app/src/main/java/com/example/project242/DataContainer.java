package com.example.project242;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.example.project242.MonetaryRates.Costs.Cost;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.zNavigationMenu.SectionsMenu;

import java.util.ArrayList;

public class DataContainer extends Service {

    public static CostsHandler costsHandler = new CostsHandler();
    public static DiscountsHandler discountsHandler = new DiscountsHandler();
    public static TransactionsHandler transactionsHandler = new TransactionsHandler();
    public static ArrayList<Student> allStudentsArrayList = new ArrayList<>();
    public static ArrayList<Student> currentStudentsArrayList = new ArrayList<>();
    public static User currentUser = new User();

    public DataContainer() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //retrieve passed objects
        retrieveList(costsHandler, intent, "costsHandler");
        retrieveList(discountsHandler, intent, "discountsHandler");
        retrieveList(transactionsHandler, intent, "transactionsHandler");
        retrieveList(allStudentsArrayList, intent, "allStudentsArrayList");
        retrieveList(currentStudentsArrayList, intent, "currentStudentsArrayList");
        currentUser = (User) intent.getSerializableExtra("currentUser");

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private <T extends ArrayList> void retrieveList(T t, Intent intent, String name){
        ArrayList list = (ArrayList) intent.getSerializableExtra(name);
        for (int i=0; i < list.size(); i++) {
            t.add(0, list.get(i));
        }

    }
}