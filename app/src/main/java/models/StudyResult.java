package models;

public class StudyResult {
    private long studyResultId;
    private String notes;
    private int rating;
    private String feedback;

    public StudyResult(String notes, int rating, String feedback) {
        this.notes = notes;
        this.rating = rating;
        this.feedback = feedback;
    }

    public StudyResult(long studyResultId, String notes, int rating, String feedback) {
        this.studyResultId = studyResultId;
        this.notes = notes;
        this.rating = rating;
        this.feedback = feedback;
    }

    public long getStudyResultId() {
        return studyResultId;
    }

    public void setStudyResultId(long studyResultId) {
        this.studyResultId = studyResultId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
