package models;

public class StudyResult {
    private long resultId;
    private String notes;
    private int rating;
    private String feedback;

    public StudyResult() {
    }

    public StudyResult(String notes, int rating, String feedback) {
        this.notes = notes;
        this.rating = rating;
        this.feedback = feedback;
    }

    public StudyResult(long resultId, String notes, int rating, String feedback) {
        this.resultId = resultId;
        this.notes = notes;
        this.rating = rating;
        this.feedback = feedback;
    }

    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
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
