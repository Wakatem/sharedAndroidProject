package com.example.project242.MonetaryRates.Discount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project242.Home.HomeSection;
import com.example.project242.MonetaryRates.BottomSheet;
import com.example.project242.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DiscountsFragment extends Fragment {

    public static ListView discountList;
    public static DiscountAdapter adapter;
    ImageView close;

    FloatingActionButton b1;


    public DiscountsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = LayoutInflater.from(getContext()).inflate(R.layout.discounts_fragment,container,false);
        discountList = fragment.findViewById(R.id.discountList);

        adapter = new DiscountAdapter(getContext(), HomeSection.discountsHandler);
        discountList.setAdapter(adapter);
       // close = fragment.findViewById(R.id.discountName);
        close = discountList.findViewById(R.id.closedown);

        /*ArrayList<Discount> discountList = new ArrayList<>();

        discountList.add(new Discount("early bird",25, false));
        discountList.add(new Discount("early bird",25, false));
        discountList.add(new Discount("early bird",25, false));
        discountList.add(new Discount("early bird",25, false));
        discountList.add(new Discount("early bird",25, false));
        discountList.add(new Discount("early bird",25, true));
        DiscountAdapter adapter = new DiscountAdapter(getContext(), Home.discountsHandler);
        discountView.setAdapter(adapter); */

        fragment.findViewById(R.id.fab);

        b1= fragment.findViewById(R.id.fab);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet sheet = new BottomSheet(getContext(),R.layout.discount_sheet_item);
                sheet.initializeDiscountsViews();
                sheet.setDiscountListeners();
                sheet.show();


            }
        });



        return fragment;
    }

}
