package com.example.institutemanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class Login extends AppCompatActivity {

    Button loginButt, registerButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        loginHandler();
        registerButtFun();
    }

    public void loginHandler() {
        //Finding button by its Id using findViewById
        loginButt = findViewById(R.id.login);
        //using onClickListener to change activity when button pressed
        //using lambda expression instead of new OnViewListener
        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }

    public void registerButtFun() {
        registerButt = findViewById(R.id.register);
        registerButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }
}
