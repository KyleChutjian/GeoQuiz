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
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    // number of questions
    private int quizQuestions = 50;

    // time for quiz
    private int quizMinutes = 2;
    private int quizSeconds = 15;
    private int quizTotalSeconds = 300;

    NavController navController = null;


    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("time",quizTotalSeconds);
        outState.putInt("questions",quizQuestions);
        super.onSaveInstanceState(outState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        if (savedInstanceState != null) {
            quizQuestions = savedInstanceState.getInt("questions");
            quizTotalSeconds = savedInstanceState.getInt("time");
            TextView quizQuestions = (TextView) view.findViewById(R.id.qAmountSettingText);
            quizQuestions.setText(String.valueOf(quizQuestions));
            setTimeText(view);
        } else {
            System.out.println("SAVEDINSTANCESTATE NULL");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        addButtonListeners(view);


        // get buttons and disable or enable them if they are at their MAX / MIN
        // this may or may not work when fully implemented - we can do something different of course
        Button moreTimeBtn = (Button) view.findViewById(R.id.moreTimeBtn);
        Button lessTimeBtn = (Button) view.findViewById(R.id.lessTimeBtn);
        if (quizMinutes < 10) {
//            moreTimeBtn.setEnabled(true);
        } else if (quizMinutes == 10) {
//            moreTimeBtn.setEnabled(true);
        }
        if (quizMinutes < 2) {
//            lessTimeBtn.setEnabled(true);
        }

        Button moreQsBtn = (Button) view.findViewById(R.id.lessQBtn);
        Button lessQsBtn = (Button) view.findViewById(R.id.lessQBtn);

        if (quizQuestions < 50 && quizQuestions > 10) {
//            moreQsBtn.setEnabled(true);
//            lessQsBtn.setEnabled(true);
        } else if (quizQuestions == 50) {
//            moreQsBtn.setEnabled(false);
        } else if (quizQuestions == 10) {
//            lessQsBtn.setEnabled(false);
        }

    }

    public void addButtonListeners(View view) {
        Button saveChangesButton = (Button) view.findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Save Changes Clicked");
                Bundle bundle = new Bundle();
                bundle.putInt("questions", quizQuestions);
                bundle.putInt("time", quizTotalSeconds);
                navController.navigate(R.id.action_settingsFragment_to_startScreenFragment, bundle);
            }
        });
        Button increaseQuestions = (Button) view.findViewById(R.id.moreQBtn);
        increaseQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("More Questions Clicked");
                if (quizQuestions <= 45) {
                    quizQuestions += 5;
                    TextView textView = (TextView) view.findViewById(R.id.qAmountSettingText);
                    textView.setText(String.valueOf(quizQuestions));
                } else {
                    Toast.makeText(view.getContext(), "You cannot have more than 50 questions!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button decreaseQuestions = (Button) view.findViewById(R.id.lessQBtn);
        decreaseQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Less Questions Clicked");
                if (quizQuestions >= 15) {
                    quizQuestions -= 5;
                    TextView textView = (TextView) view.findViewById(R.id.qAmountSettingText);
                    textView.setText(String.valueOf(quizQuestions));
                } else {
                    Toast.makeText(view.getContext(), "You cannot have less than 10 questions!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button increaseTime = (Button) view.findViewById(R.id.moreTimeBtn);
        increaseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("More time Clicked");
                if (quizTotalSeconds <= 585) {
                    quizTotalSeconds += 15;
                    setTimeText(view);
                } else {
                    Toast.makeText(view.getContext(), "You cannot have more than 10 minutes!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button decreaseTime = (Button) view.findViewById(R.id.lessTimeBtn);
        decreaseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Less time Clicked");
                if (quizTotalSeconds >= 135) {
                    quizTotalSeconds -= 15;
                    setTimeText(view);
                } else {
                    Toast.makeText(view.getContext(), "You cannot have less than 2 minutes!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setTimeText(View view) {
        TextView textView = (TextView) view.findViewById(R.id.timeSettingText);
        quizMinutes = quizTotalSeconds / 60;
        quizSeconds = quizTotalSeconds % 60;
        textView.setText(String.format("%02d:%02d", quizMinutes, quizSeconds));
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