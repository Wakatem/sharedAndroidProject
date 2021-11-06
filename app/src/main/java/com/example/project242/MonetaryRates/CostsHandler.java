package com.example.project242.MonetaryRates;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class CostsHandler extends ArrayList<Cost> {


    public CostsHandler() {
    }


    private void sortCosts() {
        for (int pos = 0; pos < size(); pos++) {

            //if next index is not OutOfBounds
            if (pos + 1 != size()) {
                Cost currentCost = get(pos);
                Cost nextCost = get(pos + 1);

                //compare current Cost with next Cost
                if (currentCost.getHours() < nextCost.getHours()) {
                    set(pos, nextCost);          // replace
                    set(pos + 1, currentCost);     // return current cost back to list

                    //adjust index
                    if (pos >= 1) {
                        pos = pos - 2;
                    }

                }
                //check both costs types (only if they got same duration)
                else if (nextCost.getHours() == currentCost.getHours()) {

                    if ((currentCost.getType() == Cost.Type.EQUAL) && (nextCost.getType() == Cost.Type.GREATER)) {
                        set(pos, nextCost);            // replace
                        set(pos + 1, currentCost);     // return current cost back to list

                        //adjust index
                        if (pos >= 1) {
                            pos = pos - 2;
                        }
                    }
                    if ((currentCost.getType() == Cost.Type.LESS) && (nextCost.getType() == Cost.Type.EQUAL)) {
                        set(pos, nextCost);            // replace
                        set(pos + 1, currentCost);     // return current cost back to list

                    }

                    if ((currentCost.getType() == Cost.Type.LESS) && (nextCost.getType() == Cost.Type.GREATER)) {
                        set(pos, nextCost);            // replace
                        set(pos + 1, currentCost);     // return current cost back to list

                        //adjust index
                        if (pos >= 1) {
                            pos = pos - 2;
                        }
                    }


                }


            }//1st if


        }//for


    }



    @Override
    public boolean add(Cost cost) {
        boolean isAdded = super.add(cost);
        sortCosts();

        return isAdded;
    }


}
