package com.example.project242;

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
import android.widget.Toast;

import com.example.project242.MonetaryRates.Cost;
import com.example.project242.MonetaryRates.CostAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class BottomSheet {

    BottomSheetDialog sheetDialog;
    Context context;
    Cost chosenCost;

    //create expected specific objects
    ArrayList<Cost> costsList;
    ListView costsListView;
    CostAdapter adapter;


    //sheet components (based on passed layout)
    LinearLayout ringContainer;
    EditText amountET;
    EditText hoursET;
    Spinner costTypesMenu;
    FButton editButton;
    FButton deleteButton;


    //default & shared variables
    int sheetLayout;
    boolean initialLoad = true;       // to avoid the overriding of the ring on bottomsheet load
    int chosenRing;                   // passed later to save final ring
    Cost.Type chosenCostType;         // passed later to save final cost type
    boolean isEditing = false;        // to track editing mode
    boolean dataChanged = false;      // if true, save process step included


    public BottomSheet(Context parentContext, int sheetLayout, Cost chosenCost, Object... objects){
        //initialize sheet dialog
        sheetDialog = new BottomSheetDialog(parentContext);
        context = parentContext;
        this.chosenCost = chosenCost;

        //set sheet layout
        this.sheetLayout = sheetLayout;
        sheetDialog.setContentView(sheetLayout);

        //cast objects according to type
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof ArrayList ){
                costsList = (ArrayList<Cost>) objects[i];
                continue;
            }

            if (objects[i] instanceof ListView ){
                costsListView = (ListView) objects[i];
                continue;
            }

            if (objects[i] instanceof CostAdapter){
                adapter = (CostAdapter) objects[i];
            }


        }//for


    }

    public void initializeViews(){

        //load and link sheet components
        ringContainer = sheetDialog.findViewById(R.id.ringContainer);
        amountET = sheetDialog.findViewById(R.id.chosenCost_amount);
        hoursET = sheetDialog.findViewById(R.id.chosenCost_hours);
        costTypesMenu = sheetDialog.findViewById(R.id.chosenCost_desc);

        editButton = sheetDialog.findViewById(R.id.edit_button);
        deleteButton = sheetDialog.findViewById(R.id.delete_button);

        //update components with the relevant info
        ringContainer.setBackgroundResource(chosenCost.getRing());
        amountET.setText(String.valueOf(chosenCost.getAmount()));
        hoursET.setText(String.valueOf(chosenCost.getDuration()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.costTypes, android.R.layout.simple_spinner_dropdown_item);
        costTypesMenu.setAdapter(adapter);
        costTypesMenu.setEnabled(false);
    }

    public void setListeners(){

        costTypesMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (initialLoad) {
                    //set the correct drop menu option based on current ring of the item
                    if (chosenCost.getType() == Cost.Type.GREATER) {
                        costTypesMenu.setSelection(0);
                        ringContainer.setBackgroundResource(R.drawable.greater_than_ring);
                    } else if (chosenCost.getType() == Cost.Type.EQUAL) {
                        costTypesMenu.setSelection(1);
                        ringContainer.setBackgroundResource(R.drawable.equal_ring);
                    } else {
                        costTypesMenu.setSelection(2);
                        ringContainer.setBackgroundResource(R.drawable.less_than_ring);
                    }

                    //initial load is done
                    initialLoad = false;

                } else {
                    dataChanged = true;
                    if (position == 0) {
                        chosenCostType = Cost.Type.GREATER;
                        chosenRing = R.drawable.greater_than_ring;
                        ringContainer.setBackgroundResource(chosenRing);
                    }
                    else if (position == 1) {
                        chosenCostType = Cost.Type.EQUAL;
                        chosenRing = R.drawable.equal_ring;
                        ringContainer.setBackgroundResource(R.drawable.equal_ring);
                    }
                    else {
                        chosenCostType = Cost.Type.LESS;
                        chosenRing = R.drawable.less_than_ring;
                        ringContainer.setBackgroundResource(R.drawable.less_than_ring);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        amountET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dataChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        hoursET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dataChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing) {
                    if (dataChanged) {
                        boolean readyToSave = true;

                        //check if any field is empty before saving
                        if (amountET.getText().toString().equals("") || amountET.getText().toString().equals("")) {
                            readyToSave = false;
                            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        }


                        {
                            //a condition to avoid checking details if current fields are empty
                            if (readyToSave){
                                int newDuration = Integer.valueOf(hoursET.getText().toString());

                                for (int i = 0; i < costsList.size(); i++) {

                                    //skip current chosen cost item to avoid unnecessary comparison
                                    if (costsList.get(i) != chosenCost){

                                        //if matching details found, don't save
                                        if (newDuration == costsList.get(i).getDuration() && chosenCostType == costsList.get(i).getType()) {
                                            readyToSave = false;
                                            Toast.makeText(context, "Matching cost duration exists", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }

                                }//for
                            }

                        }//uniqueDetailsChecker



                        //Saving process
                        if (readyToSave) {
                            //switch back to non-editing
                            isEditing = false;
                            dataChanged = false;
                            costTypesMenu.setEnabled(false);

                            //save changes
                            saveCostChanges(chosenCost, chosenRing, amountET, hoursET, chosenCostType);
                            //close bottom sheet
                            sheetDialog.dismiss();
                            //refresh list
                            adapter.notifyDataSetChanged();   // notify adapter of data changes
                            costsListView.invalidateViews();  // redraw the list


                        }

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
                costsList.remove(chosenCost);
                //close bottom sheet
                sheetDialog.dismiss();
                //refresh list
                adapter.notifyDataSetChanged();   // notify adapter of data changes
                costsListView.invalidateViews();  // redraw the list
            }
        });

    }

    private void saveCostChanges(Cost chosenCost, int chosenRing, EditText amountET, EditText hoursET, Cost.Type costType){

        int position = costsList.indexOf(chosenCost);

        costsList.get(position).setAmount(Integer.valueOf(amountET.getText().toString()));
        costsList.get(position).setDuration(Integer.valueOf(hoursET.getText().toString()));
        costsList.get(position).setRing(chosenRing);
        costsList.get(position).setType(costType);

    }

    public void show(){
        sheetDialog.show();
    }

    public void close(){
        sheetDialog.dismiss();
    }
}
