package com.example.project242.MonetaryRates.Costs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project242.Home.Home;
import com.example.project242.MonetaryRates.BottomSheet;
import com.example.project242.R;

public class CostsFragment extends Fragment {

    static ListView costsListView;
    static CostAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = LayoutInflater.from(getContext()).inflate(R.layout.costs_fragment, container, false);

        //link listview
        costsListView = frag.findViewById(R.id.listV);

        //set custom adapter
        adapter = new CostAdapter(getContext(), Home.costsHandler);
        costsListView.setAdapter(adapter);

        //set onclick listener for a single item
        costsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //display selected cost in a sheet
                BottomSheet sheet = new BottomSheet(getContext(), R.layout.cost_sheet_item, Home.costsHandler.get(position), Home.costsHandler, costsListView, adapter);
                sheet.initializeCostsViews();
                sheet.setListeners();
                sheet.show();
            }
        });


        return frag;
    }


}
