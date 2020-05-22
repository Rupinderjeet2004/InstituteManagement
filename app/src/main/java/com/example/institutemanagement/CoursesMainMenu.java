package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;


public class CoursesMainMenu extends AppCompatActivity {

    Button newCourses, updateCourses, deleteCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_main_menu);
        setTitle("Courses Menu");
        addNewCoursesFun();
        updateAllCoursesFun();
        deleteCoursesFun();
    }

    public void addNewCoursesFun() {
        newCourses = findViewById(R.id.newCoursesButton);
        newCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(CoursesMainMenu.this, AddNewCourses.class);
                startActivity(newIntent);
            }
        });
    }

    public void updateAllCoursesFun() {
        updateCourses = findViewById(R.id.updateCoursesButton);
        updateCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoursesMainMenu.this, UpdateCourses.class);
                startActivity(intent);
            }
        });
    }

    public void deleteCoursesFun() {
        deleteCourses = findViewById(R.id.deleteCoursesButton);
        deleteCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoursesMainMenu.this, DeleteCourses.class);
                startActivity(intent);
            }
        });
    }

}
