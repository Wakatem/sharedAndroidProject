package com.example.project242.MonetaryRates;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project242.BottomSheet;
import com.example.project242.Home.Home;
import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class CostsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = LayoutInflater.from(getContext()).inflate(R.layout.costs_fragment, container, false);

        //link listview
        ListView costsListView = frag.findViewById(R.id.listV);

        //get costs list
        ArrayList<Cost> costsList = Home.costsList;

        //set custom adapter
        CostAdapter adapter = new CostAdapter(getContext(), costsList);
        costsListView.setAdapter(adapter);

        //set onclick listener for a single item
        costsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BottomSheet sheet = new BottomSheet(getContext(), R.layout.cost_item_sheet, costsList.get(position), costsList, costsListView, adapter);
                sheet.initializeViews();
                sheet.setListeners();
                sheet.show();
            }
        });


        return frag;
    }



}
