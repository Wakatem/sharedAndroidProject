package com.example.project242.MonetaryRates.Discount;

import android.content.Context;
import android.widget.Toast;

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

public class DiscountsHandler extends ArrayList<Discount> {

    public DiscountsHandler(){

    }

    public void newDiscount(String discountName, int percentage, boolean switchstatus){
        new Discount(discountName,percentage,switchstatus);
    }

    public void getJSON(Context context){
        try{
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
            JSONArray alldiscounts = MonetaryRates_obj.getJSONArray("Discounts");

            for (int i = 0; i < alldiscounts.length(); i++) {
                JSONObject discount = alldiscounts.getJSONObject(i);

                String discounttype = discount.getString("Title");
                String percentage1 = discount.getString("Percentage");
                int percentage= Integer.valueOf(percentage1);
                String switchValue = discount.getString("Active");
                boolean switchValue1 = Boolean.valueOf(switchValue);

                add(new Discount(discounttype,percentage,switchValue1));



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
    }




