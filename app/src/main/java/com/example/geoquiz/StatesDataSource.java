package com.example.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StatesDataSource {
    private SQLiteDatabase database;
    private StatesDatabaseHelper dbHelper;

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
        insertState("Delaware", "Delaware (DE) was the first state to ratify the Constitution! It also has the lowest altitude compared to any other state.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/delaware-map-outline-dddddd.png");
        insertState("Pennsylvania", "Pennsylvania’s (PA) largest city is Philadelphia, which was the original capital of the United States from September 5th, 1774 to October 24, 1774.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/pennsylvania-map-outline-dddddd.png");
        insertState("New Jersey","While New Jersey (NJ) doesn’t have the highest total population, it has the highest population per square foot.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-jersey-map-outline-dddddd.png");
        insertState("Georgia","Georgia (GA) is the largest state east of the Mississippi River.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/georgia-map-outline-dddddd.png");
        insertState("Connecticut","Connecticut (CT) is home to the first hamburger, Polaroid camera, helicopter, and color television.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/connecticut-map-outline-dddddd.png");
        insertState("Massachusetts","Massachusetts (MA) holds the two largest cities in New England, Boston, the largest, and Worcester.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/massachusetts-map-outline-dddddd.png");
        insertState("Maryland","Maryland (MD) is home to the second longest continuous truss bridge in the nation; the 1,200ft Francis Scott Key Bridge in Baltimore.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/maryland-map-outline-dddddd.png");
        insertState("South Carolina","South Carolina (SC) is home to the first battle of the Civil War.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/south-carolina-map-outline-dddddd.png");
        insertState("New Hampshire","New Hampshire (NH) was the first state to have its own constitution.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-hampshire-map-outline-dddddd.png");
        insertState("Virginia","Virginia (VA) has the highest vanity license plates per capita of any state. Around 16% of its population owns one.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/virginia-map-outline-dddddd.png");
        insertState("New York","New York is the third most populous state in the United States after California and Texas. Of its population of 19 million, a little over 8 million live in New York City alone.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-york-map-outline-dddddd.png");
        insertState("North Carolina","North Carolina (NC) is the birthplace of the world famous donut shop Krispy Kreme. The first shop was created in Winston-Salem.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/north-carolina-map-outline-dddddd.png");
        insertState("Rhode Island","Rhode Island (RI), Despite the name, is not an island. It is composed of many of them. It is the smallest state in the USA but is the second most densely populated, after New Jersey.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/rhode-island-map-outline-dddddd.png");
        insertState("Vermont","Vermont (VT) was the first state admitted to the Union after the ratification of the Constitution.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/vermont-map-outline-dddddd.png");
        insertState("Kentucky","Kentucky (KY) is home to the largest underground cave in the world: 300 miles long, the Mammoth-Flint Cave system.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/kentucky-map-outline-dddddd.png");
        insertState("Tennessee","Tennessee (TN) hosts the longest continuously running live radio program in the world; broadcast every weekend since 1925.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/tennessee-map-outline-dddddd.png");
        insertState("Ohio","Ohio (OH) is the only state with a burgee-shaped (like a pennant with a triangle missing at the end) flag.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/ohio-map-outline-dddddd.png");
        insertState("Louisiana","Louisiana (LA), thanks to its French heritage, is the only state in the country to adhere to a civil law system, as opposed to the common law used in the other 49 states.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/louisiana-map-outline-dddddd.png");
        insertState("Indiana","Indiana (IN) is the “Crossroads of America”, as it has more miles of interstate per sq mile than any other US state. ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/indiana-map-outline-dddddd.png");
        insertState("Mississippi","Mississippi (MS) is named after the Mississippi River. It is home to the world’s largest shrimp.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/mississippi-map-outline-dddddd.png");
        insertState("Illinois","Illinois (IL) is home to the tallest building in the USA; The Sears Tower in Chicago.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/illinois-map-outline-dddddd.png");
        insertState("Alabama","Alabama (AL) is home to the largest cast-iron statue in the world. The statue of Vulcan in Birmingham stands 56ft tall.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/alabama-map-outline-dddddd.png");
        insertState("Maine","Maine (ME) is the only state that shares its borders with only one other US state. It is also the birthplace of Donut holes.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/maine-map-outline-dddddd.png");
        insertState("Missouri","Missouri (MO) is home to the deadliest tornado in US History; the Tri-State tornado of March 18, 1925.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/missouri-map-outline-dddddd.png");
        insertState("Arkansas","Arkansas (AR) is Home to America's only operational diamond mine – Crater Diamonds State Park","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/arkansas-map-outline-dddddd.png");
        insertState("Michigan","Michigan (MI) boasts the most lighthouses of any US State - with 115+ lighthouses along the Great Lakes.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/michigan-map-outline-dddddd.png");
        insertState("Florida","Florida (FL) is the only place in the world where alligators and crocodiles coexist - in Everglades National Park.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/florida-map-outline-dddddd.png");
        insertState("Texas","Texas (TX) is larger than any country in Europe and is also the second largest state in the US. It was once it’s own country.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/texas-map-outline-dddddd.png");
        insertState("Iowa","Iowa (IA) is home to The shortest and steepest railroad in the U.S., Dubuque: 60 incline, 296 feet.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/iowa-map-outline-dddddd.png");
        insertState("Wisconsin","Wisconsin (WI) is where the typewriter was invented; in Milwaukee in 1867.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/wisconsin-map-outline-dddddd.png");
        insertState("California","California (CA) is home to the world's biggest tree by volume, a sequoia named General Sherman, in Sequoia National Park.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/california-map-outline-dddddd.png");
        insertState("Minnesota","Minnesota (MN) is home to the oldest rock in the world; 3.8 billion years old, found in Minnesota River valley.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/minnesota-map-outline-dddddd.png");
        insertState("Oregon","Oregon (OR) is home to the world’s largest living organism, a fungal colony in the state’s Blue Mountains.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/oregon-map-outline-dddddd.png");
        insertState("Kansas","In Kansas (KS) it was once illegal to put a scoop of ice cream on cherry pie. This is also, famously, Dorothy’s home state in The Wizard of Oz.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/kansas-map-outline-dddddd.png");
        insertState("West Virginia","West Virginia (WV) is the only state to be admitted under presidential proclamation. ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/west-virginia-map-outline-dddddd.png");
        insertState("Nevada","Nevada (NV) may be the nuclear capital of the country, with 928 nuclear tests conducted at the Nevada Test Site between 1951 and 1992 (just over 60 miles from Las Vegas).","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/nevada-map-outline-dddddd.png");
        insertState("Nebraska","Nebraska (NE) is the birthplace of Kool-Aid; invented by Edwin Perkins of Hastings back in 1927.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/nebraska-map-outline-dddddd.png");
        insertState("Colorado","Colorado (CO) has the world’s largest flat top mountain, deepest hot springs, and has around 75% of the USA’s land mass that is above 10,000ft.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/colorado-map-outline-dddddd.png");
        insertState("North Dakota","North Dakota (ND) is the number one producer of honey in the USA.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/north-dakota-map-outline-dddddd.png");
        insertState("South Dakota","South Dakota (SD) is home to Mount Rushmore - a mountain that has the faces of George Washington, Thomas Jefferson, Theodore Roosevelt and Abraham Lincoln carved into it.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/south-dakota-map-outline-dddddd.png");
        insertState("Montana","Montana (MT) holds the world record for the greatest temperature change in 24 hours. On January 14-15, 1972, the temperature went from -54 degrees fahrenheit to 49 degrees in Loma, MT; a 103 degree difference.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/montana-map-outline-dddddd.png");
        insertState("Washington","Washington (WA) has more glaciers than the other 47 contiguous states combined.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/washington-map-outline-dddddd.png");
        insertState("Idaho","Idaho (ID) is the heartland of potatoes in the USA - 13 billion pounds are harvested yearly in this state.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/idaho-map-outline-dddddd.png");
        insertState("Wyoming","Wyoming (WY) is the least populated state but is the 10th largest state by Area. It is home to Yellowstone National Park - where the world’s largest supervolcano resides.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/wyoming-map-outline-dddddd.png");
        insertState("Utah","Utah (UT) is home to Rainbow Bridge, the largest natural stone bridge in the world, 290 feet high, 275 feet across.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/utah-map-outline-dddddd.png");
        insertState("Oklahoma","Oklahoma’s (OK) official state poem is “Howdy Folks,” an ode to Oklahoma cowboy Will Rogers by David Randolph Milsten. ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/oklahoma-map-outline-dddddd.png");
        insertState("New Mexico","New Mexico’s (NM) state capital, Santa Fe, is the highest capital in the country—sitting at 7,000 square feet above sea level.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-mexico-map-outline-dddddd.png");
        insertState("Arizona","In Arizona (AZ), cutting down a Cactus can get you thrown into jail.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/arizona-map-outline-dddddd.png");
        insertState("Alaska","Not only is Alaska (AK) the largest state in the country (more than twice as large as Texas), Wrangell–St Elias National Park & Preserve covers a larger area than nine US states.","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/alaska-map-outline-dddddd.png");
        insertState("Hawaii","Hawaii (HI) has the most isolated large population center on Earth, almost 2,400 miles from California and about 4,000 miles from Japan. ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/hawaii-map-outline-dddddd.png");
    }

    public String[] queryWithImageLink(String link) {
        Cursor cursor = database.rawQuery("SELECT name,description FROM STATES WHERE imageLink LIKE '" + link + "%';" ,null);
        String[] returnString = null;
        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                returnString = new String[]{cursor.getString(0), cursor.getString(1)};
            }
        }
        return returnString;
    }
}
