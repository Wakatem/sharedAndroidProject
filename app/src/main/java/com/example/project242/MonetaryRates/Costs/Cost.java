package com.example.project242.MonetaryRates.Costs;

import com.example.project242.R;

public class Cost {

    private int amount;
    private int hours;
    private CostTypes type;
    private int ring;


    public Cost(int amount, CostTypes type, int hours) {
        this.amount = amount;
        this.hours = hours;
        this.type = type;

        //assign ring based on cost type
        if (type == CostTypes.GREATER)
            ring = R.drawable.greater_than_ring;
        else if (type == CostTypes.EQUAL)
            ring = R.drawable.equal_ring;
        else
            ring = R.drawable.less_than_ring;

    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getRing() {
        return ring;
    }

    public CostTypes getType() {
        return type;
    }


}
