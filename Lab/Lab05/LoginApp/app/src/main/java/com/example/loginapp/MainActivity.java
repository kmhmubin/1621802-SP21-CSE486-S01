package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // variables for the input fields and button
    EditText etUsername, etPassword;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference the input fields and button with xml
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btLogin = findViewById(R.id.login_button);

        // adding a click listener for button
        
    }
}