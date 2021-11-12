package com.example.project242.Students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project242.R;
import com.example.project242.SectionsMenu;

public class StudentDetailsActivity extends AppCompatActivity {
    private TextView studentName;
    private TextView studentID;
    private TextView studentDOB;
    private TextView studentGender;
    private TextView guardian;
    private TextView guardianName;
    private TextView guardianRelationship;
    private TextView guardianPhoneNumber;
    private TextView guardianEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.student_details_drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setupToolbar("Student Details", R.drawable.menu_icon);
        menu.setOptionSelectedListener();

        studentName = findViewById(R.id.activity_student_details_textView_student_name_1);
        studentID = findViewById(R.id.activity_student_details_textView_student_id_1);
        studentDOB = findViewById(R.id.activity_student_details_textView_student_DOB_1);
        studentGender = findViewById(R.id.activity_student_details_textView_student_gender_1);
        guardian = findViewById(R.id.activity_student_details_textView_student_guardian_1);
        guardianName = findViewById(R.id.activity_student_details_textView_guardian_name_1);
        guardianRelationship = findViewById(R.id.activity_student_details_textView_guardian_relationship_1);
        guardianPhoneNumber = findViewById(R.id.activity_student_details_textView_guardian_phone_number_1);
        guardianEmail = findViewById(R.id.activity_student_details_textView_guardian_email_1);

        Intent intent = getIntent();
        Student student = (Student) intent.getParcelableExtra("Student");
        student.setGuardian((Guardian) intent.getParcelableExtra("Guardian"));

        studentName.setText("Name: " + student.getStudentName());
        studentID.setText("ID: " + String.valueOf(student.getStudentID()));
        studentDOB.setText("DOB: " + student.getStudentDOB());
        studentGender.setText("Gender: " + student.getStudentGender());
        guardian.setText("Guardian:");
        guardianName.setText("Name: " + student.getGuardian().getGuardianName());
        guardianRelationship.setText("Relationship: " + student.getGuardian().getRelationship());
        guardianPhoneNumber.setText("Phone number: " + student.getGuardian().getGuardianPhoneNumber());
        guardianEmail.setText("Email: " + student.getGuardian().getGuardianEmail());
    }
}