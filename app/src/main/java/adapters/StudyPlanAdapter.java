package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_assign.R;

import java.util.List;

import models.StudyPlan;

public class StudyPlanAdapter extends BaseAdapter {
    private Context context;
    private List<StudyPlan> studyPlans;

    public StudyPlanAdapter(Context context, List<StudyPlan> studyPlans) {
        this.context = context;
        this.studyPlans = studyPlans;
    }

    @Override
    public int getCount() {
        return studyPlans.size();
    }

    @Override
    public Object getItem(int position) {
        return studyPlans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.study_plan_item, parent, false);
        }

        TextView tvSubject = convertView.findViewById(R.id.tvSubject);
        TextView tvParticipants = convertView.findViewById(R.id.tvParticipants);

        StudyPlan studyPlan = studyPlans.get(position);
        tvSubject.setText(studyPlan.getSubject());
        tvParticipants.setText(studyPlan.getParticipants());

        return convertView;
    }
}
