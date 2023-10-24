package utils;

import models.Profile;
import models.StudyPlan;
import models.StudyResult;
import models.StudySession;
import models.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studyBuddyDB"; //Database name
    // TODO Table User
    private static final String TABLE_USER = "users"; //User table name
    private static final String TABLE_STUDY_PLAN = "study_plans"; //StudyPlan table name
    private static final String TABLE_STUDY_SESSION = "study_sessions"; // StudySession table name
    private static final String TABLE_STUDY_RESULT = "study_results"; // StudyResult table name

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_PASSWORD = "user_password";

    // TODO Table Profile
    private static final String TABLE_PROFILE = "profiles"; //User table name
    private static final String COLUMN_PROFILE_ID = "user_id";
    private static final String COLUMN_PROFILE_NAME = "profile_name";
    private static final String COLUMN_SUBJECTS = "subjects";
    private static final String COLUMN_AVAILABILITY = "availability";
    private static final String COLUMN_STUDY_PREFERENCE = "study_preference";

    // TODO Table StudyPlan
    private static final String COLUMN_STUDY_PLAN_ID = "study_plan_id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_PARTICIPANTS = "participants";

    // TODO Table StudySession
    private static final String COLUMN_STUDY_SESSION_ID = "study_session_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TOPIC = "topic";

    // TODO Table StudyResult
    private static final String COLUMN_STUDY_RESULT_ID = "study_result_id";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_FEEDBACK = "feedback";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT" + ")";

    private String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "(" + COLUMN_PROFILE_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFILE_NAME + " TEXT, " +
            COLUMN_SUBJECTS + " TEXT," + COLUMN_AVAILABILITY + " TEXT," + COLUMN_STUDY_PREFERENCE + " TEXT" +")";

    private String CREATE_STUDY_PLAN_TABLE = "CREATE TABLE " + TABLE_STUDY_PLAN + "(" + COLUMN_STUDY_PLAN_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUBJECT + " TEXT, " +
            COLUMN_PARTICIPANTS + " TEXT" + ")";

    private String CREATE_STUDY_SESSION_TABLE = "CREATE TABLE " + TABLE_STUDY_SESSION + "(" + COLUMN_STUDY_SESSION_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT, " +
            COLUMN_TOPIC + " TEXT, " + COLUMN_STUDY_RESULT_ID + " INTEGER" + ")";

    private String CREATE_STUDY_RESULT_TABLE = "CREATE TABLE " + TABLE_STUDY_RESULT + "(" + COLUMN_STUDY_RESULT_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTES + " TEXT, " +
            COLUMN_RATING + " INTEGER, " + COLUMN_FEEDBACK + " TEXT" + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_STUDY_PLAN_TABLE);
        db.execSQL(CREATE_STUDY_SESSION_TABLE);
        db.execSQL(CREATE_STUDY_RESULT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDY_PLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDY_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDY_RESULT);
        onCreate(db);
    }

    public long insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME,user.getUserName());
        values.put(COLUMN_PASSWORD,user.getPassword());
        long userId = db.insert(TABLE_USER,null,values);
        db.close();
        return userId;
    }

    public User getUserByUserName(String userName){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                new String[]{COLUMN_USER_ID,COLUMN_USER_NAME,COLUMN_PASSWORD},
                COLUMN_USER_NAME + "=?",
                new String[]{userName},null,null,null);
        if (cursor != null && cursor.moveToNext()){
            User user = new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2) );
            cursor.close();
            return user;
        }

        return null;
    }

    public long insertProfile(long userId, String name, String subjects, String availability, String studyPreference) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId); // add this line
        values.put(COLUMN_PROFILE_NAME, name);
        values.put(COLUMN_SUBJECTS, subjects);
        values.put(COLUMN_AVAILABILITY, availability);
        values.put(COLUMN_STUDY_PREFERENCE, studyPreference);
        long profileId = db.insert(TABLE_PROFILE, null, values);
        db.close();
        return profileId;
    }

    public Profile getProfileyUserId(long userID){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROFILE,
                new String[]{COLUMN_PROFILE_ID,COLUMN_PROFILE_NAME,COLUMN_SUBJECTS,COLUMN_AVAILABILITY,COLUMN_STUDY_PREFERENCE},
                COLUMN_PROFILE_ID + "=?",
                new String[]{String.valueOf(userID)},null,null,null);
        if (cursor != null && cursor.moveToNext()){
            Profile profile = new Profile(cursor.getLong(0), cursor.getString(1), cursor.getString(2)
                ,cursor.getString(3),cursor.getString(4));
            cursor.close();
            return profile;
        }

        return null;
    }

    public boolean profileExists(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILE,
                new String[]{COLUMN_PROFILE_ID},
                COLUMN_USER_ID + "=?",
                new String[]{String.valueOf(userId)}, null, null, null);
        boolean exists = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return exists;
    }

    public int updateProfile(long userId, String name, String subjects,String availability, String studyPreference){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILE_NAME,name);
        values.put(COLUMN_SUBJECTS,subjects);
        values.put(COLUMN_AVAILABILITY,availability);
        values.put(COLUMN_STUDY_PREFERENCE,studyPreference);
        int rows = db.update(TABLE_PROFILE,values,
                COLUMN_PROFILE_ID + "=?",
                new String[]{String.valueOf(userId)});
        db.close();
        return rows;
    }

    public long insertStudyPlan(StudyPlan studyPlan, long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT, studyPlan.getSubject());
        values.put(COLUMN_PARTICIPANTS, studyPlan.getParticipants());
        long studyPlanId = db.insert(TABLE_STUDY_PLAN, null, values);

        // Insert the StudySessions associated with this StudyPlan
        for (StudySession session : studyPlan.getStudySessions()) {
            ContentValues sessionValues = new ContentValues();
            sessionValues.put(COLUMN_STUDY_PLAN_ID, studyPlanId);
            sessionValues.put(COLUMN_DATE, session.getDate());
            sessionValues.put(COLUMN_TOPIC, session.getTopic());
            long sessionId = db.insert(TABLE_STUDY_SESSION, null, sessionValues);

            // Insert the StudyResult associated with this StudySession
            StudyResult result = getStudyResultById(session.getStudyResultId());
            if (result != null) {
                ContentValues resultValues = new ContentValues();
                resultValues.put(COLUMN_STUDY_SESSION_ID, sessionId);
                resultValues.put(COLUMN_NOTES, result.getNotes());
                resultValues.put(COLUMN_RATING, result.getRating());
                resultValues.put(COLUMN_FEEDBACK, result.getFeedback());
                long resultId = db.insert(TABLE_STUDY_RESULT, null, resultValues);

                // Update the StudySession with the result ID
                ContentValues updateSessionValues = new ContentValues();
                updateSessionValues.put(COLUMN_STUDY_RESULT_ID, resultId);
                db.update(TABLE_STUDY_SESSION, updateSessionValues, COLUMN_STUDY_SESSION_ID + "=?", new String[]{String.valueOf(sessionId)});
            }
        }

        db.close();
        return studyPlanId;
    }
    // StudyResult methods
    public long insertStudyResult(StudyResult studyResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTES, studyResult.getNotes());
        values.put(COLUMN_RATING, studyResult.getRating());
        values.put(COLUMN_FEEDBACK, studyResult.getFeedback());
        long studyResultId = db.insert(TABLE_STUDY_RESULT, null, values);
        db.close();
        return studyResultId;
    }

    public StudyResult getStudyResultById(long studyResultId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDY_RESULT,
                new String[]{COLUMN_STUDY_RESULT_ID, COLUMN_NOTES, COLUMN_RATING, COLUMN_FEEDBACK},
                COLUMN_STUDY_RESULT_ID + "=?",
                new String[]{String.valueOf(studyResultId)}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            StudyResult studyResult = new StudyResult(cursor.getLong(0),
                    cursor.getString(1), cursor.getInt(2), cursor.getString(3));
            cursor.close();
            return studyResult;
        }
        return null;
    }

    // StudySession methods
    public long insertStudySession(StudySession studySession) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, studySession.getDate());
        values.put(COLUMN_TOPIC, studySession.getTopic());
        values.put(COLUMN_STUDY_RESULT_ID, studySession.getStudyResultId());
        long studySessionId = db.insert(TABLE_STUDY_SESSION, null, values);
        db.close();
        return studySessionId;
    }

    public StudySession getStudySessionById(long studySessionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDY_SESSION,
                new String[]{COLUMN_STUDY_SESSION_ID, COLUMN_DATE, COLUMN_TOPIC, COLUMN_STUDY_RESULT_ID},
                COLUMN_STUDY_SESSION_ID + "=?",
                new String[]{String.valueOf(studySessionId)}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            StudySession studySession = new StudySession(cursor.getLong(0),
                    cursor.getString(1), cursor.getString(2), cursor.getLong(3));
            cursor.close();
            return studySession;
        }
        return null;
    }

    public List<StudyPlan> getStudyPlans(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<StudyPlan> studyPlans = new ArrayList<>();

        // Query to get all StudyPlans for the given user
        Cursor cursor = db.query(TABLE_STUDY_PLAN,
                new String[]{COLUMN_STUDY_PLAN_ID, COLUMN_SUBJECT, COLUMN_PARTICIPANTS},
                COLUMN_USER_ID + "=?",
                new String[]{String.valueOf(userId)}, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            long studyPlanId = cursor.getLong(0);
            String subject = cursor.getString(1);
            String participants = cursor.getString(2);

            // Get the StudySessions associated with this StudyPlan
            List<StudySession> sessions = new ArrayList<>();
            Cursor sessionCursor = db.query(TABLE_STUDY_SESSION,
                    new String[]{COLUMN_STUDY_SESSION_ID, COLUMN_DATE, COLUMN_TOPIC, COLUMN_STUDY_RESULT_ID},
                    COLUMN_STUDY_PLAN_ID + "=?",
                    new String[]{String.valueOf(studyPlanId)}, null, null, null);
            while (sessionCursor != null && sessionCursor.moveToNext()) {
                long sessionId = sessionCursor.getLong(0);
                String dateString = sessionCursor.getString(1);
                String topic = sessionCursor.getString(2);
                long resultId = sessionCursor.getLong(3);

                // Get the StudyResult associated with this StudySession
                StudyResult result = null;
                Cursor resultCursor = db.query(TABLE_STUDY_RESULT,
                        new String[]{COLUMN_NOTES, COLUMN_RATING, COLUMN_FEEDBACK},
                        COLUMN_STUDY_RESULT_ID + "=?",
                        new String[]{String.valueOf(resultId)}, null, null, null);
                if (resultCursor != null && resultCursor.moveToNext()) {
                    String notes = resultCursor.getString(0);
                    int rating = resultCursor.getInt(1);
                    String feedback = resultCursor.getString(2);
                    result = new StudyResult(notes, rating, feedback);
                    resultCursor.close();
                }

                StudySession session = new StudySession(sessionId, dateString, topic, resultId);
                sessions.add(session);
            }
            if (sessionCursor != null) {
                sessionCursor.close();
            }

            StudyPlan studyPlan = new StudyPlan(studyPlanId, subject, participants, sessions);
            studyPlans.add(studyPlan);
        }
        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return studyPlans;
    }

}
