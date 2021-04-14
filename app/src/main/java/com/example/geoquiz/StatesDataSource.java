package com.example.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StatesDataSource {
    private SQLiteDatabase database;
    private StatesDatabaseHelper dbHelper;
    private String[] allColumns = {};

    public StatesDataSource(Context context) {
        dbHelper = new StatesDatabaseHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public SQLiteDatabase getDatabase() {
        return database;
    }
    public void insertState(String name, String description, String imageLink) {
        ContentValues stateValues = new ContentValues();
        stateValues.put("name",name);
        stateValues.put("description",description);
        stateValues.put("imageLink",imageLink);
        database.insert("STATES",null,stateValues);
        System.out.println(description);
    }
    public Cursor getCursor() {
        Cursor cursor = database.rawQuery("SELECT * FROM STATES",null);
        return cursor;
    }
}
