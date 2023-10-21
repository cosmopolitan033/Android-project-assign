package com.example.project_assign;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import models.Profile;
import utils.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private long userID;
    private Profile profile;

    private TextView tvName;
    private TextView tvSubjects;
    private TextView tvStudyPreference;
    private TextView tvAvailability;
    private Button btnEditProfile;
    private Button btnSaveProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db=new DatabaseHelper(this);
        userID = getIntent().getLongExtra("USER_ID",-1);
        profile = db.getProfileyUserId(userID);

        tvName = findViewById(R.id.tvName);
        tvSubjects = findViewById(R.id.tvSubjects);
        tvAvailability = findViewById(R.id.tvAvailability);
        tvStudyPreference = findViewById(R.id.tvStudyPreference);
        btnEditProfile = findViewById(R.id.btnEdit);
        btnSaveProfile = findViewById(R.id.btnSave);

        if (profile != null) {
            tvName.setText(profile.getName());
            tvSubjects.setText(profile.getSubjects());
            tvAvailability.setText(profile.getAvailability());
            tvStudyPreference.setText(profile.getStudyPreference());
        }
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
//fixme
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
    }
    private void setFieldsEditable(boolean editable){
        tvName.setEnabled(editable);
        tvSubjects.setEnabled(editable);
        tvAvailability.setEnabled(editable);
        tvStudyPreference.setEnabled(editable);
    }
    private void saveProfileData(long userId){
        String name = tvName.getText().toString();
        String subjects = tvSubjects.getText().toString();
        String availability = tvAvailability.getText().toString();
        String studyPreference = tvStudyPreference.getText().toString();
        Profile profile = new Profile(name,subjects,availability,studyPreference);
        db.updateProfile(userId,name,subjects,availability,studyPreference);
    }
}
