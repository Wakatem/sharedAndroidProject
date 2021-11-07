package com.example.project242.MonetaryRates;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.Home.Home;
import com.example.project242.MonetaryRates.Cost;
import com.example.project242.MonetaryRates.CostAdapter;
import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class BottomSheet {

    private BottomSheetDialog sheetDialog;
    private Context context;
    private Cost chosenCost;
    //private Discount discount

    //create expected specific objects
    private CostsHandler costsList;
    private ListView costsListView;
    private CostAdapter adapter;


    //sheet components (based on passed layout)
    private LinearLayout ringContainer;
    private EditText amountET;
    private EditText hoursET;
    private Spinner costTypesMenu;
    private FButton editButton;
    private FButton cancelButton;


    // to track editing mode in edit button
    private boolean isEditing = false;


    public BottomSheet(Context parentContext, int sheetLayout, Cost givenCost, Object... objects) {

        chosenCost = givenCost;

        //initialize sheet dialog
        sheetDialog = new BottomSheetDialog(parentContext);
        context = parentContext;

        //set sheet layout
        sheetDialog.setContentView(sheetLayout);

        //cast objects according to type
        for (Object object : objects) {
            if (object instanceof CostsHandler) {
                costsList = (CostsHandler) object;
            }

            if (object instanceof ListView) {
                costsListView = (ListView) object;
            }

            if (object instanceof CostAdapter) {
                adapter = (CostAdapter) object;
            }


        }//for


    }


    public void initializeViews() {

        //load and link sheet components
        ringContainer = sheetDialog.findViewById(R.id.ringContainer);
        amountET = sheetDialog.findViewById(R.id.chosenCost_amount);
        hoursET = sheetDialog.findViewById(R.id.chosenCost_hours);
        costTypesMenu = sheetDialog.findViewById(R.id.chosenCost_desc);
        cancelButton = sheetDialog.findViewById(R.id.cancel_button);
        editButton = sheetDialog.findViewById(R.id.edit_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.costTypes, android.R.layout.simple_spinner_dropdown_item);
        costTypesMenu.setAdapter(adapter);

        //disable all controls initially
        amountET.setFocusableInTouchMode(false);
        hoursET.setFocusableInTouchMode(false);
        costTypesMenu.setEnabled(false);

        //set buttons parameters
        editButton.setText("Edit");
        editButton.setButtonColor(Color.parseColor("#FFC107"));
        cancelButton.setText("Cancel");
        cancelButton.setButtonColor(Color.parseColor("#9E9E9E"));

        //update components with values respectively
        ringContainer.setBackgroundResource(chosenCost.getRing());
        amountET.setText(String.valueOf(chosenCost.getAmount()));
        hoursET.setText(String.valueOf(chosenCost.getHours()));



    }


    public void setListeners() {

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing) {
                    boolean readyToSave = true;

                    //check if any field is empty before saving
                    if (amountET.getText().toString().equals("") || amountET.getText().toString().equals("")) {
                        readyToSave = false;
                        Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    }


                    //Saving process
                    if (readyToSave) {

                        //save changes
                        saveCostChanges(chosenCost, amountET, hoursET);

                        //close bottom sheet
                        sheetDialog.dismiss();

                        //refresh list
                        adapter.notifyDataSetChanged();   // notify adapter of data changes
                        costsListView.invalidateViews();  // redraw the list

                        //switch back to non-editing
                        isEditing = false;

                    }


                } else {
                    // -- when in non-editing mode --

                    //switch to edit mode
                    isEditing = true;
                    editButton.setText("Save");
                    editButton.setButtonColor(Color.parseColor("#4CAF50"));

                    if (chosenCost.getType() == Cost.Type.GREATER || chosenCost.getType() == Cost.Type.LESS){
                        amountET.setFocusableInTouchMode(true);
                        hoursET.setFocusableInTouchMode(false);
                    } else {
                        amountET.setFocusableInTouchMode(true);
                        hoursET.setFocusableInTouchMode(true);
                    }

                }

            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //close sheet
                    sheetDialog.dismiss();

            }
        });

    }


    private void saveCostChanges(Cost chosenCost, EditText amountET, EditText hoursET) {

        int amount = Integer.valueOf(amountET.getText().toString());
        int hours = Integer.valueOf(hoursET.getText().toString());

        costsList.editCost(chosenCost, amount, hours);

    }

    public void show() {
        sheetDialog.show();
    }

    public void close() {
        sheetDialog.dismiss();
    }

}