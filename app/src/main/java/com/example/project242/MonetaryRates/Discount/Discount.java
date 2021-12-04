package com.example.project242.MonetaryRates.Discount;
import android.widget.Switch;

import java.io.Serializable;

public class Discount implements Serializable {
    private String discountName;
    private int percent;


    public Discount(String discountName, int percent){
        this.discountName=discountName;
        this.percent=percent;
    }

    public String getDiscountName() {

        return discountName;
    }

    public void setDiscountName(String discountName) {

        this.discountName = discountName;
    }

    public int getPercent() {

        return percent;
    }

    public void setPercent(int percent) {

        this.percent = percent;
    }



    @Override
    public String toString() {
        return discountName;
    }
}
