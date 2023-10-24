package com.example.project_assign;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapters.StudyPlanAdapter;
import models.StudyPlan;
import utils.DatabaseHelper;

public class StudyPlanActivity extends AppCompatActivity {
    private ListView studyPlanListView;
    private Button addStudyPlanButton;
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
        addStudyPlanButton = findViewById(R.id.btnCreate);

        userId = getIntent().getLongExtra("USER_ID",-1);
        studyPlans = databaseHelper.getStudyPlans(userId);
        studyPlanAdapter = new StudyPlanAdapter(this,studyPlans);
        studyPlanListView.setAdapter((ListAdapter) studyPlanAdapter);

        addStudyPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
