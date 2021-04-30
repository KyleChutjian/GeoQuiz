package com.example.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LeaderboardDataSource {
    private SQLiteDatabase database;
    private LeaderboardDatabaseHelper dbHelper;

    public LeaderboardDataSource(Context context) {
        dbHelper = new LeaderboardDatabaseHelper(context);
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

    public Cursor getCursor() {
        Cursor cursor = database.rawQuery("SELECT * FROM LEADERBOARD",null);
        return cursor;
    }

    public Cursor queryTopThree() {
        Cursor cursor = database.rawQuery("SELECT name,time,score FROM LEADERBOARD ORDER BY score DESC, time ASC",null);
        return cursor;
    }

    public void insertEntry(String name, double time, int score) {
        ContentValues leaderboardValues = new ContentValues();
        leaderboardValues.put("name",name);
        leaderboardValues.put("time",time);
        leaderboardValues.put("score",score);
        database.insert("LEADERBOARD",null,leaderboardValues);
    }
}
