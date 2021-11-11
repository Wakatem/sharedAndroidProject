package com.example.project242.Students;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project242.R;

public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Intent intent = getIntent();
        String studentName = intent.getStringExtra("Name");
        int studentID = intent.getIntExtra("ID", -1);

        TextView studentNameTextView = (TextView) findViewById(R.id.activity_check_in_textView_student_name_1);
        TextView studentIDTextView = (TextView) findViewById(R.id.activity_check_in_textView_student_id_1);

        studentNameTextView.setText("Name: " + studentName);
        studentIDTextView.setText("ID : " + (String.valueOf(studentID)));

        Button checkInButton = findViewById(R.id.activity_check_in_button);
        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCheckIn dialogCheckIn = new DialogCheckIn();
                Bundle bundle = new Bundle();
                bundle.putString("Name", studentName);
                bundle.putInt("ID", studentID);
                dialogCheckIn.setArguments(bundle);
                dialogCheckIn.show(getSupportFragmentManager(), "Check-in Dialog");
            }
        });
    }
}