package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFaculty extends AppCompatActivity {

    Button saveButton, resetButtonUpdateFaculty;
    DatabaseHelper helper;
    EditText facultyIDField, fullNameField, fullAddressField, qualificationField, experienceField, salaryField, designationField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        setTitle("Update Faculty");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        facultyIDField = findViewById(R.id.facultyIdUpdateFaculty);
        fullNameField = findViewById(R.id.fullNameUpdateFaculty);
        fullAddressField = findViewById(R.id.fullAddressUpdateFaculty);
        qualificationField = findViewById(R.id.qualificationUpdateFaculty);
        experienceField = findViewById(R.id.experienceUpdateFaculty);
        salaryField = findViewById(R.id.salaryUpdateFaculty);
        designationField = findViewById(R.id.designationUpdateFaculty);
        saveButtonFun();
        resetButtonFunction();
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonUpdateFaculty);
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
                } else {
                    int isUpdated = helper.updateFaculty(facultyID, fullName, fullAddress, qualification, experience, salary, designation);
                    if (isUpdated > 0) {
                        Toast.makeText(UpdateFaculty.this, "Update Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(UpdateFaculty.this, FacultyMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred in data updating.
                        Toast.makeText(UpdateFaculty.this, "Update Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void resetButtonFunction() {
        resetButtonUpdateFaculty = findViewById(R.id.resetButtonUpdateFaculty);
        resetButtonUpdateFaculty.setOnClickListener(new View.OnClickListener() {
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
