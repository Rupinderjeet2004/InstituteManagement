package com.example.institutemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteStudents extends AppCompatActivity {

    Spinner trade, semester;
    ArrayList<String> tradeList = new ArrayList<>();
    ArrayList<String> semesterList = new ArrayList<>();
    Button saveButton;
    EditText studentIDField;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_students);
        setTitle("Delete Students");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        studentIDField = findViewById(R.id.studentIdDeleteStudents);
        trade = findViewById(R.id.spinnerTradeDeleteStudents);
        semester = findViewById(R.id.spinnerSemesterDeleteStudents);
        spinnerSemAndTradePicker();
        saveButtonFun();
    }

    public void spinnerSemAndTradePicker() {
        tradeList.add("CSE");
        tradeList.add("CIVIL");
        tradeList.add("MECHANICAL");
        tradeList.add("ECE");
        tradeList.add("ELECTRICAL");

        semesterList.add("1st");
        semesterList.add("2nd");
        semesterList.add("3rd");
        semesterList.add("4th");
        semesterList.add("5th");
        semesterList.add("6th");

        ArrayAdapter<String> tradeAdapter = new ArrayAdapter<>(
                DeleteStudents.this, android.R.layout.simple_spinner_dropdown_item, tradeList);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(
                DeleteStudents.this, android.R.layout.simple_spinner_dropdown_item, semesterList);
        trade.setAdapter(tradeAdapter);
        semester.setAdapter(semesterAdapter);
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonDeleteStudents);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId, trade1, semester1;
                studentId = studentIDField.getText().toString();
                trade1 = trade.getSelectedItem().toString();
                semester1 = semester.getSelectedItem().toString();
                if (studentId.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    studentIDField.setError("Field can't be empty.");
                } else {
                    int isDeleted = helper.deleteStudents(studentId, trade1, semester1);
                    if (isDeleted > 0) {
                        Toast.makeText(DeleteStudents.this, "Student Deleted Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(DeleteStudents.this, StudentsMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred in data updating.
                        Toast.makeText(DeleteStudents.this, "Student Deletion Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
