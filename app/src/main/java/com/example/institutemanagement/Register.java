package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    //Declaring Variables
    Button register;
    DatabaseHelper helper;
    EditText usernameField, passwordField, confirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        registerSaveButtonClick();
    }

    public void registerSaveButtonClick() {
        register = findViewById(R.id.registerSave);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Finding all the textFields by their id's
                usernameField = findViewById(R.id.usernameFieldRegisterFrame);
                passwordField = findViewById(R.id.passwordFieldRegisterFrame);
                confirmPasswordField = findViewById(R.id.confirmPasswordFieldRegisterFrame);

                //Declaring temporary variables
                String user, pass, confPass;
                //Getting data from text fields and storing it to temp variables
                user = usernameField.getText().toString();
                pass = passwordField.getText().toString();
                confPass = confirmPasswordField.getText().toString();

                //Checking if all fields are empty
                if (user.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    usernameField.setError("Field can't be empty.");
                } else if (pass.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    passwordField.setError("Field can't be empty.");
                } else if (confPass.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    confirmPasswordField.setError("Field can't be empty.");
                } else if (!pass.equals(confPass)) {  // Checking if the password field text is equals to confirmed password field text.
                    passwordField.setError("Passwords doesn't match.");
                    confirmPasswordField.setError("Passwords doesn't match.");
                } else {
                    //Passing user entered values to the registerUser method which is written in Database Helper class.
                    boolean isInserted = helper.registerUser(user, pass);
                    if (isInserted) { // If return value of registerUser method is true then message of Registration SuccessFull is shown.
                        Toast.makeText(Register.this, "Registration SuccessFul", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Register.this, Login.class);
                        startActivity(i);
                        finish();
                    } else {
                        //This message will be shown to user if some error occurred during data insertion to database.
                        Toast.makeText(Register.this, "Registration UnSuccessFul", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
