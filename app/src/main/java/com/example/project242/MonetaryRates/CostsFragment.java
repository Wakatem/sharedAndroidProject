package com.example.project242.MonetaryRates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project242.R;

import java.util.ArrayList;

public class CostsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = LayoutInflater.from(getContext()).inflate(R.layout.costs_fragment, container, false);

        ListView costsListView = frag.findViewById(R.id.listV);

        //create costs list
        ArrayList<Cost> costsList = new ArrayList<>();
        costsList.add(new Cost(120, 4, Cost.Type.EQUAL));
        costsList.add(new Cost(220, 5, Cost.Type.GREATER));
        costsList.add(new Cost(340, 6, Cost.Type.LESS));

        //set custom adapter
        CostsAdapter adapter = new CostsAdapter(getContext(), costsList);
        costsListView.setAdapter(adapter);

        return frag;
    }

}
