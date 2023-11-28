package com.example.scheduleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String EMPLOYEE_TABLE = "EMPLOYEE_TABLE";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_FIRST_NAME = "COLUMN_FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "COLUMN_LAST_NAME";
    public static final String COLUMN_PHONE_NUMBER = "COLUMN_PHONE_NUMBER";
    public static final String COLUMN_EMAIL = "COLUMN_EMAIL";
    public static final String COLUMN_OPENING = "COLUMN_OPENING";
    public static final String COLUMN_CLOSING = "COLUMN_CLOSING";
    public static final String COLUMN_MORNINGS = "COLUMN_MORNINGS";
    public static final String COLUMN_AFTERNOONS = "COLUMN_AFTERNOONS";
    public static final String COLUMN_EVENINGS = "COLUMN_EVENINGS";
    public static final String COLUMN_WEEKENDS = "COLUMN_WEEKENDS";

    public static final String COLUMN_STATUS = "COLUMN_STATUS";
    public static final String PASSWORD_TABLE = "PASSWORD_TABLE";
    public static final String COLUMN_PASSWORD = "COLUMN_PASSWORD";
    private final Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "SchedulingApp.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createEmployeeTableStatement = "CREATE TABLE " + EMPLOYEE_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_OPENING + " BOOL, " +
                COLUMN_CLOSING + " BOOL, " +
                COLUMN_MORNINGS + " BOOL, " +
                COLUMN_AFTERNOONS + " BOOL, " +
                COLUMN_EVENINGS + " BOOL, " +
                COLUMN_WEEKENDS + " BOOL," +
                COLUMN_STATUS + " BOOL)";

        db.execSQL(createEmployeeTableStatement);

        String createPasswordTableStatement = "CREATE TABLE " + PASSWORD_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PASSWORD + " TEXT)";

        db.execSQL(createPasswordTableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE  " + EMPLOYEE_TABLE);
            onCreate(db);
    }

    public boolean addOne(Employee employee){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NAME, employee.getFirstName());
        cv.put(COLUMN_LAST_NAME, employee.getLastName());
        cv.put(COLUMN_PHONE_NUMBER, employee.getPhoneNumber());
        cv.put(COLUMN_EMAIL, employee.getEmail());
        cv.put(COLUMN_OPENING, employee.getOpening());
        cv.put(COLUMN_CLOSING, employee.getClosing());
        cv.put(COLUMN_MORNINGS, employee.getMornings());
        cv.put(COLUMN_AFTERNOONS, employee.getAfternoons());
        cv.put(COLUMN_EVENINGS, employee.getEvenings());
        cv.put(COLUMN_WEEKENDS, employee.getWeekends());
        cv.put(COLUMN_STATUS, employee.getStatus());



        long insert = db.insert(EMPLOYEE_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Employee> getEveryone(int num) {

        ArrayList<Employee> returnList = new ArrayList<>();

        // get data from the database.
        String queryString=null;
        //Query for active employees
        switch (num) {

            case 1:
                queryString = "SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + COLUMN_STATUS + " =1";
                break;

            case 2:
                // Query for inactive employees
                queryString = "SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + COLUMN_STATUS + " =0";
                break;
        }

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                // loop through the cursor (result set) and create new customer objects. Put them into the return list.
                do {
                    int id = cursor.getInt(0);
                    String first_Name = cursor.getString(1);
                    String last_Name = cursor.getString(2);
                    String phone_Number = cursor.getString(3);
                    String email = cursor.getString(4);
                    boolean opening = cursor.getInt(5) == 1 ? true : false;
                    boolean closing = cursor.getInt(6) == 1 ? true : false;
                    boolean mornings = cursor.getInt(7) == 1 ? true : false;
                    boolean afternoons = cursor.getInt(8) == 1 ? true : false;
                    boolean evenings = cursor.getInt(9) == 1 ? true : false;
                    boolean weekends = cursor.getInt(10) == 1 ? true : false;
                    boolean status = cursor.getInt(11) == 1 ? true : false;

                    Employee newEmployee = new Employee(id, first_Name, last_Name, phone_Number, email,
                            opening, closing, mornings, afternoons, evenings, weekends, status);
                    returnList.add(newEmployee);

                } while (cursor.moveToNext());
            } else {
                // failure. do not add anything to the list.
            }

            // close both the cursor and the database.
            cursor.close();
            db.close();
        }
        return returnList;
    }


    void updateData(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Convert employee.id to a string
        String row_id = String.valueOf(employee.id);

        cv.put(COLUMN_FIRST_NAME, employee.first_Name);
        cv.put(COLUMN_LAST_NAME, employee.last_Name);
        cv.put(COLUMN_PHONE_NUMBER, employee.phone_Number);
        cv.put(COLUMN_EMAIL, employee.email);
        cv.put(COLUMN_OPENING, employee.opening);
        cv.put(COLUMN_CLOSING, employee.closing);
        cv.put(COLUMN_MORNINGS, employee.mornings);
        cv.put(COLUMN_AFTERNOONS, employee.afternoons);
        cv.put(COLUMN_EVENINGS, employee.evenings);
        cv.put(COLUMN_WEEKENDS, employee.weekends);
        cv.put(COLUMN_STATUS,employee.status);

        long result = db.update(EMPLOYEE_TABLE, cv, COLUMN_ID+"=?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean addPassword(String newPassword) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PASSWORD, newPassword);


        long insert = db.insert(PASSWORD_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<String> getPassword() {

        ArrayList<String> passwords = new ArrayList<String>();

        String queryString = "SELECT * FROM " + PASSWORD_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                // loop through the cursor (result set) and create new customer objects. Put them into the return list.
                do {
                    String storedPassword = cursor.getString(1);

                    passwords.add(storedPassword);

                } while (cursor.moveToNext());
            } else {
                // failure. do not add anything to the list.
            }
        }

        cursor.close();
        db.close();

        return passwords;
    }
}