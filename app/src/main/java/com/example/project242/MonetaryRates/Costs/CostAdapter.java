package com.example.project242.MonetaryRates.Costs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project242.R;

public class CostAdapter extends ArrayAdapter<Cost> {

    CostsHandler list;

    public CostAdapter(@NonNull Context context, CostsHandler list) {
        super(context, 0, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null)
            item = LayoutInflater.from(getContext()).inflate(R.layout.costs_list_item, parent, false);

        //link views
        TextView amountTV = item.findViewById(R.id.amountTextView);
        TextView descTV = item.findViewById(R.id.descriptionTextView);
        LinearLayout ring = item.findViewById(R.id.ringContainer);

        //setup views' values
        amountTV.setText(String.valueOf(list.get(position).getAmount()) + " AED");
        ring.setBackgroundResource(list.get(position).getRing());

        CostTypes type = list.get(position).getType();
        String duration = String.valueOf(list.get(position).getHours());

        //assign description appropriately
        if (type == CostTypes.GREATER) {
            descTV.setText("Per additional hour");
        } else if (type == CostTypes.EQUAL) {
            if (list.get(position).getHours() == 1)
                descTV.setText(duration + " Hour");
            else
                descTV.setText(duration + " Hours");
        } else {
            if (list.get(position).getHours() == 1)
                descTV.setText("Less than " + duration + " hour");
            else
                descTV.setText("Less than " + duration + " hours");
        }

        return item;
    }

}
