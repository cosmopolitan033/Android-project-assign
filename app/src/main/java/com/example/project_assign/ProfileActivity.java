package com.example.project_assign;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import models.Profile;
import utils.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private long userID;
    private Profile profile;

    private EditText etName;
    private EditText etSubjects;
    private EditText etStudyPreference;
    private EditText etAvailability;
    private Button btnEditProfile;
    private Button btnSaveProfile;
    private Button btnStudyPlans;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db=new DatabaseHelper(this);
        userID = getIntent().getLongExtra("USER_ID",-1);
        profile = db.getProfileyUserId(userID);

        etName = findViewById(R.id.tvName);
        etSubjects = findViewById(R.id.tvSubjects);
        etAvailability = findViewById(R.id.tvAvailability);
        etStudyPreference = findViewById(R.id.tvStudyPreference);
        btnEditProfile = findViewById(R.id.btnEdit);
        btnSaveProfile = findViewById(R.id.btnSave);
        btnStudyPlans = findViewById(R.id.btnStudyPlans);
        setFieldsEditable(false);

        Log.d("fuck", String.valueOf(profile));
        if (profile != null) {
            etName.setText(profile.getName());
            etSubjects.setText(profile.getSubjects());
            etAvailability.setText(profile.getAvailability());
            etStudyPreference.setText(profile.getStudyPreference());
        }
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFieldsEditable(true);
            }
        });
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfileData(userID);
                setFieldsEditable(false);

            }
        });
        btnStudyPlans.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, StudyPlanActivity.class);
            intent.putExtra("USER_ID", userID);
            startActivity(intent);
        });
    }
    private void setFieldsEditable(boolean editable){
        etName.setEnabled(editable);
        etSubjects.setEnabled(editable);
        etAvailability.setEnabled(editable);
        etStudyPreference.setEnabled(editable);
    }
    private void saveProfileData(long userId){
        String name = etName.getText().toString();
        String subjects = etSubjects.getText().toString();
        String availability = etAvailability.getText().toString();
        String studyPreference = etStudyPreference.getText().toString();
        if (db.profileExists(userId)) {
            db.updateProfile(userId, name, subjects, availability, studyPreference);
        } else {
            db.insertProfile(userId, name, subjects, availability, studyPreference);
        }
    }
}
