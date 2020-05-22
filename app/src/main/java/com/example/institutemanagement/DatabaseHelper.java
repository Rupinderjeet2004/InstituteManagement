package com.example.institutemanagement;

import android.content.Context;
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

    public DatabaseHelper(@Nullable Context context) {
        //Passing database name variable To Create database whenever constructor is called.
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean registerUser() {

        db.execSQL("create table if not exists " + TBL_LOGIN_CREDENTIALS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        return false;
    }
}
