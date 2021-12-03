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

public class DialogCheckIn extends AppCompatDialogFragment {
    private TextView studentNameTextView;
    private TextView studentIDTextView;

    private AdapterAllStudents adapter;
    private ListView listView;

    DialogCheckIn(AdapterAllStudents adapter, ListView listView) {
        this.adapter = adapter;
        this.listView = listView;
    }

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
                        student.setCheckInTime(System.currentTimeMillis());
                        DataContainer.currentStudentsArrayList.add(student);
                        student.setCheckedInFlag(true);
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

        studentNameTextView = view.findViewById(R.id.dialog_check_in_textView_student_name_1);
        studentIDTextView = view.findViewById(R.id.dialog_check_in_textView_student_id_1);

        studentNameTextView.setText("Name:" + student.getStudentName());
        studentIDTextView.setText("ID:" + String.valueOf(student.getStudentID()));


        return builder.create();
    }
}
