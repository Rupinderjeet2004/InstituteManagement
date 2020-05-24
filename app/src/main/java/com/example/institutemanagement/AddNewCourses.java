package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewCourses extends AppCompatActivity {

    Button saveButton, resetButton;
    DatabaseHelper helper;
    EditText courseIDField, courseNameField, durationField, feeField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_courses);
        setTitle("Add New Courses");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        courseIDField = findViewById(R.id.courseIDAddCourses);
        courseNameField = findViewById(R.id.courseNameAddCourses);
        durationField = findViewById(R.id.durationAddCourses);
        feeField = findViewById(R.id.feeAddCourses);
        saveButtonFun();
        resetButtonFun();
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonAddCourses);
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
                } else if (courseName.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    courseNameField.setError("Field can't be empty.");
                } else if (duration.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    durationField.setError("Field can't be empty.");
                } else if (fee.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    feeField.setError("Field can't be empty.");
                } else {
                    boolean isInserted = helper.addNewCourses(ID, courseName, duration, fee);
                    if (isInserted) { // If return value of addNewCourses method is true then message of New Course Added Successfully is shown.
                        Toast.makeText(AddNewCourses.this, "New Course Added Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddNewCourses.this, CoursesMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred during data insertion to database.
                        Toast.makeText(AddNewCourses.this, "Operation UnSuccessFull", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void resetButtonFun() {
        resetButton = findViewById(R.id.resetButtonAddCourses);
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
