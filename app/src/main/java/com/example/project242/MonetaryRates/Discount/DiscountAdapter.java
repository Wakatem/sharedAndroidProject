package com.example.project242.MonetaryRates.Discount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.project242.Home.Home;
import com.example.project242.R;

import java.util.ArrayList;

public class DiscountAdapter extends ArrayAdapter<Discount> {
    //private Context context;
    //ArrayList<Discount> discountList = new ArrayList<>();
    DiscountsHandler list;

    public DiscountAdapter(@NonNull Context context, DiscountsHandler list) {
        super(context, 0, list);
        //this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.single_list_item,parent,false);
        }

        TextView name = listItem.findViewById(R.id.discountName);
        name.setText(list.get(position).getDiscountName());

        TextView rupee = listItem.findViewById(R.id.percentage);
        rupee.setText(String.valueOf(list.get(position).getPercent()));

        Switch aswitch = listItem.findViewById(R.id.aswitch);
        aswitch.setChecked(list.get(position).getaSwitch());

        ImageView close = listItem.findViewById(R.id.closedown);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home.discountsHandler.remove(position);
                DiscountsFragment.adapter.notifyDataSetChanged();
                DiscountsFragment.discountList.invalidateViews();
            }
        });

        return listItem;
    }
}
