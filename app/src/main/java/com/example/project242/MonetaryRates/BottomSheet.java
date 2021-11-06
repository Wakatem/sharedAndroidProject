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


    //create expected specific objects
    private ArrayList<Cost> costsList;
    private ListView costsListView;
    private CostAdapter adapter;


    //sheet components (based on passed layout)
    private LinearLayout ringContainer;
    private EditText amountET;
    private EditText hoursET;
    private Spinner costTypesMenu;
    private FButton editButton;
    private FButton deleteButton;


    //default & shared variables
    private boolean initialCostLoad = true;   // to avoid the overriding of the ring on bottom sheet load
    private Cost.Type chosenCostType;         // passed later to save final cost type
    private boolean isEditing = false;        // to track editing mode in edit button
    private boolean isAddingCost;             // to determine bottom sheet role


    public BottomSheet(boolean isAddingItem, Context parentContext, int sheetLayout, Cost chosenCost, Object... objects) {
        this.isAddingCost = isAddingItem;

        //initialize sheet dialog
        sheetDialog = new BottomSheetDialog(parentContext);
        context = parentContext;
        this.chosenCost = chosenCost;

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

        editButton = sheetDialog.findViewById(R.id.edit_button);
        deleteButton = sheetDialog.findViewById(R.id.delete_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.costTypes, android.R.layout.simple_spinner_dropdown_item);
        costTypesMenu.setAdapter(adapter);

        if (isAddingCost) {
            //show "Add Cost" text
            TextView newCost_tv = sheetDialog.findViewById(R.id.newCostTV);
            newCost_tv.setVisibility(View.VISIBLE);

            //empty fields
            amountET.setText("");
            hoursET.setText("");

            //switch to edit mode
            isEditing = true;
            amountET.setFocusableInTouchMode(true);
            hoursET.setFocusableInTouchMode(true);
            costTypesMenu.setEnabled(true);

            editButton.setText("Save");
            editButton.setButtonColor(Color.parseColor("#4CAF50"));
            deleteButton.setText("Cancel");
            deleteButton.setButtonColor(Color.parseColor("#9E9E9E"));

            costTypesMenu.setEnabled(true);


        } else {
            //-- an existing cost was chosen --

            editButton.setText("Edit");
            editButton.setButtonColor(Color.parseColor("#FFC107"));
            deleteButton.setText("Delete");
            deleteButton.setButtonColor(Color.parseColor("#EF5350"));

            amountET.setFocusableInTouchMode(false);
            hoursET.setFocusableInTouchMode(false);
            costTypesMenu.setEnabled(false); //disable initial menu availability

            //update components with values respectively
            ringContainer.setBackgroundResource(chosenCost.getRing());
            amountET.setText(String.valueOf(chosenCost.getAmount()));
            hoursET.setText(String.valueOf(chosenCost.getHours()));
        }


    }

    public void setListeners() {

        costTypesMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (initialCostLoad) {
                    //set the correct drop menu option & values based on current cost type
                    if (chosenCost.getType() == Cost.Type.GREATER) {
                        chosenCostType = Cost.Type.GREATER;
                        costTypesMenu.setSelection(0);
                        ringContainer.setBackgroundResource(R.drawable.greater_than_ring);
                    } else if (chosenCost.getType() == Cost.Type.EQUAL) {
                        chosenCostType = Cost.Type.EQUAL;
                        costTypesMenu.setSelection(1);
                        ringContainer.setBackgroundResource(R.drawable.equal_ring);
                    } else {
                        chosenCostType = Cost.Type.LESS;
                        costTypesMenu.setSelection(2);
                        ringContainer.setBackgroundResource(R.drawable.less_than_ring);
                    }

                    //initial load is done
                    initialCostLoad = false;

                } else {
                    if (position == 0) {
                        chosenCostType = Cost.Type.GREATER;
                        ringContainer.setBackgroundResource(R.drawable.greater_than_ring);
                    } else if (position == 1) {
                        chosenCostType = Cost.Type.EQUAL;
                        ringContainer.setBackgroundResource(R.drawable.equal_ring);
                    } else {
                        chosenCostType = Cost.Type.LESS;
                        ringContainer.setBackgroundResource(R.drawable.less_than_ring);
                    }


                }//else


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


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

                    //if all fields have a value
                    if (!amountET.getText().toString().equals("") || !amountET.getText().toString().equals("")) {

                        int newDuration = Integer.valueOf(hoursET.getText().toString());

                        for (int i = costsList.size()-1; i > -1; i--){
                            if (uniqueCostDetails(i, newDuration) == false || noRangeConflict(i, newDuration) == false){
                                readyToSave = false;
                                break;
                            }

                        }//for

                    }


                    //Saving process
                    if (readyToSave) {

                        //save changes
                        saveCostChanges(chosenCost, amountET, hoursET, chosenCostType);

                        //close bottom sheet
                        sheetDialog.dismiss();

                        //refresh list
                        adapter.notifyDataSetChanged();   // notify adapter of data changes
                        costsListView.invalidateViews();  // redraw the list

                        //switch back to non-editing
                        isEditing = false;
                        costTypesMenu.setEnabled(false);

                    }


                } else {
                    // -- when in non-editing mode --

                    //switch to edit mode
                    isEditing = true;
                    amountET.setFocusableInTouchMode(true);
                    hoursET.setFocusableInTouchMode(true);
                    costTypesMenu.setEnabled(true);
                    editButton.setText("Save");
                    editButton.setButtonColor(Color.parseColor("#4CAF50"));
                }

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAddingCost) {
                    sheetDialog.dismiss();
                } else {
                    costsList.remove(chosenCost);
                    //close bottom sheet
                    sheetDialog.dismiss();
                    //refresh list
                    adapter.notifyDataSetChanged();   // notify adapter of data changes
                    costsListView.invalidateViews();  // redraw the list
                }

            }
        });

    }

    private boolean uniqueCostDetails(int i, int newDuration) {


            //skip current chosen cost item to avoid unnecessary comparison
            if (costsList.get(i) != chosenCost) {

                //if matching details found, don't save
                if (newDuration == costsList.get(i).getHours() && chosenCostType == costsList.get(i).getType()) {
                    Toast.makeText(context, "Matching cost duration exists", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }

        return true;
    }

    private boolean noRangeConflict(int i, int newDuration){

        try {

            Cost currentCost = costsList.get(i);

            if (currentCost.getType() == Cost.Type.LESS){
                if (chosenCostType == Cost.Type.GREATER){
                    if (newDuration < currentCost.getHours()){
                        if (i+1 == costsList.size()){
                            Toast.makeText(context, "conflicting with LESS " + String.valueOf(i), Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                }
            }else if (currentCost.getType() == Cost.Type.GREATER){
                if (chosenCostType == Cost.Type.LESS){
                    if (newDuration > currentCost.getHours()){
                        if (i-1 == -1){
                            Toast.makeText(context, "conflicting with GREATER " + String.valueOf(i), Toast.LENGTH_SHORT).show();
                            return false;
                        }else {
                            Toast.makeText(context, "conflicting with GREATER " + String.valueOf(i), Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                }
            }



        }catch (IndexOutOfBoundsException e){

        }

        return true;
    }

    private void saveCostChanges(Cost chosenCost, EditText amountET, EditText hoursET, Cost.Type costType) {

        if (isAddingCost) {

            chosenCost.setAmount(Integer.valueOf(amountET.getText().toString()));
            chosenCost.setHours(Integer.valueOf(hoursET.getText().toString()));
            chosenCost.setType(costType);

            Home.costsHandler.add(chosenCost);

        } else {
            // -- Editing cost --

            int position = costsList.indexOf(chosenCost);

            costsList.get(position).setAmount(Integer.valueOf(amountET.getText().toString()));
            costsList.get(position).setHours(Integer.valueOf(hoursET.getText().toString()));
            costsList.get(position).setType(costType);

        }


    }

    public void show() {
        sheetDialog.show();
    }

    public void close() {
        sheetDialog.dismiss();
    }

}
