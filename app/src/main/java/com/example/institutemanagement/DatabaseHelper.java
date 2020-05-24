package com.example.institutemanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    //Declaring Database Name
    private static final String DATABASE_NAME = "institute.db";

    //Declaring table names
    private static final String TBL_LOGIN_CREDENTIALS = "tbl_login_credentials";
    private static final String TBL_COURSES = "tbl_courses";
    private static final String TBL_FACULTY = "tbl_faculty";
    private static final String TBL_STUDENTS = "tbl_students";

    //Instance of SQLiteDatabase
    private SQLiteDatabase db = this.getWritableDatabase();

    DatabaseHelper(@Nullable Context context) {
        //Passing database name variable To Create database whenever constructor is called.
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    boolean registerUser(String username, String password) {
        String USERNAME = "USERNAME";
        String PASSWORD = "PASSWORD";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_LOGIN_CREDENTIALS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        long result = db.insert(TBL_LOGIN_CREDENTIALS, null, values);
        return result != -1;
    }

    String userLogin(String password) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_LOGIN_CREDENTIALS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        //Query to select username and password form table TBL_LOGIN_CREDENTIALS.
        String query = "SELECT USERNAME, PASSWORD FROM " + TBL_LOGIN_CREDENTIALS;
        //USing cursor to store values as the data is being searched in database.
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
        //Declaring temp variables
        String user, pass = "";
        //Checking if cursor moves to first element in database.
        if (cursor.moveToFirst()) {
            do {
                //Storing first element to user variable
                user = cursor.getString(0);
                //Then checking if value of user variable is equals to the password entered by the user.
                if (user.equals(password)) {
                    //If the password entered by the user is found then the loop will break.
                    pass = cursor.getString(1);
                    break;
                }
            }
            //It will go until matching password is not found or there is nothing to query.
            while (cursor.moveToNext());
        }
        return pass;
    }

    boolean addNewCourses(String Id, String courseName, String duration, String fee) {
        String COURSEID = "COURSEID", COURSENAME = "COURSENAME", DURATION = "DURATION", FEE = "FEE";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_COURSES + " (COURSEID INTEGER PRIMARY KEY NOT NULL, COURSENAME TEXT, DURATION INTEGER, FEE INTEGER)");
        ContentValues values = new ContentValues();
        values.put(COURSEID, Id);
        values.put(COURSENAME, courseName);
        values.put(DURATION, duration);
        values.put(FEE, fee);
        long result = db.insert(TBL_COURSES, null, values);
        return result != -1;
    }

    int updateCourses(String courseID, String courseName, String duration, String fee) {
        String COURSEID = "COURSEID", COURSENAME = "COURSENAME", DURATION = "DURATION", FEE = "FEE";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_COURSES + " (COURSEID INTEGER PRIMARY KEY NOT NULL, COURSENAME TEXT, DURATION INTEGER, FEE INTEGER)");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSEID, courseID);
        contentValues.put(COURSENAME, courseName);
        contentValues.put(DURATION, duration);
        contentValues.put(FEE, fee);
        return db.update(TBL_COURSES, contentValues, "COURSEID = ?", new String[]{courseID});
    }

    int deleteCourses(String id) {
        return db.delete(TBL_COURSES, "COURSEID = ? ", new String[]{id});
    }
}
