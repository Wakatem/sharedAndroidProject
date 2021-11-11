package com.example.project242.MonetaryRates.Discount;
import android.widget.Switch;

public class Discount {
    private String discountName;
    private int percent;
    private boolean aSwitch;

    public Discount(String discountName, int percent, boolean aSwitch){
        this.discountName=discountName;
        this.percent=percent;
        this.aSwitch=aSwitch;
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

    public boolean getaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(boolean aSwitch) {
        this.aSwitch = aSwitch;
    }
}
