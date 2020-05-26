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

    //Adding Courses
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

    //Updating Courses
    int updateCourses(String courseID, String courseName, String duration, String fee) {
        String COURSEID = "COURSEID", COURSENAME = "COURSENAME", DURATION = "DURATION", FEE = "FEE";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_COURSES + " (COURSEID INTEGER PRIMARY KEY NOT NULL, COURSENAME TEXT, DURATION INTEGER, FEE INTEGER)");
        ContentValues values = new ContentValues();
        values.put(COURSEID, courseID);
        values.put(COURSENAME, courseName);
        values.put(DURATION, duration);
        values.put(FEE, fee);
        return db.update(TBL_COURSES, values, "COURSEID = ?", new String[]{courseID});
    }

    //Deleting Courses
    int deleteCourses(String courseID) {
        return db.delete(TBL_COURSES, "COURSEID = ? ", new String[]{courseID});
    }

    //Adding new Faculty
    boolean addNewFaculty(String facultyID, String fullName, String fullAddress, String qualification, String experience, String salary, String designation) {
        String FACULTYID = "FACULTYID",
                FULLNAME = "FULLNAME",
                FULLADDRESS = "FULLADDRESS",
                QUALIFICATION = "QUALIFICATION",
                EXPERIENCE = "EXPERIENCE",
                SALARY = "SALARY",
                DESIGNATION = "DESIGNATION";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_FACULTY + " (FACULTYID INTEGER PRIMARY KEY NOT NULL, FULLNAME TEXT, FULLADDRESS TEXT, QUALIFICATION TEXT, EXPERIENCE INTEGER, SALARY INTEGER, DESIGNATION TEXT)");
        ContentValues values = new ContentValues();
        values.put(FACULTYID, facultyID);
        values.put(FULLNAME, fullName);
        values.put(FULLADDRESS, fullAddress);
        values.put(QUALIFICATION, qualification);
        values.put(EXPERIENCE, experience);
        values.put(SALARY, salary);
        values.put(DESIGNATION, designation);
        long result = db.insert(TBL_FACULTY, null, values);
        return result != -1;
    }

    //Update Faculty
    int updateFaculty(String facultyID, String fullName, String fullAddress, String qualification, String experience, String salary, String designation) {
        String FACULTYID = "FACULTYID",
                FULLNAME = "FULLNAME",
                FULLADDRESS = "FULLADDRESS",
                QUALIFICATION = "QUALIFICATION",
                EXPERIENCE = "EXPERIENCE",
                SALARY = "SALARY",
                DESIGNATION = "DESIGNATION";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_FACULTY + " (FACULTYID INTEGER PRIMARY KEY NOT NULL, FULLNAME TEXT, FULLADDRESS TEXT, QUALIFICATION TEXT, EXPERIENCE INTEGER, SALARY INTEGER, DESIGNATION TEXT)");
        ContentValues values = new ContentValues();
        values.put(FACULTYID, facultyID);
        values.put(FULLNAME, fullName);
        values.put(FULLADDRESS, fullAddress);
        values.put(QUALIFICATION, qualification);
        values.put(EXPERIENCE, experience);
        values.put(SALARY, salary);
        values.put(DESIGNATION, designation);
        return db.update(TBL_FACULTY, values, "FACULTYID = ?", new String[]{facultyID});
    }

    //Delete Faculty
    int deleteFaculty(String facultyID) {
        return db.delete(TBL_FACULTY, "FACULTYID = ? ", new String[]{facultyID});
    }

    //Adding new Students
    boolean addNewStudent(String studentId, String rollNo, String trade, String semester, String fullName, String fatherName, String fullAddress, String contactNo) {
        String STUDENTID = "STUDENTID",
                ROLLNO = "ROLLNO",
                TRADE = "TRADE",
                SEMESTER = "SEMESTER",
                FULLNAME = "FULLNAME",
                FATHERNAME = "FATHERNAME",
                FULLADDRESS = "FULLADDRESS",
                CONTACTNO = "CONTACTNO";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_STUDENTS + " (STUDENTID INTEGER PRIMARY KEY NOT NULL, ROLLNO INTEGER, TRADE TEXT, SEMESTER TEXT,FULLNAME TEXT, FATHERNAME TEXT, FULLADDRESS TEXT, CONTACTNO INTEGER)");
        ContentValues values = new ContentValues();
        values.put(STUDENTID, studentId);
        values.put(ROLLNO, rollNo);
        values.put(TRADE, trade);
        values.put(SEMESTER, semester);
        values.put(FULLNAME, fullName);
        values.put(FATHERNAME, fatherName);
        values.put(FULLADDRESS, fullAddress);
        values.put(CONTACTNO, contactNo);
        long result = db.insert(TBL_STUDENTS, null, values);
        return result != -1;
    }

    //Update Faculty
    int updateStudent(String studentId, String rollNo, String trade, String semester, String fullName, String fatherName, String fullAddress, String contactNo) {
        String STUDENTID = "STUDENTID",
                ROLLNO = "ROLLNO",
                TRADE = "TRADE",
                SEMESTER = "SEMESTER",
                FULLNAME = "FULLNAME",
                FATHERNAME = "FATHERNAME",
                FULLADDRESS = "FULLADDRESS",
                CONTACTNO = "CONTACTNO";
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_STUDENTS + " (STUDENTID INTEGER PRIMARY KEY NOT NULL, ROLLNO INTEGER, TRADE TEXT, SEMESTER TEXT,FULLNAME TEXT, FATHERNAME TEXT, FULLADDRESS TEXT, CONTACTNO INTEGER)");
        ContentValues values = new ContentValues();
        values.put(STUDENTID, studentId);
        values.put(ROLLNO, rollNo);
        values.put(TRADE, trade);
        values.put(SEMESTER, semester);
        values.put(FULLNAME, fullName);
        values.put(FATHERNAME, fatherName);
        values.put(FULLADDRESS, fullAddress);
        values.put(CONTACTNO, contactNo);
        return db.update(TBL_STUDENTS, values, "STUDENTID = ? AND TRADE = ? AND SEMESTER = ?", new String[]{studentId, trade, semester});
    }

    //Delete Students
    int deleteStudents(String studentId, String trade, String semester) {
        return db.delete(TBL_STUDENTS, "STUDENTID = ? AND TRADE = ? AND SEMESTER = ?", new String[]{studentId, trade, semester});
    }
}
