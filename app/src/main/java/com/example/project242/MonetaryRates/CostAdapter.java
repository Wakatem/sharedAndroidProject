package com.example.project242.MonetaryRates;

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

import java.util.ArrayList;

public class CostAdapter extends ArrayAdapter<Cost> {

    ArrayList<Cost> list;

    public CostAdapter(@NonNull Context context, ArrayList<Cost> list) {
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

        Cost.Type type = list.get(position).getType();
        String duration = String.valueOf(list.get(position).getDuration());

        //assign description appropriately
        if (type == Cost.Type.GREATER) {
            descTV.setText("More than "+duration+" hours");
        } else if (type == Cost.Type.LESS) {
            descTV.setText("Less than "+duration+" hours");
        } else {
            descTV.setText(duration+" Hours");
        }

        return item;
    }

}
