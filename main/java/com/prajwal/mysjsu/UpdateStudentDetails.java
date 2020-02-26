package com.prajwal.mysjsu;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateStudentDetails extends AppCompatActivity {

    EditText nameText, emailText, phoneText, addressText;
    TextView studentIDText;
    Button updateButton;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_details);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        phoneText = findViewById(R.id.phoneText);
        addressText = findViewById(R.id.addressText);
        updateButton = findViewById(R.id.updateButton);
        studentIDText = findViewById(R.id.studentIdVal);

        nameText.setText(getIntent().getStringExtra("name"));
        phoneText.setText(getIntent().getStringExtra("phone"));
        emailText.setText(getIntent().getStringExtra("email"));
        addressText.setText(getIntent().getStringExtra("address"));

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        studentIDText.setText(Integer.toString(id));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(UpdateStudentDetails.this, R.style.AppTheme));
                builder.setMessage("Are you sure?").setTitle("Confirm");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {
                            SQLiteDatabase database = UpdateStudentDetails.this.openOrCreateDatabase("STUDENTS", MODE_PRIVATE, null);
                            database.execSQL("UPDATE students SET name = '" + nameText.getText().toString() + "', email = '" + emailText.getText().toString() + "', phone = " + Integer.parseInt(phoneText.getText().toString()) + ", address = '" + addressText.getText().toString() + "' WHERE id = " + id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(UpdateStudentDetails.this, "Updated the Student Details", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UpdateStudentDetails.this, "Cancelled Update", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
