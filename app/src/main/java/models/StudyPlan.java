package models;

import android.se.omapi.Session;

public class StudyPlan {
    private long planId;
    private User participant;
    private Session[] sessions;
    private String subject;

    public StudyPlan() {
    }

    public StudyPlan(User participant, Session[] sessions, String subject) {
        this.participant = participant;
        this.sessions = sessions;
        this.subject = subject;
    }

    public StudyPlan(long planId, User participant, Session[] sessions, String subject) {
        this.planId = planId;
        this.participant = participant;
        this.sessions = sessions;
        this.subject = subject;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        this.participant = participant;
    }

    public Session[] getSessions() {
        return sessions;
    }

    public void setSessions(Session[] sessions) {
        this.sessions = sessions;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
