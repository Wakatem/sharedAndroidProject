package com.example.project242.Students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    private TextView guardianAccountNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        View includer = findViewById(R.id.includer);
        setupToolbar(includer, "Student Details");

        studentName = findViewById(R.id.activity_student_details_textView_student_name_1);
        studentID = findViewById(R.id.activity_student_details_textView_student_id_1);
        studentDOB = findViewById(R.id.activity_student_details_textView_student_DOB_1);
        studentGender = findViewById(R.id.activity_student_details_textView_student_gender_1);
        guardian = findViewById(R.id.activity_student_details_textView_student_guardian_1);
        guardianName = findViewById(R.id.activity_student_details_textView_guardian_name_1);
        guardianRelationship = findViewById(R.id.activity_student_details_textView_guardian_relationship_1);
        guardianPhoneNumber = findViewById(R.id.activity_student_details_textView_guardian_phone_number_1);
        guardianEmail = findViewById(R.id.activity_student_details_textView_guardian_email_1);
        guardianAccountNumber = findViewById(R.id.activity_student_details_textView_guardian_account_number_1);

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
        guardianAccountNumber.setText("Account Number: " + student.getGuardian().getGuardianAccountNumber());
    }

    private void setupToolbar(View includer, String title){
        Toolbar toolbar       = (Toolbar)   includer.findViewById(R.id.app_toolbar);
        TextView screenTitle  = (TextView)  includer.findViewById(R.id.screenTitle);
        ImageView backButton  = (ImageView) includer.findViewById(R.id.menu_button);

        screenTitle.setText(title);
        backButton.setImageResource(R.drawable.back_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}