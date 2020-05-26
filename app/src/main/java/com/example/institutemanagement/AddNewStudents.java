package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewStudents extends AppCompatActivity {

    Spinner trade, semester;
    ArrayList<String> tradeList = new ArrayList<>();
    ArrayList<String> semesterList = new ArrayList<>();
    Button saveButton, resetButton;
    DatabaseHelper helper;
    EditText studentIDField, rollNoField, fullNameField, fatherNameField, fullAddressField, contactNoField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_students);
        setTitle("Add New Students");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        studentIDField = findViewById(R.id.studentIdAddStudents);
        rollNoField = findViewById(R.id.rollNoAddStudents);
        fullNameField = findViewById(R.id.fullNameAddStudents);
        fatherNameField = findViewById(R.id.fatherNameAddStudents);
        fullAddressField = findViewById(R.id.fullAddressAddStudents);
        contactNoField = findViewById(R.id.contactNoAddStudents);
        trade = findViewById(R.id.spinnerTradeAddStudents);
        semester = findViewById(R.id.spinnerSemesterAddStudents);
        spinnerSemAndTradePicker();
        saveButtonFun();
        resetButtonFun();
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
                AddNewStudents.this, android.R.layout.simple_spinner_dropdown_item, tradeList);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(
                AddNewStudents.this, android.R.layout.simple_spinner_dropdown_item, semesterList);

        trade.setAdapter(tradeAdapter);
        semester.setAdapter(semesterAdapter);
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonAddStudents);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId, rollNo, trade1, semester1, fullName, fatherName, fullAddress, contactNo;
                studentId = studentIDField.getText().toString();
                rollNo = rollNoField.getText().toString();
                trade1 = trade.getSelectedItem().toString();
                semester1 = semester.getSelectedItem().toString();
                fullName = fullNameField.getText().toString();
                fatherName = fatherNameField.getText().toString();
                fullAddress = fullAddressField.getText().toString();
                contactNo = contactNoField.getText().toString();

                //Checking if all fields are empty
                if (studentId.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    studentIDField.setError("Field can't be empty.");
                } else if (rollNo.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    rollNoField.setError("Field can't be empty.");
                } else if (fullName.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    fullNameField.setError("Field can't be empty.");
                } else if (fatherName.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    fatherNameField.setError("Field can't be empty.");
                } else if (fullAddress.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    fullAddressField.setError("Field can't be empty.");
                } else if (contactNo.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    contactNoField.setError("Field can't be empty.");
                } else {
                    boolean isInserted = helper.addNewStudent(studentId, rollNo, trade1, semester1, fullName, fatherName, fullAddress, contactNo);
                    if (isInserted) {
                        Toast.makeText(AddNewStudents.this, "New Student Added Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddNewStudents.this, StudentsMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred during data insertion to database.
                        Toast.makeText(AddNewStudents.this, "Operation UnSuccessFull", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void resetButtonFun() {
        resetButton = findViewById(R.id.resetButtonAddStudents);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentIDField.setText("");
                rollNoField.setText("");
                fullNameField.setText("");
                fatherNameField.setText("");
                fullAddressField.setText("");
                contactNoField.setText("");
            }
        });
    }
}
