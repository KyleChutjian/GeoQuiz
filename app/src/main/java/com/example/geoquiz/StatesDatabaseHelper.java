package com.example.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatesDatabaseHelper extends SQLiteOpenHelper {

    private static final String STATE_ID = "_id";
    private static final String STATE_NAME = "name";
    private static final String STATE_DESCRIPTION = "description";
    private static final String STATE_IMAGELINK = "imageLink";

    private static final String DB_NAME = "statesDB.db";
    private static final int DB_VERSION = 1;

    private static final String DB_CREATE = "CREATE TABLE STATES(_id integer primary key autoincrement, name text not null, description text not null, imageLink text not null);";

    public StatesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        System.out.println("Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }


    private void insertState(SQLiteDatabase db, String state, String description, String imageLink) {
        ContentValues stateValues = new ContentValues();
        stateValues.put("STATE",state);
        stateValues.put("DESCRIPTION",description);
        stateValues.put("IMAGELINK",imageLink);
        db.insert("STATES",null,stateValues);
        System.out.println(description);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STATES");
        onCreate(db);
    }
}
