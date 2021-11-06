package com.example.project242.MonetaryRates;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class CostsHandler extends ArrayList<Cost> {


    public CostsHandler() {
    }


    @Override
    public boolean add(Cost cost) {
        boolean isAdded = super.add(cost);
        //sortCosts();

        return isAdded;
    }


}
