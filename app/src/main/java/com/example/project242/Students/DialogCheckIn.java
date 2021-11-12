package com.example.project242.Students;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.project242.Home.Home;
import com.example.project242.R;

public class DialogCheckIn extends AppCompatDialogFragment {
    private TextView studentNameTextView;
    private TextView studentIDTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_check_in, null);

        Bundle bundle = getArguments();
        Student student = bundle.getParcelable("Student");

        builder.setView(view)
                .setTitle("Check-in Confirmation")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Home.currentStudentsArrayList.add(student);
                        // callBack.onResult(true);
                        Toast.makeText(getActivity(),"Confirmed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });

        studentNameTextView = view.findViewById(R.id.dialog_check_in_textView_student_name_1);
        studentIDTextView = view.findViewById(R.id.dialog_check_in_textView_student_id_1);

        studentNameTextView.setText("Name:\t" + student.getStudentName());
        studentIDTextView.setText("ID:\t\t" + String.valueOf(student.getStudentID()));

        return builder.create();
    }
}
