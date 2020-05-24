package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteCourses extends AppCompatActivity {

    Button saveButton;
    DatabaseHelper helper;
    EditText courseIDField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_courses);
        setTitle("Delete Courses");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        courseIDField = findViewById(R.id.courseIDDeleteCourses);
        saveButtonFun();
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonDeleteCourses);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID;
                ID = courseIDField.getText().toString();
                if (ID.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    courseIDField.setError("Field can't be empty.");
                } else {
                    int isDeleted = helper.deleteCourses(ID);
                    if (isDeleted > 0) { // If return value of deleteCourses method is less than 0 message of Course Deleted Successful is shown.
                        Toast.makeText(DeleteCourses.this, "Course Deleted Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(DeleteCourses.this, CoursesMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred in data updating.
                        Toast.makeText(DeleteCourses.this, "Course Deletion Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
