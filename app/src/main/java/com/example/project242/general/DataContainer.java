package com.example.project242.general;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.project242.LoginActivity;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.Students.Guardian;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataContainer extends Service {

    private String username;
    private String password;

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

        //retrieve credentials
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        boolean dataLoaded = LoadData();
        Intent message = new Intent("LoadData");
        message.putExtra("dataLoaded", dataLoaded);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(message);


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    private boolean LoadData(){

        try {
            //load database
            CentralJSON.loadJSON(this);


            //load data structures
            costsHandler              = CentralJSON.parseCosts();
            discountsHandler          = CentralJSON.parseDiscounts();
            transactionsHandler       = CentralJSON.parseTransactions();
            allStudentsArrayList      = CentralJSON.parseStudentsLists()[0];
            currentStudentsArrayList  = CentralJSON.parseStudentsLists()[1];

            currentUser = CentralJSON.findCurrentUser(username,password);


            // Synchronise checkedInFlag of allStudentsArrayList with currentStudentsArrayList
            for (int i = 0; i <  allStudentsArrayList.size(); ++i) {
                for (int j = 0; j < currentStudentsArrayList.size(); ++j) {
                    if ( allStudentsArrayList.get(i).getStudentID() == currentStudentsArrayList.get(j).getStudentID()) {
                        allStudentsArrayList.get(i).setCheckedInFlag(currentStudentsArrayList.get(j).getCheckedInFlag());
                        break;
                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


        return true;
    }



}