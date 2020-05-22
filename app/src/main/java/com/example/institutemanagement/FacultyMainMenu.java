package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacultyMainMenu extends AppCompatActivity {

    Button newFacultyButt, updateFacultyButt, deleteFacultyButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_main_menu);
        setTitle("Faculty Menu");
        addNewFaculty();
        updateFaculty();
        deleteFaculty();
    }

    public void addNewFaculty() {
        newFacultyButt = findViewById(R.id.addNewFaculty);
        newFacultyButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyMainMenu.this, AddNewFaculty.class);
                startActivity(intent);
            }
        });
    }

    public void updateFaculty() {
        updateFacultyButt = findViewById(R.id.updateFaculty);
        updateFacultyButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyMainMenu.this, UpdateFaculty.class);
                startActivity(intent);
            }
        });
    }

    public void deleteFaculty() {
        deleteFacultyButt = findViewById(R.id.deleteFaculty);
        deleteFacultyButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyMainMenu.this, DeleteFaculty.class);
                startActivity(intent);
            }
        });
    }
}
