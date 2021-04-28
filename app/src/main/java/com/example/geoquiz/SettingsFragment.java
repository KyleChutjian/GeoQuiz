package com.example.geoquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // number of questions
    private int quizQuestions = 50;

    // time for quiz
    private int quizMinutes = 2;
    private int quizSeconds = 15;

    NavController navController = null;


    public SettingsFragment() {
    }


    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // get buttons and disable or enable them if they are at their MAX / MIN
        // this may or may not work when fully implemented - we can do something different of course
        Button moreTimeBtn = (Button) view.findViewById(R.id.moreTimeBtn);
        Button lessTimeBtn = (Button) view.findViewById(R.id.lessTimeBtn);
        if (quizMinutes < 10) {
            moreTimeBtn.setEnabled(true);
        } else if (quizMinutes == 10) {
            moreTimeBtn.setEnabled(true);
        }
        if (quizMinutes < 2) {
            lessTimeBtn.setEnabled(true);
        }

        Button moreQsBtn = (Button) view.findViewById(R.id.lessQBtn);
        Button lessQsBtn = (Button) view.findViewById(R.id.lessQBtn);

        if (quizQuestions < 50 && quizQuestions > 10) {
            moreQsBtn.setEnabled(true);
            lessQsBtn.setEnabled(true);
        } else if (quizQuestions == 50) {
            moreQsBtn.setEnabled(false);
        } else if (quizQuestions == 10) {
            lessQsBtn.setEnabled(false);
        }

    }

    // need to implement onclick listeners for buttons & use variables in TextViews
//    @Override
//    public void onClick(View v) {
//        if (v.getId()==R.id.moreTimeBtn) {
//            TextView timeSetting = (TextView) getView().findViewById(R.id.timeSettingText);
//            timeSetting.setText("+MINUTE VARIABLE" + "+SECONDS VARIABLE");
//        } else if (v.getId()==R.id.lessTimeBtn) {
//            TextView timeSetting = (TextView) getView().findViewById(R.id.timeSettingText);
//            timeSetting.setText("+MINUTE VARIABLE" + "-SECONDS VARIABLE");
//        } else if (v.getId()==R.id.moreQBtn) {
//            TextView questionsSetting = (TextView) getView().findViewById(R.id.qAmountSettingText);
//            questionsSetting.setText("+questions");
//        } else if (v.getId()==R.id.lessQBtn){
//
//        }
//    }
}