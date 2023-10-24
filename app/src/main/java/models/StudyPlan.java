package models;

import java.util.List;

public class StudyPlan {
    private long studyPlanId;
    private String subject;
    private String participants;
    private List<StudySession> studySessions;  // Added a list to store StudySession objects

    public StudyPlan(String subject, String participants, List<StudySession> studySessions) {
        this.subject = subject;
        this.participants = participants;
        this.studySessions = studySessions;
    }

    public StudyPlan(String subject, String participants) {
        this.subject = subject;
        this.participants = participants;
    }

    public StudyPlan(long studyPlanId, String subject, String participants, List<StudySession> studySessions) {
        this.studyPlanId = studyPlanId;
        this.subject = subject;
        this.participants = participants;
        this.studySessions = studySessions;
    }

    // Getter and setter methods for all attributes

    public long getStudyPlanId() {
        return studyPlanId;
    }

    public void setStudyPlanId(long studyPlanId) {
        this.studyPlanId = studyPlanId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public List<StudySession> getStudySessions() {
        return studySessions;
    }

    public void setStudySessions(List<StudySession> studySessions) {
        this.studySessions = studySessions;
    }
}
