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


    /**
     * Return Cost object based on duration
     */
    public int getCost(int hours, int minutes) {

        int minIndex = -1;
        boolean greaterFound = false;

        int maxIndex = -1;
        boolean lessFound = false;

        boolean ignoreDelay = true;
        if (minutes > 15)
            ignoreDelay = false;


        for (int i = size() - 1; i > -1; i--) {
            Cost currentCost = get(i);
            if (currentCost.getHours() == hours) {

                if (ignoreDelay){
                    if (currentCost.getType() == Cost.Type.EQUAL) {
                        //return amount here
                        return currentCost.getAmount();
                    }
                }else {
                    if (currentCost.getType() == Cost.Type.GREATER) {
                        //return amount here
                        return currentCost.getAmount();
                    }
                }


                if (minIndex != -1) {
                    //when iteration reaches cost containing hours matching "hours", and founds a range
                    //from lower-positioned costs, break
                    greaterFound = true;
                    break;
                }



            }
            else if (currentCost.getHours() < hours) {
                if (currentCost.getType() == Cost.Type.GREATER) {
                    minIndex = i;
                }
            }
            else if (currentCost.getHours() > hours) {
                if (currentCost.getType() == Cost.Type.LESS) {
                    maxIndex = i;
                    lessFound = true;
                    break;  //max possible cost found, break instantly
                }
            }

        }//for


        if (!greaterFound && !lessFound){

            //return -1  (no cost entry covers the duration)
            return -1;
        }else if (greaterFound){

            //return
            return get(minIndex).getAmount();

        }else if (lessFound){

            //return
            return get(maxIndex).getAmount();
        }
        else {

            //return error code -2 (unexpected behaviour)
            return -2;
        }


    }


    @Override
    public boolean add(Cost cost) {
        boolean isAdded = super.add(cost);
        sortCosts();

        return isAdded;
    }


}
