package com.example.project242.MonetaryRates;

import com.example.project242.R;

public class Cost {

    public enum Type {
        GREATER,
        EQUAL,
        LESS
    }

    private int amount;
    private int duration;
    private Type type;
    private int ring;


    public Cost(int amount, int duration, Type type) {
        this.amount = amount;
        this.duration = duration;
        this.type = type;

        //assign ring based on cost type
        if (type == Type.GREATER)
            ring = R.drawable.greater_than_ring;
        else if (type == Type.EQUAL)
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRing() {
        return ring;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;

        //assign ring based on cost type
        if (type == Type.GREATER)
            ring = R.drawable.greater_than_ring;
        else if (type == Type.EQUAL)
            ring = R.drawable.equal_ring;
        else
            ring = R.drawable.less_than_ring;
    }
}
