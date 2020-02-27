package com.prajwal.mysjsu;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView studentID, name, email, phone, address;
    FloatingActionButton fab;
    Fragment infoFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.infoItem: {
                FragmentManager manager = getSupportFragmentManager();
                infoFragment = manager.findFragmentById(R.id.fragment);
                FragmentTransaction ft = manager.beginTransaction();
//                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out ).hide(infoFragment).commit();
                if(infoFragment.isHidden()){
                    ft.show(infoFragment);
                } else {
                    ft.hide(infoFragment);
                }
                ft.commit();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        FragmentManager manager = getSupportFragmentManager();
        infoFragment = manager.findFragmentById(R.id.fragment);
        FragmentTransaction ft = manager.beginTransaction();
        ft.hide(infoFragment);
        ft.commit();


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
