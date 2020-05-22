package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class StudentsMainMenu extends AppCompatActivity {

    Button addNewStudent, updateStudent, deleteStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_main_menu);
        setTitle("Students Menu");
        addNewStudentFun();
        updateStudentFun();
        deleteStudentFun();
    }

    public void addNewStudentFun() {
        addNewStudent = findViewById(R.id.addNewStudents);
        addNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentsMainMenu.this, AddNewStudents.class);
                startActivity(i);
            }
        });
    }

    public void updateStudentFun() {
        updateStudent = findViewById(R.id.updateStudents);
        updateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentsMainMenu.this, UpdateStudents.class);
                startActivity(i);
            }
        });
    }

    public void deleteStudentFun() {
        deleteStudent = findViewById(R.id.deleteStudents);
        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentsMainMenu.this, DeleteStudents.class);
                startActivity(i);
            }
        });
    }
}
