package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteFaculty extends AppCompatActivity {

    Button saveButton;
    DatabaseHelper helper;
    EditText facultyIDField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_faculty);
        setTitle("Delete Faculty");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        facultyIDField = findViewById(R.id.facultyIdDeleteFaculty);
        saveButtonFun();
    }

    public void saveButtonFun() {
        saveButton = findViewById(R.id.saveButtonDeleteFaculty);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facultyID;
                facultyID = facultyIDField.getText().toString();
                if (facultyID.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    facultyIDField.setError("Field can't be empty.");
                } else {
                    int isDeleted = helper.deleteFaculty(facultyID);
                    if (isDeleted > 0) {
                        Toast.makeText(DeleteFaculty.this, "Faculty Deleted Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(DeleteFaculty.this, FacultyMainMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred in data updating.
                        Toast.makeText(DeleteFaculty.this, "Faculty Deletion Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
