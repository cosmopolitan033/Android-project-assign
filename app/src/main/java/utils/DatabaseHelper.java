package utils;

import models.Profile;
import models.StudyPlan;
import models.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studyBuddyDB"; //Database name
    // TODO Table User
    private static final String TABLE_USER = "users"; //User table name
    private static final String TABLE_STUDY_PLAN = "study_plans"; //StudyPlan table name

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_PASSWORD = "user_password";

    // TODO Table Profile
    private static final String TABLE_PROFILE = "profiles"; //User table name
    private static final String COLUMN_PROFILE_ID = "profile_id";
    private static final String COLUMN_PROFILE_NAME = "profile_name";
    private static final String COLUMN_SUBJECTS = "subjects";
    private static final String COLUMN_AVAILABILITY = "availability";
    private static final String COLUMN_STUDY_PREFERENCE = "study_preference";


    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT" + ")";
    private String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "(" + COLUMN_PROFILE_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFILE_NAME + " TEXT, " +
            COLUMN_SUBJECTS + " TEXT," + COLUMN_AVAILABILITY + " TEXT," + COLUMN_STUDY_PREFERENCE + " TEXT" +")";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.CREATE_USER_TABLE = CREATE_USER_TABLE;
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE );
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

    public long insertProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILE_NAME,profile.getName());
        values.put(COLUMN_SUBJECTS,profile.getSubjects());
        values.put(COLUMN_AVAILABILITY,profile.getAvailability());
        values.put(COLUMN_STUDY_PREFERENCE,profile.getStudyPreference());
        long profileId = db.insert(TABLE_PROFILE,null,values);
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
    //todo
    public List<StudyPlan> getAllStudyPlans(long userId){
        return new ArrayList<>();
    }


}
