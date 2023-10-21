package models;

import android.telephony.mbms.MbmsErrors;

public class StudySession {
    private long sessionId;
    private String date;
    private String topic;
    private StudyResult result;

    public StudySession() {
    }
    public StudySession(String date, String topic, StudyResult result) {
        this.date = date;
        this.topic = topic;
        this.result = result;
    }
    public StudySession(long sessionId, String date, String topic, StudyResult result) {
        this.sessionId = sessionId;
        this.date = date;
        this.topic = topic;
        this.result = result;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
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

    public StudyResult getResult() {
        return result;
    }

    public void setResult(StudyResult result) {
        this.result = result;
    }
}
