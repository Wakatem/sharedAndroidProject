package com.example.project242.Students;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.project242.R;

import java.util.ArrayList;

public class AdapterCurrentStudents extends ArrayAdapter {
    private Context context;
    private ArrayList<Student> students;
    private ListView listView;

    private TextView studentNameTextView;
    private TextView studentIDTextView;
    private Button statusButton;

    private DialogCheckOut dialogCheckOut;

    public AdapterCurrentStudents(Context context, ArrayList<Student> arrayList, ListView listView) {
        super(context, 0, arrayList);
        this.context = context;
        this.students = arrayList;
        this.listView = listView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(this.context).inflate(R.layout.listview_current_students, parent, false);
        }

        Student student = students.get(position);

        studentNameTextView = (TextView) listItem.findViewById(R.id.listView_current_students_textView_student_name_1);
        studentNameTextView.setText(student.getStudentName());

        studentIDTextView = (TextView) listItem.findViewById(R.id.listView_current_students_textView_student_id_1);
        studentIDTextView.setText(String.valueOf(student.getStudentID()));

        statusButton = (Button) listItem.findViewById(R.id.listView_current_students_button_status);
        statusButton.setText("Check-Out");

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentsSection students = (StudentsSection) (context);
                FragmentManager fragmentManager = students.getSupportFragmentManager();

                dialogCheckOut = new DialogCheckOut(AdapterCurrentStudents.this, listView);

                Bundle bundle = new Bundle();
                bundle.putParcelable("Student", student);
                dialogCheckOut.setArguments(bundle);
                dialogCheckOut.show(fragmentManager, "Check-Out Dialog");
            }
        });

        return listItem;
    }
}
