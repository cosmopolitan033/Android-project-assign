package com.example.project_assign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import models.User;
import utils.DatabaseHelper;

public class LoginActivity extends Activity {
    private EditText userNameEditText;
    private EditText userPasswordEditText;
    private Button loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
        userNameEditText = findViewById(R.id.userNameEditText);
        userPasswordEditText = findViewById(R.id.userPasswordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });

    }
    private void authenticateUser(){
        String userName = userNameEditText.getText().toString();
        String userPassword = userPasswordEditText.getText().toString();
        User storedUser = databaseHelper.getUserByUserName(userName);

        if (storedUser != null) {
            if (userPassword.equals(storedUser.getPassword())){
                Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                intent.putExtra("USER_ID",storedUser.getUserId());
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this,"Incorrect password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"UserName not found", Toast.LENGTH_SHORT).show();
        }
    }

}
