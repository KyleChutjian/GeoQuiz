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
        insertAllStates();
    }
    public void close() {
        dbHelper.close();
    }
    public SQLiteDatabase getDatabase() {
        return database;
    }
    public Cursor getCursor() {
        Cursor cursor = database.rawQuery("SELECT * FROM STATES",null);
        return cursor;
    }

    public void insertState(String name, String description, String imageLink) {
        ContentValues stateValues = new ContentValues();
        stateValues.put("name",name);
        stateValues.put("description",description);
        stateValues.put("imageLink",imageLink);
        database.insert("STATES",null,stateValues);
    }
    public void insertAllStates() {
        insertState("Delaware", "Fun fact about Delaware","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/delaware-map-outline-dddddd.png");
        insertState("Pennsylvania", "Fun fact about Pennsylvania","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/pennsylvania-map-outline-dddddd.png");
        insertState("NewJersey","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-jersey-map-outline-dddddd.png");
        insertState("Georgia","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/georgia-map-outline-dddddd.png");
        insertState("Connecticut","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/connecticut-map-outline-dddddd.png");
        insertState("Massachusetts","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/massachusetts-map-outline-dddddd.png");
        insertState("Maryland","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/maryland-map-outline-dddddd.png");
        insertState("SouthCarolina","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/south-carolina-map-outline-dddddd.png");
        insertState("NewHampshire","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-hampshire-map-outline-dddddd.png");
        insertState("Virginia","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/virginia-map-outline-dddddd.png");
        insertState("NewYork","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-york-map-outline-dddddd.png");
        insertState("NorthCarolina","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/north-carolina-map-outline-dddddd.png");
        insertState("RhodeIsland","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/rhode-island-map-outline-dddddd.png");
        insertState("Vermont","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/vermont-map-outline-dddddd.png");
        insertState("Kentucky","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/kentucky-map-outline-dddddd.png");
        insertState("Tennessee","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/tennessee-map-outline-dddddd.png");
        insertState("Ohio","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/ohio-map-outline-dddddd.png");
        insertState("Louisiana","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/louisiana-map-outline-dddddd.png");
        insertState("Indiana","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/indiana-map-outline-dddddd.png");
        insertState("Mississippi","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/mississippi-map-outline-dddddd.png");
        insertState("Illinois","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/illinois-map-outline-dddddd.png");
        insertState("Alabama","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/alabama-map-outline-dddddd.png");
        insertState("Maine","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/maine-map-outline-dddddd.png");
        insertState("Missouri","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/missouri-map-outline-dddddd.png");
        insertState("Arkansas","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/arkansas-map-outline-dddddd.png");
        insertState("Michigan","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/michigan-map-outline-dddddd.png");
        insertState("Florida","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/florida-map-outline-dddddd.png");
        insertState("Texas","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/texas-map-outline-dddddd.png");
        insertState("Iowa","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/iowa-map-outline-dddddd.png");
        insertState("Wisconsin","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/wisconsin-map-outline-dddddd.png");
        insertState("California","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/california-map-outline-dddddd.png");
        insertState("Minnesota","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/minnesota-map-outline-dddddd.png");
        insertState("Oregon","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/oregon-map-outline-dddddd.png");
        insertState("Kansas","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/kansas-map-outline-dddddd.png");
        insertState("WestVirginia","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/west-virginia-map-outline-dddddd.png");
        insertState("Nevada","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/nevada-map-outline-dddddd.png");
        insertState("Nebraska","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/nebraska-map-outline-dddddd.png");
        insertState("Colorado","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/colorado-map-outline-dddddd.png");
        insertState("NorthDakota","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/north-dakota-map-outline-dddddd.png");
        insertState("SouthDakota","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/south-dakota-map-outline-dddddd.png");
        insertState("Montana","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/montana-map-outline-dddddd.png");
        insertState("Washington","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/washington-map-outline-dddddd.png");
        insertState("Idaho","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/idaho-map-outline-dddddd.png");
        insertState("Wyoming","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/wyoming-map-outline-dddddd.png");
        insertState("Utah","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/utah-map-outline-dddddd.png");
        insertState("Oklahoma","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/oklahoma-map-outline-dddddd.png");
        insertState("NewMexico","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-mexico-map-outline-dddddd.png");
        insertState("Arizona","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/arizona-map-outline-dddddd.png");
        insertState("Alaska","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/alaska-map-outline-dddddd.png");
        insertState("Hawaii","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/hawaii-map-outline-dddddd.png");
        //dataSource.insertState("","","");
    }
}
