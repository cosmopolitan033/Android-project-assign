package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_assign.R;

import java.util.List;

import models.StudySession;

public class StudySessionAdapter extends BaseAdapter {
    private Context context;
    private List<StudySession> studySessions;

    public StudySessionAdapter(Context context, List<StudySession> studySessions) {
        this.context = context;
        this.studySessions = studySessions;
    }

    @Override
    public int getCount() {
        return studySessions.size();
    }

    @Override
    public Object getItem(int position) {
        return studySessions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.study_session_item, parent, false);
        }

        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvTopic = convertView.findViewById(R.id.tvTopic);
        TextView tvResult = convertView.findViewById(R.id.tvResult);

        StudySession studySession = studySessions.get(position);
        tvDate.setText(studySession.getDate());
        tvTopic.setText(studySession.getTopic());
        // You will need to fetch the result using the result ID and set it here
        tvResult.setText("Result: " + String.valueOf(studySession.getStudyResultId()));

        return convertView;
    }
}
