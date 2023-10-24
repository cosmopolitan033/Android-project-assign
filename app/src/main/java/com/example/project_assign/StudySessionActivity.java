package com.example.project_assign;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapters.StudySessionAdapter;
import models.StudySession;
import utils.DatabaseHelper;

public class StudySessionActivity extends AppCompatActivity {
    private ListView studySessionListView;
    private Button addSessionButton;
    private StudySessionAdapter studySessionAdapter;
    private List<StudySession> studySessions;
    private DatabaseHelper databaseHelper;
    private long studyPlanId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_session);
        databaseHelper = new DatabaseHelper(this);
        studySessionListView = findViewById(R.id.lvStudySessions);
        addSessionButton = findViewById(R.id.btnAddSession);
        studyPlanId = getIntent().getLongExtra("STUDY_PLAN_ID", -1);
        studySessions = databaseHelper.getStudySessionsByStudyPlanId(studyPlanId);
        studySessionAdapter = new StudySessionAdapter(this, studySessions);
        studySessionListView.setAdapter(studySessionAdapter);

        addSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for adding a new study session
            }
        });
    }
}
