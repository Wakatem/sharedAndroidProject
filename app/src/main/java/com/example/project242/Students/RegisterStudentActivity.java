package com.example.project242.Students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;
import com.example.project242.SectionsMenu;

public class RegisterStudentActivity extends AppCompatActivity {

    private EditText studentLastNameEditText;
    private EditText studentFirstNameEditText;
    private EditText studentDOBEditText;
    private RadioGroup studentGenderRadioGroup;
    private RadioButton studentGenderRadioButton;
    private EditText guardianLastNameEditText;
    private EditText guardianFirstNameEditText;
    private EditText guardianRelationshipEditText;
    private EditText guardianPhoneNumberEditText;
    private EditText guardianEmailEditText;
    private EditText guardianAccountNumberEditText;
    private Button registerButton;

    private DialogRegisterStudent dialogRegisterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer);
        menu.initialize();
        menu.setToolbarTitle("Register New Student");
        menu.setOptionSelectedListener();

        studentLastNameEditText = (EditText) findViewById(R.id.activity_register_student_editText_student_last_name_1);
        studentFirstNameEditText = (EditText) findViewById(R.id.activity_register_student_editText_student_first_name_1);
        studentDOBEditText = (EditText) findViewById(R.id.activity_register_student_editText_student_dob_1);
        studentGenderRadioGroup = (RadioGroup) findViewById(R.id.activity_register_student_editText_student_gender_1);

        guardianLastNameEditText = (EditText) findViewById(R.id.activity_register_student_editText_guardian_last_name_1);
        guardianFirstNameEditText = (EditText) findViewById(R.id.activity_register_student_editText_guardian_first_name_1);
        guardianRelationshipEditText = (EditText) findViewById(R.id.activity_register_student_editText_guardian_relation_1);
        guardianPhoneNumberEditText = (EditText) findViewById(R.id.activity_register_student_editText_guardian_phone_number_1);
        guardianEmailEditText = (EditText) findViewById(R.id.activity_register_student_editText_guardian_email_1);
        guardianAccountNumberEditText = (EditText) findViewById(R.id.activity_register_student_editText_guardian_account_name_1);

        registerButton = findViewById(R.id.activity_register_student_button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioButtonId = studentGenderRadioGroup.getCheckedRadioButtonId();
                studentGenderRadioButton = (RadioButton) findViewById(radioButtonId);

                String guardianName = ((guardianFirstNameEditText.getText()) + " " + (guardianLastNameEditText.getText()));
                String guardianRelation = String.valueOf(guardianRelationshipEditText.getText());
                String guardianPhoneNumber = String.valueOf(guardianPhoneNumberEditText.getText());
                String guardianEmail = String.valueOf(guardianEmailEditText.getText());
                String guardianAccountNumber = String.valueOf(guardianAccountNumberEditText.getText());
                Guardian guardian = new Guardian(guardianName, guardianRelation, guardianPhoneNumber, guardianEmail, guardianAccountNumber);

                String studentName = ((studentFirstNameEditText.getText()) + " " + (studentLastNameEditText.getText()));
                String studentDOB = String.valueOf(studentDOBEditText.getText());
                String studentGender = String.valueOf(studentGenderRadioButton.getText());
                int studentID = generateStudentId();
                Student student = new Student(studentID, studentName, studentDOB, studentGender, guardian);

                dialogRegisterStudent = new DialogRegisterStudent();

                Bundle bundle = new Bundle();
                bundle.putParcelable("Student", student);
                dialogRegisterStudent.setArguments(bundle);
                dialogRegisterStudent.show(getSupportFragmentManager(), "Check-In Dialog");
            }
        });
    }

    // generate random student ID.
    public int generateStudentId() {
        int min = 1000;
        int max = 9999;

        int randomID = (int)(Math.floor(Math.random()*(max-min+1)+min));;

        for (int i = 0; i < HomeSection.allStudentsArrayList.size(); ++i) {
            if (randomID == HomeSection.allStudentsArrayList.get(i).getStudentID()) {
                i = 0;
                randomID = (int)(Math.floor(Math.random()*(max-min+1)+min));
            }
        }

        return randomID;
    }
}