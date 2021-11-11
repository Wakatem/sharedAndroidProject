package com.example.project242.MonetaryRates;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.Home.Home;
import com.example.project242.MonetaryRates.Costs.Cost;
import com.example.project242.MonetaryRates.Costs.CostAdapter;
import com.example.project242.MonetaryRates.Costs.CostTypes;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.Discount;
import com.example.project242.MonetaryRates.Discount.DiscountAdapter;
import com.example.project242.MonetaryRates.Discount.DiscountsFragment;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.example.project242.MonetaryRates.Discount.Discount;

import info.hoang8f.widget.FButton;

public class BottomSheet {

    private BottomSheetDialog sheetDialog;
    private Context context;
    private Cost chosenCost;

    //expected specific objects
    private CostsHandler costsList;
    private CostAdapter adapter;
    private ListView costsListView;
    private DiscountsHandler discountsList;

    //costs variables
    private LinearLayout ringContainer;
    private EditText amountET;
    private EditText hoursET;
    private Spinner costTypesMenu;
    private FButton editButton;
    private FButton cancelButton;

    //discount variables
    private TextView heading;
    private TextView discountType;
    private EditText discountTypeR;
    private EditText percentageR;
    private Switch aSwitch;
    private RelativeLayout buttons;
    private Button add;
    private Button cancel;


    // to track cost editing mode in edit button
    private boolean isEditing = false;


    public BottomSheet(Context newContext, int sheetLayout){
        discountsList = Home.discountsHandler;

        sheetDialog = new BottomSheetDialog(newContext);
        context = newContext;

        sheetDialog.setContentView(sheetLayout);
    }


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


    public void initializeCostsViews() {

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

    public void initializeDiscountsViews(){
        heading = sheetDialog.findViewById(R.id.header);
        discountType = sheetDialog.findViewById(R.id.type1);
        discountTypeR = sheetDialog.findViewById(R.id.type2);
        percentageR = sheetDialog.findViewById(R.id.percentage2);
        aSwitch = sheetDialog.findViewById(R.id.switch1);
        buttons = sheetDialog.findViewById(R.id.buttons);
        add = sheetDialog.findViewById(R.id.add);
        cancel = sheetDialog.findViewById(R.id.cancel);




    }

    public void addDiscount(EditText discountTypeR, EditText percentageR, Switch newswitch){
        String name = discountTypeR.getText().toString();
        int percentage = Integer.valueOf(percentageR.getText().toString());
        boolean switchR = newswitch.isChecked();
        Discount newDiscount = new Discount(name, percentage,switchR);
        discountsList.add(newDiscount);
        DiscountsFragment.adapter.notifyDataSetChanged();
        DiscountsFragment.discountList.invalidateViews();


    }



    public void setDiscountListener(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(discountTypeR.getText().toString()=="" || percentageR.getText().toString().equals("")){
                    Toast.makeText(context, "please enter values to continue",Toast.LENGTH_SHORT).show();
                }
                else {
                    addDiscount(discountTypeR, percentageR, aSwitch);
                    sheetDialog.dismiss();
                }



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog.dismiss();
            }
        });



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

                    if (chosenCost.getType() == CostTypes.GREATER || chosenCost.getType() == CostTypes.LESS){
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