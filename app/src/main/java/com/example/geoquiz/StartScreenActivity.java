package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class StartScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavController navController;
    private NavigationView navigationView;
    private ShareActionProvider shareActionProvider;
    private boolean isDarkModeOn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        if (savedInstanceState != null) {
            settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.settingsFragment);

        } else {
            settingsFragment = new SettingsFragment();
        }
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Dark Mode Saving and Setting
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        navigationView = findViewById(R.id.navigationView);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // initialize Action Bar options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem helpItem = menu.findItem(R.id.app_bar_help);
        MenuItem colorItem = menu.findItem(R.id.app_bar_color);
        MenuItem settingsItem = menu.findItem(R.id.app_bar_settings);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider((MenuItem)menu.findItem(R.id.action_share));
        setShareActionIntent("Use this app to sharpen your USA Geography skills!");
        return super.onCreateOptionsMenu(menu);
    }

    // Set share intent for share button
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    // called for corresponding options on click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // share intent when share button is clicked
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Check out this cool app!");
                shareActionProvider.setShareIntent(intent);
                break;

            // change app theme to dark mode or to day mode
            case R.id.app_bar_color:
                if (isDarkModeOn == false) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("isDarkModeOn",true);
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("isDarkModeOn",false);
                    editor.apply();
                }
                break;

            // open Settings screen
            case R.id.app_bar_settings:
                navController.navigate(R.id.settingsFragment);
                break;

            // open Help screen
            case R.id.app_bar_help:
                navController.navigate(R.id.helpFragment);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//
//        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}