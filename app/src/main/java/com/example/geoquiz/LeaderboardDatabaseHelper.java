package com.example.geoquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LeaderboardDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "leaderboardDB.db";
    private static final int DB_VERSION = 3;
    private static final String DB_CREATE = "CREATE TABLE LEADERBOARD(_id integer primary key autoincrement, name text not null, time double not null, score integer not null);";

    public LeaderboardDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LEADERBOARD");
        onCreate(db);
    }
}
