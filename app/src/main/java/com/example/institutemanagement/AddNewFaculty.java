package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewFaculty extends AppCompatActivity {

    Button saveButton, resetButton;
    DatabaseHelper helper;
    EditText facultyIDField, fullNameField, fullAddressField, qualificationField, experienceField, salaryField, designationField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_faculty);
        setTitle("Add New Faculty");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        facultyIDField = findViewById(R.id.facultyIdAddFaculty);
        fullNameField = findViewById(R.id.fullNameAddFaculty);
        fullAddressField = findViewById(R.id.fullAddressAddFaculty);
        qualificationField = findViewById(R.id.qualificationAddFaculty);
        experienceField = findViewById(R.id.experienceAddFaculty);
        salaryField = findViewById(R.id.salaryAddFaculty);
        designationField = findViewById(R.id.designationAddFaculty);
        saveButtonFun();
        resetButtonFun();
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonAddFaculty);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facultyID, fullName, fullAddress, qualification, experience, salary, designation;
                facultyID = facultyIDField.getText().toString();
                fullName = fullNameField.getText().toString();
                fullAddress = fullAddressField.getText().toString();
                qualification = qualificationField.getText().toString();
                experience = experienceField.getText().toString();
                salary = salaryField.getText().toString();
                designation = designationField.getText().toString();

                //Checking if all fields are empty
                if (facultyID.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    facultyIDField.setError("Field can't be empty.");
                } else if (fullName.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    fullNameField.setError("Field can't be empty.");
                } else if (fullAddress.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    fullAddressField.setError("Field can't be empty.");
                } else if (qualification.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    qualificationField.setError("Field can't be empty.");
                } else if (experience.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    experienceField.setError("Field can't be empty.");
                } else if (salary.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    salaryField.setError("Field can't be empty.");
                } else if (designation.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    designationField.setError("Field can't be empty.");
                } else {
                    boolean isInserted = helper.addNewFaculty(facultyID, fullName, fullAddress, qualification, experience, salary, designation);
                    if (isInserted) {
                        Toast.makeText(AddNewFaculty.this, "New Faculty Added Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddNewFaculty.this, FacultyMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred during data insertion to database.
                        Toast.makeText(AddNewFaculty.this, "Operation UnSuccessFull", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void resetButtonFun() {
        resetButton = findViewById(R.id.resetButtonAddFaculty);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultyIDField.setText("");
                fullNameField.setText("");
                fullAddressField.setText("");
                qualificationField.setText("");
                experienceField.setText("");
                salaryField.setText("");
                designationField.setText("");
            }
        });
    }
}
