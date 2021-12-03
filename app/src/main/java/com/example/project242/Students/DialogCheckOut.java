package com.example.project242.Students;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.project242.general.DataContainer;
import com.example.project242.R;

public class DialogCheckOut extends AppCompatDialogFragment {
    private TextView studentNameTextView;
    private TextView studentIDTextView;

    private AdapterCurrentStudents adapter;
    private ListView listView;

    DialogCheckOut(AdapterCurrentStudents adapter, ListView listView) {
        this.adapter = adapter;
        this.listView = listView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_check_out, null);

        Bundle bundle = getArguments();
        Student student = bundle.getParcelable("Student");

        builder.setView(view)
                .setTitle("Check-out Confirmation")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*
                        Date date = new Date(System.currentTimeMillis());
                        student.setCheckOutTime(System.currentTimeMillis());

                        long checkInDuration = student.getCheckOutTime() - student.getCheckInTime();
                        int hours = (int) checkInDuration / 60;
                        int minutes = (int) checkInDuration % 60;

                        int checkOutCosts = HomeSection.costsHandler.getCost(hours, minutes);

                        int transactionID = 1;


                        */
                        DataContainer.currentStudentsArrayList.remove(student);
                        for (int j = 0; j < DataContainer.allStudentsArrayList.size(); ++j) {
                            if (DataContainer.allStudentsArrayList.get(j).getStudentID() == student.getStudentID()) {
                                DataContainer.allStudentsArrayList.get(j).setCheckedInFlag(false);
                                break;
                            }
                        }
                        adapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        Toast.makeText(getActivity(),"Confirmed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });

        studentNameTextView = view.findViewById(R.id.dialog_check_out_textView_student_name_1);
        studentIDTextView = view.findViewById(R.id.dialog_check_out_textView_student_id_1);

        studentNameTextView.setText("Name:" + student.getStudentName());
        studentIDTextView.setText("ID:" + String.valueOf(student.getStudentID()));


        return builder.create();
    }

    public void checkOutStudent() {

    }
}
