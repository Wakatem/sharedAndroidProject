package com.example.project242.Students;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;

import java.util.ArrayList;

public class AdapterAllStudents extends ArrayAdapter {
    private Context context;
    private ArrayList<Student> students;
    private ListView listView;

    private TextView studentNameTextView;
    private TextView studentIDTextView;
    private Button studentStatus;
    private Button detailsButton;

    private DialogCheckIn dialogCheckIn;

    public AdapterAllStudents(Context context, ArrayList<Student> arrayList, ListView listView) {
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
            listItem = LayoutInflater.from(this.context).inflate(R.layout.listview_all_students, parent, false);
        }

        Student student = students.get(position);

        studentNameTextView = (TextView) listItem.findViewById(R.id.listView_all_students_textView_student_name_1);
        studentNameTextView.setText(student.getStudentName());

        studentIDTextView = (TextView) listItem.findViewById(R.id.listView_all_students_textView_student_id_1);
        studentIDTextView.setText(String.valueOf(student.getStudentID()));

        studentStatus = (Button) listItem.findViewById(R.id.listView_all_students_button_status);
        if (!student.getCheckedInFlag()) {
            studentStatus.setText("Check-In");
        } else {
            studentStatus.setText("Checked-In");
        }

        detailsButton = (Button) listItem.findViewById(R.id.listView_all_students_button_details);
        detailsButton.setText("Details");

        studentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student.getCheckedInFlag()) {
                    Toast.makeText(context, "The student is already checked in!", Toast.LENGTH_SHORT).show();
                    return;
                }

                StudentsSection students = (StudentsSection) (context);
                FragmentManager fragmentManager = students.getSupportFragmentManager();

                dialogCheckIn = new DialogCheckIn(AdapterAllStudents.this, listView);

                Bundle bundle = new Bundle();
                bundle.putParcelable("Student", student);
                dialogCheckIn.setArguments(bundle);
                dialogCheckIn.show(fragmentManager, "Check-In Dialog");
            }
        });

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentDetailsActivity.class);
                intent.putExtra("Student", student);
                intent.putExtra("Guardian", student.getGuardian());
                context.startActivity(intent);
            }
        });

        return listItem;
    }
}
