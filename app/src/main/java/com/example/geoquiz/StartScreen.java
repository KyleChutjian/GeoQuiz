package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class StartScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavController navController;
    private NavigationView navigationView;
    private Cursor cursor;
    public int currentState = 0;
    private ArrayList<Integer> possibleStates;
    private StatesDataSource dataSource;
    public int currentQuestion = 1;

    private String currentStateName = null;
    private String currentStateDescription = null;
    private String currentStateImageLink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        navigationView = findViewById(R.id.navigationView);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);

        dataSource = new StatesDataSource(this);
        dataSource.open();
        insertAllStates();
        SQLiteOpenHelper statesDatabaseHelper = new StatesDatabaseHelper(this);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,drawerLayout);
    }

    public void onClick(View view) {
        Button currentButton = (Button) view.findViewById(view.getId());
        System.out.println(currentButton.getText());
        cursor = dataSource.getCursor();
        cursor.moveToFirst();
        String name = cursor.getString(1);

//        ImageView imageView = (ImageView)view.findViewById(R.id.StateImage);
//        System.out.println("--------------" + imageView.getTag());
//        if (currentButton.getText().toString().equalsIgnoreCase("")) {
//
//        }
    }

    public void setupDatabase(View view) {
        try {
            cursor = dataSource.getCursor();
            if (cursor.moveToFirst()) {
                for (int i = 1; i < possibleStates.get(0); i++) {
                    cursor.moveToNext();
                }


                int currentId = cursor.getInt(0);
                currentStateName = cursor.getString(1);
                currentStateDescription = cursor.getString(2);
                currentStateImageLink = cursor.getString(3);
                System.out.println(currentStateImageLink);
                System.out.println(currentId + "|" + currentStateName + "|" + currentStateDescription + "|" + currentStateImageLink);
                ImageView imageView = view.findViewById(R.id.StateImage);

            }
        } catch (SQLException e) {
            System.out.println("Database didnt load");
        }

    }


    private void insertAllStates() {
        dataSource.insertState("Delaware", "Fun fact about Delaware","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/delaware-map-outline-dddddd.png");
        dataSource.insertState("Pennsylvania", "Fun fact about Pennsylvania","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/pennsylvania-map-outline-dddddd.png");
        dataSource.insertState("New Jersey","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/pennsylvania-map-outline-dddddd.png");
        dataSource.insertState("Georgia","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/georgia-map-outline-dddddd.png");
        dataSource.insertState("Connecticut","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/connecticut-map-outline-dddddd.png");
        dataSource.insertState("Massachusetts","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/massachusetts-map-outline-dddddd.png");
        dataSource.insertState("Maryland","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/maryland-map-outline-dddddd.png");
        dataSource.insertState("SouthCarolina","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/south-carolina-map-outline-dddddd.png");
        dataSource.insertState("New Hampshire","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-hampshire-map-outline-dddddd.png");
        dataSource.insertState("Virginia","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/virginia-map-outline-dddddd.png");
        dataSource.insertState("New York","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-york-map-outline-dddddd.png");
        dataSource.insertState("NorthCarolina","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/north-carolina-map-outline-dddddd.png");
        dataSource.insertState("RhodeIsland","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/rhode-island-map-outline-dddddd.png");
        dataSource.insertState("Vermont","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/vermont-map-outline-dddddd.png");
        dataSource.insertState("Kentucky","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/vermont-map-outline-dddddd.png");
        dataSource.insertState("Tennessee","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/kentucky-map-outline-dddddd.png");
        dataSource.insertState("Ohio","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/tennessee-map-outline-dddddd.png");
        dataSource.insertState("Louisiana","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/ohio-map-outline-dddddd.png");
        dataSource.insertState("Indiana","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/louisiana-map-outline-dddddd.png");
        dataSource.insertState("Mississippi","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/indiana-map-outline-dddddd.png");
        dataSource.insertState("Illinois","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/mississippi-map-outline-dddddd.png");
        dataSource.insertState("Alabama","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/illinois-map-outline-dddddd.png");
        dataSource.insertState("Maine","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/alabama-map-outline-dddddd.png");
        dataSource.insertState("Missouri","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/maine-map-outline-dddddd.png");
        dataSource.insertState("Arkansas","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/missouri-map-outline-dddddd.png");
        dataSource.insertState("Michigan","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/michigan-map-outline-dddddd.png");
        dataSource.insertState("Florida","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/florida-map-outline-dddddd.png");
        dataSource.insertState("Texas","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/texas-map-outline-dddddd.png");
        dataSource.insertState("Iowa","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/iowa-map-outline-dddddd.png");
        dataSource.insertState("Wisconsin","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/wisconsin-map-outline-dddddd.png");
        dataSource.insertState("California","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/california-map-outline-dddddd.png");
        dataSource.insertState("Minnesota","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/minnesota-map-outline-dddddd.png");
        dataSource.insertState("Oregon","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/oregon-map-outline-dddddd.png");
        dataSource.insertState("Kansas","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/kansas-map-outline-dddddd.png");
        dataSource.insertState("WestVirginia","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/west-virginia-map-outline-dddddd.png");
        dataSource.insertState("Nevada","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/nevada-map-outline-dddddd.png");
        dataSource.insertState("Nebraska","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/nebraska-map-outline-dddddd.png");
        dataSource.insertState("Colorado","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/colorado-map-outline-dddddd.png");
        dataSource.insertState("NorthDakota","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/north-dakota-map-outline-dddddd.png");
        dataSource.insertState("SouthDakota","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/south-dakota-map-outline-dddddd.png");
        dataSource.insertState("Montana","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/montana-map-outline-dddddd.png");
        dataSource.insertState("Washington","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/washington-map-outline-dddddd.png");
        dataSource.insertState("Idaho","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/idaho-map-outline-dddddd.png");
        dataSource.insertState("Wyoming","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/wyoming-map-outline-dddddd.png");
        dataSource.insertState("Utah","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/utah-map-outline-dddddd.png");
        dataSource.insertState("Oklahoma","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/oklahoma-map-outline-dddddd.png");
        dataSource.insertState("NewMexico","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/new-mexico-map-outline-dddddd.png");
        dataSource.insertState("Arizona","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/arizona-map-outline-dddddd.png");
        dataSource.insertState("Alaska","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/alaska-map-outline-dddddd.png");
        dataSource.insertState("Hawaii","Fun Fact: ","https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/hawaii-map-outline-dddddd.png");
        //dataSource.insertState("","","");
    }


}