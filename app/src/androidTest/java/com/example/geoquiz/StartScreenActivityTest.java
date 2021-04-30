package com.example.geoquiz;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.android.material.internal.ContextUtils.getActivity;

@RunWith(AndroidJUnit4.class)
public class StartScreenActivityTest {
    @Rule
    public ActivityTestRule<StartScreenActivity> startScreenActivityActivityTestRule = new ActivityTestRule<>(StartScreenActivity.class);
    @Test
    public void testOnCreateView() {
        onView(withId(R.id.us_states_quiz)).perform(click());
        onView(withId(R.id.playerName)).perform(typeText("Instrumented Test Input"), ViewActions.closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withText("Start Quiz")).perform(click());

        onView(withText("AL")).perform(click());
        onView(withText("AK")).perform(click());
        onView(withText("AZ")).perform(click());
        onView(withText("AR")).perform(click());
        onView(withText("CA")).perform(click());

        onView(withText("CO")).perform(click());
        onView(withText("CT")).perform(click());
        onView(withText("DE")).perform(click());
        onView(withText("FL")).perform(click());
        onView(withText("GA")).perform(click());

        onView(withText("HI")).perform(click());
        onView(withText("ID")).perform(click());
        onView(withText("IL")).perform(click());
        onView(withText("IN")).perform(click());
        onView(withText("IA")).perform(click());

        onView(withText("AL")).perform(click());
        onView(withText("AK")).perform(click());
        onView(withText("AZ")).perform(click());
        onView(withText("AR")).perform(click());
        onView(withText("CA")).perform(click());

        onView(withText("CO")).perform(click());
        onView(withText("CT")).perform(click());
        onView(withText("DE")).perform(click());
        onView(withText("FL")).perform(click());
        onView(withText("GA")).perform(click());

        onView(withText("HI")).perform(click());
        onView(withText("ID")).perform(click());
        onView(withText("IL")).perform(click());
        onView(withText("IN")).perform(click());
        onView(withText("IA")).perform(click());

        onView(withText("AL")).perform(click());
        onView(withText("AK")).perform(click());
        onView(withText("AZ")).perform(click());
        onView(withText("AR")).perform(click());
        onView(withText("CA")).perform(click());

        onView(withText("CO")).perform(click());
        onView(withText("CT")).perform(click());
        onView(withText("DE")).perform(click());
        onView(withText("FL")).perform(click());
        onView(withText("GA")).perform(click());

        onView(withText("HI")).perform(click());
        onView(withText("ID")).perform(click());
        onView(withText("IL")).perform(click());
        onView(withText("IN")).perform(click());
        onView(withText("IA")).perform(click());

        onView(withText("AL")).perform(click());
        onView(withText("AK")).perform(click());
        onView(withText("AZ")).perform(click());
        onView(withText("AR")).perform(click());
        onView(withText("CA")).perform(click());
    }
}