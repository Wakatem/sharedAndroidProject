package com.example.project242.MonetaryRates.Costs;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.project242.MonetaryRates.Costs.Cost;
import com.example.project242.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CostsHandler extends ArrayList<Cost> {


    public CostsHandler() {}


    public void editCost(@NonNull Cost chosenCost, int amount, int hours){

        //change all costs hours if EQUAL cost's hours changed
        if (chosenCost.getType() == CostTypes.EQUAL){
            for(Cost cost : this){
                cost.setHours(hours);
            }
        }

        chosenCost.setAmount(amount);
        chosenCost.setHours(hours);
    }


    /**
     * Return Cost object based on duration
     */
    public int getCost(int hours, int minutes) {

        if ((hours > this.get(0).getHours()) || (hours == this.get(0).getHours() && minutes > 15)){
            //GREATER than

            int totalTime= hours + 1;  // +1 represents the additional minutes (treated as extra one hour)
            int totalCost=140;

            for(Cost cost : this){
                if (cost.getType() == CostTypes.GREATER) {

                    //get additional costs per hour
                    while (totalTime != 0){
                        totalCost+=cost.getAmount();
                        totalTime--;
                    }

                    return totalCost;
                }

            }
        }

        else if ((hours == this.get(0).getHours()) || (hours == this.get(0).getHours() && minutes <= 15)){
            //EQUAL

            for(Cost cost : this){
                if (cost.getType() == CostTypes.EQUAL)
                    return cost.getAmount();
            }
        }

        else {
            //LESS
            for(Cost cost : this){
                if (cost.getType() == CostTypes.LESS)
                    return cost.getAmount();
            }
        }

        return -1;
    }



}
