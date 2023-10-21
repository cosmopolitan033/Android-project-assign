package models;

public class Profile {
    private long profileID;
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

    public Profile(long profileID, String name, String subjects, String availability, String studyPreference) {
        this.profileID = profileID;
        this.name = name;
        this.subjects = subjects;
        this.availability = availability;
        this.studyPreference = studyPreference;
    }

    public long getProfileID() {
        return profileID;
    }

    public void setProfileID(long profileID) {
        this.profileID = profileID;
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
