package com.prajwal.mysjsu;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView studentID, name, email, phone, address;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        studentID = findViewById(R.id.studentIdVal);
        name = findViewById(R.id.nameValue);
        email = findViewById(R.id.emailValue);
        phone = findViewById(R.id.phoneValue);
        address = findViewById(R.id.addressValue);
        fab = findViewById(R.id.floatingActionButton);

        studentID.setText(getIntent().getStringExtra("id"));
        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        phone.setText(getIntent().getStringExtra("phone"));
        address.setText(getIntent().getStringExtra("address"));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDetailsActivity.this, UpdateStudentDetails.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("phone", getIntent().getStringExtra("phone"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("address", getIntent().getStringExtra("address"));
                startActivity(intent);
            }
        });
    }
}
