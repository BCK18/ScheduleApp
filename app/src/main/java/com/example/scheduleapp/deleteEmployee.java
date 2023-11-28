package com.example.scheduleapp;


import static com.example.scheduleapp.DataBaseHelper.EMPLOYEE_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class deleteEmployee extends SQLiteOpenHelper {
    private final Context context;
    public Employee employee;
    public deleteEmployee(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    void deleteEmployee(int employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_STATUS, false);  // false indicates inactive status
        db.update(EMPLOYEE_TABLE, cv, DataBaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(employeeId)});
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();

    }
    void reactive(int employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_STATUS, true);  // false indicates inactive status
        db.update(EMPLOYEE_TABLE, cv, DataBaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(employeeId)});
        Toast.makeText(context, "Active Status", Toast.LENGTH_SHORT).show();
        db.close();

    }

    @Override
   public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}