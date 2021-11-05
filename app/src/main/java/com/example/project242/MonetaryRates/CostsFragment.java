package com.example.project242.MonetaryRates;

import android.os.Bundle;
import android.text.Editable;
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

import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class CostsFragment extends Fragment {

    boolean firstLoad = true; // to avoid the overriding of the ring on bottomsheat load

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = LayoutInflater.from(getContext()).inflate(R.layout.costs_fragment, container, false);

        //link listview
        ListView costsListView = frag.findViewById(R.id.listV);

        //create costs list
        ArrayList<Cost> costsList = new ArrayList<>();
        costsList.add(new Cost(120, 4, Cost.Type.EQUAL));
        costsList.add(new Cost(220, 5, Cost.Type.GREATER));
        costsList.add(new Cost(340, 6, Cost.Type.LESS));

        //set custom adapter
        CostsAdapter adapter = new CostsAdapter(getContext(), costsList);
        costsListView.setAdapter(adapter);

        //set onclick listener for a single item
        costsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                popupBottomSheet(costsList.get(position));
            }
        });
        return frag;
    }


    private void popupBottomSheet(Cost chosenCost) {

        //load and link sheet components
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.cost_control);

        LinearLayout ring = dialog.findViewById(R.id.ringContainer);
        EditText amountET = dialog.findViewById(R.id.chosenCost_amount);
        EditText hoursET = dialog.findViewById(R.id.chosenCost_hours);

        Spinner spinner = dialog.findViewById(R.id.chosenCost_desc);

        FButton editButton = dialog.findViewById(R.id.edit_button);
        FButton deleteButton = dialog.findViewById(R.id.delete_button);


        //update components with the relevant info

        ring.setBackgroundResource(chosenCost.getRing());

        amountET.setText(String.valueOf(chosenCost.getAmount()));
        hoursET.setText(String.valueOf(chosenCost.getDuration()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.costTypes, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //set listeners
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (!firstLoad){
                    if (position == 0)
                        ring.setBackgroundResource(R.drawable.greater_than_ring);
                    else if (position == 1)
                        ring.setBackgroundResource(R.drawable.equal_ring);
                    else
                        ring.setBackgroundResource(R.drawable.less_than_ring);
                }else {
                    //first load is done, allow accurate changes based on selection
                    firstLoad = false;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ring.setBackgroundResource(chosenCost.getRing());
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.show();

    }



    private void deleteItem() {
    }

}
