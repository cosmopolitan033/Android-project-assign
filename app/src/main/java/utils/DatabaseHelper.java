package utils;

import models.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studyBuddyDB"; //Database name
    private static final String TABLE_USER = "users"; //User table name
    private static final String TABLE_STUDY_PLAN = "study_plans"; //StudyPlan table name

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT" + ")";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER );
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

}
