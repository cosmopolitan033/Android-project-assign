package adapters;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import models.StudyPlan;

public class StudyPlanAdapter extends RecyclerView {
    private List<StudyPlan> studyPlans;
    public StudyPlanAdapter(@NonNull Context context) {
        super(context);
    }

    public StudyPlanAdapter(@NonNull Context context, List<StudyPlan> studyPlans) {
        super(context);
        this.studyPlans = studyPlans;
    }
}
