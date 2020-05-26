package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    //Declaring Variables
    Button loginButt, registerButt;
    DatabaseHelper helper;
    EditText usernameField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        //Instance of SQLiteDatabase
        helper = new DatabaseHelper(this);
        //Calling methods
        loginHandler();
        registerButtFun();
    }

    public void loginHandler() {
        //Finding view by id
        loginButt = findViewById(R.id.login);
        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Finding all the textFields by their id's
                usernameField = findViewById(R.id.usernameFieldLoginFrame);
                passwordField = findViewById(R.id.passwordFieldLoginFrame);

                //Declaring temporary variables
                String user, pass, passwordFromDB;
                //Getting data from text fields and storing it to temp variables
                user = usernameField.getText().toString();
                pass = passwordField.getText().toString();
                passwordFromDB = helper.userLogin(user);

                //Checking if all fields are empty
                if (user.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    usernameField.setError("Field can't be empty.");
                } else if (pass.equals("")) {
                    //Showing error if user tries to press button without filling field.
                    passwordField.setError("Field can't be empty.");
                } else {
                    //Checking if the password form the database is same as the user entered
                    if (pass.equals(passwordFromDB)) {
                        Intent intent = new Intent(Login.this, MainMenu.class);
                        startActivity(intent);
                    } else {
                        //Showing the message if user tries to enter wrong username and password
                        Toast message = Toast.makeText(Login.this, "Username and Password doesn't match or You're not Registered.", Toast.LENGTH_LONG);
                        message.show();
                    }
                }
            }
        });
    }

    public void registerButtFun() {
        //Finding all the textFields by their id's
        registerButt = findViewById(R.id.register);
        registerButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Going to different activity when user click on register button
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }
}
