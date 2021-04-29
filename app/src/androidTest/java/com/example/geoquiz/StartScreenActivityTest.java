package com.example.geoquiz;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StartScreenActivityTest {

    public void testOnCreate() {
        TestNavHostController navController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        FragmentScenario<StartScreenFragment> startScenario = FragmentScenario.launchInContainer(StartScreenFragment.class);

//        startScenario.onFragment(fragment -> navController.setGraph(R.navigation.nav_graph);
//        Navigation.setViewNavController(fragment)





    }

    public void testOnCreateOptionsMenu() {
    }

    public void testOnOptionsItemSelected() {
    }

    public void testOnBackPressed() {
    }

    public void testOnNavigationItemSelected() {
    }
}