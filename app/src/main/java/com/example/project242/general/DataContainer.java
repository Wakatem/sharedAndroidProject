package com.example.project242.general;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.Students.Guardian;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        //set guardians here as the chain of serialized/paracled objects affects data
        boolean guardiansSet = setGuardians();
        Intent message = new Intent("setGuardiansResult");
        message.putExtra("guardiansSet", guardiansSet);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(message);


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


    public boolean setGuardians(){
        JSONObject Students;
        JSONArray studentsJSONArray;
        JSONObject studentDetails;
        Guardian guardian;

        try {
            Students = CentralJSON.root.getJSONObject("Students");
            studentsJSONArray = Students.getJSONArray("All Students");

            for (int i = 0; i < studentsJSONArray.length(); ++i) {
                studentDetails = studentsJSONArray.getJSONObject(i);
                guardian = CentralJSON.parseStudentGuardian(studentDetails);
                allStudentsArrayList.get(i).setGuardian(guardian);

                //load current students list simultaneously
                if (i < currentStudentsArrayList.size()){
                    studentDetails = Students.getJSONArray("Current Students").getJSONObject(i);
                    guardian = CentralJSON.parseStudentGuardian(studentDetails);
                    currentStudentsArrayList.get(i).setGuardian(guardian);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}