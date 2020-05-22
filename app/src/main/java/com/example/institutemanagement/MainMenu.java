package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button coursesButton, facultyButton, studentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setTitle("Main Menu");
        findCourses();
        findFaculty();
        findStudent();
    }

    public void findCourses() {
        //finding each button by its ID
        coursesButton = findViewById(R.id.mainMenuCoursesButt);
        //on click listener for courses button that switches main activity to coursesMainMenu activity
        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, CoursesMainMenu.class);
                startActivity(intent);
            }
        });
    }

    public void findFaculty() {
        //finding each button by its ID
        facultyButton = findViewById(R.id.mainMenuFacultyButt);
        //on click listener for faculty button that switches main activity to facultyMainMenu activity
        facultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, FacultyMainMenu.class);
                startActivity(intent);
            }
        });
    }

    public void findStudent() {
        //finding each button by its ID
        studentButton = findViewById(R.id.mainMenuStudentsButt);
        //on click listener for students button that switches main activity to studentMainMenu activity
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, StudentsMainMenu.class);
                startActivity(intent);
            }
        });
    }
}
