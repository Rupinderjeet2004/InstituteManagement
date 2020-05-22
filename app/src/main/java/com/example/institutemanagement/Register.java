package com.example.institutemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button register;
    DatabaseHelper helper;
    EditText usernameField, passwordField, confirmPasswordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
        helper = new DatabaseHelper(this);
        registerSaveButtonClick();
    }

    public void registerSaveButtonClick() {
        register = findViewById(R.id.registerSave);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameField = findViewById(R.id.usernameFieldRegisterFrame);
                passwordField = findViewById(R.id.passwordFieldRegisterFrame);
                confirmPasswordField = findViewById(R.id.confirmPasswordFieldRegisterFrame);

                String user, pass, confPass;
                user = usernameField.getText().toString();
                pass = passwordField.getText().toString();
                confPass = confirmPasswordField.getText().toString();

                if (user.equals("") && pass.equals("") && confPass.equals("")) {
                    usernameField.setError("Field can't be empty.");
                } else if (!pass.equals(confPass)) {
                    passwordField.setError("Passwords doesn't match.");
                    confirmPasswordField.setError("Passwords doesn't match.");
                } else {
                    boolean isInserted = helper.registerUser(user, pass);
                    if (isInserted) {
                        Toast.makeText(Register.this, "Registration SuccessFull", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Register.this, Login.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Register.this, "Registration UnSuccessFull", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
