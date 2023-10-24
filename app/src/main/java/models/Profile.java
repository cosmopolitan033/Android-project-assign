package models;

public class Profile {
    private long userID;
    private String name;
    private String subjects;
    private String availability;
    private String studyPreference;

    public Profile(String name, String subjects, String availability, String studyPreference) {
        this.name = name;
        this.subjects = subjects;
        this.availability = availability;
        this.studyPreference = studyPreference;
    }

    public Profile(long userID, String name, String subjects, String availability, String studyPreference) {
        this.userID = userID;
        this.name = name;
        this.subjects = subjects;
        this.availability = availability;
        this.studyPreference = studyPreference;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String  subjects) {
        this.subjects = subjects;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getStudyPreference() {
        return studyPreference;
    }

    public void setStudyPreference(String studyPreference) {
        this.studyPreference = studyPreference;
    }
}
