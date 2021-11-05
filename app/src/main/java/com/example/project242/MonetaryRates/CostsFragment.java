package com.example.project242.MonetaryRates;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.example.project242.Home.Home;
import com.example.project242.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class CostsFragment extends Fragment {

    ListView costsListView;
    CostsAdapter adapter;

    boolean initialLoad = true;     // to avoid the overriding of the ring on bottomsheet load
    int chsoenRing;               // passed later to save final ring
    Cost.Type chosenCostType;           // passed later to save final cost type

    boolean isEditing = false;    // to track editing mode
    boolean dataChanged = false;  // on true, the button will change color and text

    ArrayList<Cost> costsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = LayoutInflater.from(getContext()).inflate(R.layout.costs_fragment, container, false);

        //link listview
        costsListView = frag.findViewById(R.id.listV);

        //get costs list
        costsList = Home.costsList;

        //set custom adapter
        adapter = new CostsAdapter(getContext(), costsList);
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

        //default values everytime the sheet appears
        initialLoad = true;
        isEditing = false;
        dataChanged = false;

        //load and link sheet components
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.cost_control);

        LinearLayout ringContainer = dialog.findViewById(R.id.ringContainer);
        EditText amountET = dialog.findViewById(R.id.chosenCost_amount);
        EditText hoursET = dialog.findViewById(R.id.chosenCost_hours);
        Spinner costTypesMenu = dialog.findViewById(R.id.chosenCost_desc);

        FButton editButton = dialog.findViewById(R.id.edit_button);
        FButton deleteButton = dialog.findViewById(R.id.delete_button);


        //update components with the relevant info
        ringContainer.setBackgroundResource(chosenCost.getRing());
        amountET.setText(String.valueOf(chosenCost.getAmount()));
        hoursET.setText(String.valueOf(chosenCost.getDuration()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.costTypes, android.R.layout.simple_spinner_dropdown_item);
        costTypesMenu.setAdapter(adapter);
        costTypesMenu.setEnabled(false);


        //set listeners
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
                        chsoenRing = R.drawable.greater_than_ring;
                        ringContainer.setBackgroundResource(chsoenRing);
                    }
                    else if (position == 1) {
                        chosenCostType = Cost.Type.EQUAL;
                        chsoenRing = R.drawable.equal_ring;
                        ringContainer.setBackgroundResource(R.drawable.equal_ring);
                    }
                    else {
                        chosenCostType = Cost.Type.LESS;
                        chsoenRing = R.drawable.less_than_ring;
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
                            Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(getContext(), "Matching cost duration exists", Toast.LENGTH_SHORT).show();
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
                            saveCostChanges(chosenCost, chsoenRing, amountET, hoursET, chosenCostType);
                            //close bottom sheet
                            dialog.dismiss();
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
                dialog.dismiss();
                //refresh list
                adapter.notifyDataSetChanged();   // notify adapter of data changes
                costsListView.invalidateViews();  // redraw the list
            }
        });



        dialog.show();

    }


    private void saveCostChanges(Cost chosenCost, int chosenRing, EditText amountET, EditText hoursET, Cost.Type costType){

        int position = costsList.indexOf(chosenCost);

        costsList.get(position).setAmount(Integer.valueOf(amountET.getText().toString()));
        costsList.get(position).setDuration(Integer.valueOf(hoursET.getText().toString()));
        costsList.get(position).setRing(chosenRing);
        costsList.get(position).setType(costType);

    }

    private void deleteItem() {
    }

}
