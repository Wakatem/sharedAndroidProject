package com.example.project242.MonetaryRates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project242.Home.Home;
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
        adapter = new CostAdapter(getContext(), Home.costsList);
        costsListView.setAdapter(adapter);

        //set onclick listener for a single item
        costsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BottomSheet sheet = new BottomSheet(false, getContext(), R.layout.cost_sheet_item, Home.costsList.get(position), Home.costsList, costsListView, adapter);
                sheet.initializeViews();
                sheet.setListeners();
                sheet.show();
            }
        });


        return frag;
    }


    @Override
    public void onDestroyView() {
        Toast.makeText(getContext(), "view destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getContext(), "destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
