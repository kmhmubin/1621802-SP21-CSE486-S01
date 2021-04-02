package com.example.loginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button clicked or not
                if (etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("password")) {
                    // adding alert dialog if successful login
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    // adding icon on alert
                    builder.setIcon(R.drawable.ic_outline_check_circle_outline_24);
                    builder.setTitle("Login Successful");
                    builder.setMessage("Welcome Admin");
                    builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // show toast message if failed
                    Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}