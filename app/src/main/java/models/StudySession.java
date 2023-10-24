package models;

public class StudySession {
    private long studySessionId;
    private String date;
    private String topic;
    private long studyResultId;

    public StudySession(String date, String topic, long studyResultId) {
        this.date = date;
        this.topic = topic;
        this.studyResultId = studyResultId;
    }

    public StudySession(long studySessionId, String date, String topic, long studyResultId) {
        this.studySessionId = studySessionId;
        this.date = date;
        this.topic = topic;
        this.studyResultId = studyResultId;
    }

    public long getStudySessionId() {
        return studySessionId;
    }

    public void setStudySessionId(long studySessionId) {
        this.studySessionId = studySessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getStudyResultId() {
        return studyResultId;
    }

    public void setStudyResultId(long studyResultId) {
        this.studyResultId = studyResultId;
    }
}
