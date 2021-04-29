package com.example.geoquiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.Espresso.onView;
import static com.google.common.truth.Truth.assertThat;
//import static org.junit.Assert.assertThat;
import static androidx.test.espresso.Espresso.onView;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StartScreenFragmentTest extends TestCase {



    @Test
    public void testOnViewCreated() {

//        TestNavHostController navController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        NavController navController = mock(NavController.class);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        NavController navController = navHostFragment.getNavController();
        FragmentScenario<StartScreenFragment> startScenario = FragmentScenario.launchInContainer(StartScreenFragment.class);

        startScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navController));
        navController.setGraph(R.navigation.nav_graph);




        assertThat(navController.getCurrentDestination().equals(R.id.PreQuizFragment));
    }
    /*
    TestNavHostController navController = new TestNavHostController(ApplicationProvider.getApplicationContext());

        // Create a graphical FragmentScenario for the TitleScreen
        FragmentScenario<TitleScreen> titleScenario = FragmentScenario.launchInContainer(TitleScreen.class);

        titleScenario.onFragment(fragment ->
                // Set the graph on the TestNavHostController
                navController.setGraph(R.navigation.trivia);

                // Make the NavController available via the findNavController() APIs
                Navigation.setViewNavController(fragment.requireView(), navController)
        );

        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.play_btn)).perform(ViewActions.click());
        assertThat(navController.currentDestination.id).isEqualTo(R.id.in_game);
     */

}