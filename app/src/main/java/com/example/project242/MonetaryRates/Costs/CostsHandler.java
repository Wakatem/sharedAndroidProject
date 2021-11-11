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


    public void loadFromJSON(Context context){
        try {
            //open file
            InputStreamReader inputStreamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.nursery));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            //read file
            String line;
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }

            JSONObject root = new JSONObject(stringBuffer.toString());
            JSONObject MonetaryRates_obj = root.getJSONObject("Monetary Rates");
            JSONArray allCosts = MonetaryRates_obj.getJSONArray("Costs");

            for (int i = 0; i < allCosts.length(); i++) {
                JSONObject cost = allCosts.getJSONObject(i);

                //get cost duration details
                String durationString = cost.getString("duration");
                char arr[] = durationString.toCharArray();
                Cost.Type type;

                if (arr[1] == '>'){
                    type = Cost.Type.GREATER;
                }else if(arr[1] == '='){
                    type = Cost.Type.EQUAL;
                }else {
                    type = Cost.Type.LESS;
                }

                int hours= Integer.valueOf(String.valueOf(arr[2]));


                //get cost amount
                String amountString = cost.getString("amount");
                int amount = Integer.valueOf(amountString);


                //add new Cost
                add(new Cost(amount, type, hours));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "NotFound", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void editCost(@NonNull Cost chosenCost, int amount, int hours){

        //change all costs hours if EQUAL cost's hours changed
        if (chosenCost.getType() == Cost.Type.EQUAL){
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
                if (cost.getType() == Cost.Type.GREATER) {

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
                if (cost.getType() == Cost.Type.EQUAL)
                    return cost.getAmount();
            }
        }

        else {
            //LESS
            for(Cost cost : this){
                if (cost.getType() == Cost.Type.LESS)
                    return cost.getAmount();
            }
        }

        return -1;
    }



}
