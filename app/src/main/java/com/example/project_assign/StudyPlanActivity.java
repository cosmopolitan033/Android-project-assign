package com.example.project_assign;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapters.StudyPlanAdapter;
import models.StudyPlan;
import utils.DatabaseHelper;

public class StudyPlanActivity extends AppCompatActivity {
    private ListView studyPlanListView;
    private TextView emptyMessageTextView;
    private Button addStudyPlanButton;
    private EditText editTextSubject;
    private EditText editTextParticipants;
    private Button saveStudyPlanButton;
    private StudyPlanAdapter studyPlanAdapter;
    private List<StudyPlan> studyPlans;
    private DatabaseHelper databaseHelper;

    private long userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_plan);

        databaseHelper = new DatabaseHelper(this);
        studyPlanListView = findViewById(R.id.lvStudyPlans);
        emptyMessageTextView = findViewById(R.id.tvEmptyMessage);
        addStudyPlanButton = findViewById(R.id.btnCreate);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextParticipants = findViewById(R.id.editTextParticipants);
        saveStudyPlanButton = findViewById(R.id.btnSaveStudyPlan);

        userId = getIntent().getLongExtra("USER_ID", -1);
        studyPlans = databaseHelper.getStudyPlans(userId);

        if (studyPlans != null && studyPlans.size() > 0) {
            studyPlanAdapter = new StudyPlanAdapter(this, studyPlans);
            studyPlanListView.setAdapter(studyPlanAdapter);
            emptyMessageTextView.setVisibility(View.GONE);
        } else {
            studyPlanListView.setVisibility(View.GONE);
            emptyMessageTextView.setVisibility(View.VISIBLE);
            emptyMessageTextView.setText("No study plans available. Please add a new study plan.");
        }

        addStudyPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextSubject.setVisibility(View.VISIBLE);
                editTextParticipants.setVisibility(View.VISIBLE);
                saveStudyPlanButton.setVisibility(View.VISIBLE);
            }
        });

        saveStudyPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = editTextSubject.getText().toString();
                String participants = editTextParticipants.getText().toString();
                StudyPlan newStudyPlan = new StudyPlan(subject, participants);
                long newStudyPlanId = databaseHelper.insertStudyPlan(newStudyPlan,userId);

                // Update the ListView with the new study plan
                studyPlans.add(newStudyPlan);
                studyPlanAdapter.notifyDataSetChanged();
                editTextSubject.setText("");
                editTextParticipants.setText("");
                editTextSubject.setVisibility(View.GONE);
                editTextParticipants.setVisibility(View.GONE);
                saveStudyPlanButton.setVisibility(View.GONE);

                if (studyPlans.size() > 0) {
                    studyPlanListView.setVisibility(View.VISIBLE);
                    emptyMessageTextView.setVisibility(View.GONE);
                }
            }
        });
    }
}
