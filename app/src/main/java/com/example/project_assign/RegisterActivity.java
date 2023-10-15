package com.example.project_assign;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import models.User;
import utils.DatabaseHelper;

public class RegisterActivity extends Activity {
    private EditText userNameEditText;
    private EditText userPasswordEditText;
    private Button registerButton;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        userNameEditText = findViewById(R.id.userNameEditText1);
        userPasswordEditText = findViewById(R.id.userPasswordEditText1);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }
    private void registerUser() {
        String userName = userNameEditText.getText().toString();
        String userPassword = userPasswordEditText.getText().toString();
        if (databaseHelper.getUserByUserName(userName) == null) {
            User newUser = new User(userName, userPassword);
            databaseHelper.insertUser(newUser);
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "UserName already exists", Toast.LENGTH_SHORT).show();
        }
    }
}
