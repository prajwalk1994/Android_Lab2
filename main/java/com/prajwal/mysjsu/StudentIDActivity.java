package com.prajwal.mysjsu;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentIDActivity extends AppCompatActivity {

    Button submit;
    EditText studentIdText;
    int studentId;
    boolean exists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_id);

        submit = findViewById(R.id.submitButton);

        studentIdText = findViewById(R.id.studentIDText);

        try {
            final SQLiteDatabase database = this.openOrCreateDatabase("STUDENTS", MODE_PRIVATE, null);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        studentId = Integer.parseInt(studentIdText.getText().toString());
                        database.execSQL("CREATE TABLE IF NOT EXISTS students (id INT(10) PRIMARY KEY, name VARCHAR, email VARCHAR, phone INT(10), address VARCHAR)");

//            database.execSQL("INSERT INTO students (id, name, email, phone, address) VALUES (013637832, 'Prajwal', 'kondubhatlav.eprajwal@gmail.com', '123456789', '101 E San Fernando')");

                        Cursor c = database.rawQuery("SELECT * FROM students WHERE id = " + studentId, null);

                        int idIndex = c.getColumnIndex("id");

                        c.moveToFirst();

                        if (c.getInt(idIndex) == studentId) {
                            Intent intent = new Intent(StudentIDActivity.this, StudentDetailsActivity.class);
                            intent.putExtra("id", Integer.toString(c.getInt(idIndex)));
                            intent.putExtra("name", c.getString(c.getColumnIndex("name")));
                            intent.putExtra("email", c.getString(c.getColumnIndex("email")));
                            intent.putExtra("phone", Integer.toString(c.getInt(c.getColumnIndex("phone"))));
                            intent.putExtra("address", c.getString(c.getColumnIndex("address")));
                            startActivity(intent);
                        } else {
                            Toast.makeText(StudentIDActivity.this, "Student ID does not exist", Toast.LENGTH_SHORT).show();
                        }
                    } catch (CursorIndexOutOfBoundsException e) {
                        Toast.makeText(StudentIDActivity.this, "Student ID does not exist", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
//            Log.i("Hello WHat", "123");
            e.printStackTrace();
        }

    }
}
