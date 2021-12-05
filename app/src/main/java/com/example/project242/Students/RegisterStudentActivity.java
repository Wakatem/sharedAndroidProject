package com.example.project242.Students;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.general.DataContainer;
import com.example.project242.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterStudentActivity extends AppCompatActivity {

    private EditText studentLastNameEditText;
    private EditText studentFirstNameEditText;
    private EditText studentDOBEditText;
    private RadioGroup studentGenderRadioGroup;
    private RadioButton studentGenderRadioButton;
    private RadioButton studentMale;
    private RadioButton studentFemale;
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

        setupToolbar("Register New Student");

        studentLastNameEditText = (EditText) findViewById(R.id.activity_register_student_editText_student_last_name_1);
        studentFirstNameEditText = (EditText) findViewById(R.id.activity_register_student_editText_student_first_name_1);
        studentDOBEditText = (EditText) findViewById(R.id.activity_register_student_editText_student_dob_1);
        studentGenderRadioGroup = (RadioGroup) findViewById(R.id.activity_register_student_editText_student_gender_1);
        studentMale = (RadioButton) findViewById(R.id.male);
        studentFemale =  findViewById(R.id.female);
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
                String studentGender;
                if(studentMale.isChecked()){
                    studentGender = "male";
                }
                else {
                    studentGender = "female";
                }
                String guardianName = ((guardianFirstNameEditText.getText()) + " " + (guardianLastNameEditText.getText()));
                String guardianRelation = String.valueOf(guardianRelationshipEditText.getText());
                String guardianPhoneNumber = String.valueOf(guardianPhoneNumberEditText.getText());
                String guardianEmail = String.valueOf(guardianEmailEditText.getText());
                String guardianAccountNumber = String.valueOf(guardianAccountNumberEditText.getText());

                String studentName = ((studentFirstNameEditText.getText()) + " " + (studentLastNameEditText.getText()));
                String studentDOB = String.valueOf(studentDOBEditText.getText());

                int studentID = generateStudentId();

                if(guardianName.length() != 0 && guardianRelation.length() != 0 && guardianPhoneNumber.length() != 0 &&  guardianEmail.length() != 0 && guardianAccountNumber.length() != 0 && studentName.length() != 0 && studentDOB.length() != 0  && studentGender.length() != 0) {
                    if(isValidDate(studentDOB)) {
                        if(isValidPhone(guardianPhoneNumber)) {
                            if(isValidEmail(guardianEmail)) {
                                Guardian guardian = new Guardian(guardianName, guardianRelation, guardianPhoneNumber, guardianEmail, guardianAccountNumber);
                                Student student = new Student(studentID, studentName, studentDOB, studentGender, guardian);

                                dialogRegisterStudent = new DialogRegisterStudent();

                                Bundle bundle = new Bundle();
                                bundle.putParcelable("Student", student);
                                dialogRegisterStudent.setArguments(bundle);
                                dialogRegisterStudent.show(getSupportFragmentManager(), "Check-In Dialog");
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "please enter correct email", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "please enter Phone number in correct format", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "please enter DOB in dd/mm/yyyy format", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "please fill all fields to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // generate random student ID.
    public int generateStudentId() {
        int min = 1000;
        int max = 9999;

        int randomID = (int)(Math.floor(Math.random()*(max-min+1)+min));;

        for (int i = 0; i < DataContainer.allStudentsArrayList.size(); ++i) {
            if (randomID == DataContainer.allStudentsArrayList.get(i).getStudentID()) {
                i = 0;
                randomID = (int)(Math.floor(Math.random()*(max-min+1)+min));
            }
        }

        return randomID;
    }


    private void setupToolbar(String title) {
        View toolbarIncluder = findViewById(R.id.toolbar_includer);
        TextView screenTitle = (TextView) toolbarIncluder.findViewById(R.id.screenTitle);
        screenTitle.setText(title);

        ImageView backButton = (ImageView) toolbarIncluder.findViewById(R.id.menu_button);
        backButton.setImageResource(R.drawable.back_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

    public boolean isValidDate(String date) {
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();

    }

    public boolean isValidEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public boolean isValidPhone(String phone) {
        String regex = "^\\(\\d{3}\\) \\d{3}-\\d{4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        return  matcher.matches();
    }

}