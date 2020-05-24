package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCourses extends AppCompatActivity {

    Button saveButton, resetButton;
    DatabaseHelper helper;
    EditText courseIDField, courseNameField, durationField, feeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_courses);
        setTitle("Update Courses");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        courseIDField = findViewById(R.id.courseIDUpdateCourses);
        courseNameField = findViewById(R.id.courseNameUpdateCourses);
        durationField = findViewById(R.id.durationUpdateCourses);
        feeField = findViewById(R.id.feeUpdateCourses);
        saveButtonFun();
        resetButtonFun();
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonUpdateCourses);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID, courseName, duration, fee;
                ID = courseIDField.getText().toString();
                courseName = courseNameField.getText().toString();
                duration = durationField.getText().toString();
                fee = feeField.getText().toString();

                //Checking if all fields are empty
                if (ID.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    courseIDField.setError("Field can't be empty.");
                } else {
                    int isUpdated = helper.updateCourses(ID, courseName, duration, fee);
                    if (isUpdated > 0) { // If return value of updateCourses method is less than 0 message of Update Successful is shown.
                        Toast.makeText(UpdateCourses.this, "Update Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(UpdateCourses.this, CoursesMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred in data updating.
                        Toast.makeText(UpdateCourses.this, "Update Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void resetButtonFun() {
        resetButton = findViewById(R.id.resetButtonUpdateCourses);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseIDField.setText("");
                courseNameField.setText("");
                durationField.setText("");
                feeField.setText("");
            }
        });
    }
}
