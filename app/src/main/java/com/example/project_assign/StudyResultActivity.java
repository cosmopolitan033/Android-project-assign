package com.example.project_assign;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import models.StudyResult;
import utils.DatabaseHelper;

public class StudyResultActivity extends AppCompatActivity {
    private EditText etNotes;
    private EditText etRating;
    private EditText etFeedback;
    private Button btnSaveResult;
    private DatabaseHelper databaseHelper;
    private long studyResultId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_result);
        databaseHelper = new DatabaseHelper(this);
        etNotes = findViewById(R.id.etNotes);
        etRating = findViewById(R.id.etRating);
        etFeedback = findViewById(R.id.etFeedback);
        btnSaveResult = findViewById(R.id.btnSaveResult);
        studyResultId = getIntent().getLongExtra("STUDY_RESULT_ID", -1);
        StudyResult studyResult = databaseHelper.getStudyResultById(studyResultId);

        if (studyResult != null) {
            etNotes.setText(studyResult.getNotes());
            etRating.setText(String.valueOf(studyResult.getRating()));
            etFeedback.setText(studyResult.getFeedback());
        }

        btnSaveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for saving the study result
            }
        });
    }
}
