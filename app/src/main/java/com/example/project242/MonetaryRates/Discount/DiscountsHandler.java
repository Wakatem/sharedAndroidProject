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


    }




