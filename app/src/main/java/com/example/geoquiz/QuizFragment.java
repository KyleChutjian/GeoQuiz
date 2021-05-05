package com.example.geoquiz;

import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class QuizFragment extends Fragment {
    private TextView timerTextView;
    private CountDownTimer timer;
    private long maxTimerSeconds = 20;
    private long currentMillis = 0;
    private ArrayList<Integer> possibleStates;
    private int numberOfQuestions = 50;
    private int currentQuestion = 0;
    private NavController navController;

    // Database variables
    private Cursor statesCursor;
    private StatesDataSource statesDataSource;
    private LeaderboardDataSource leaderboardDataSource;
    private String currentStateName = null,currentStateDescription = null,currentStateImageLink = null;
    private String playerName = null;
    private double time = 0;
    private int score = 0;

    public QuizFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quiz, container, false);
        playerName = getArguments().getString("playerName");
        numberOfQuestions = getArguments().getInt("questions");
        maxTimerSeconds = getArguments().getInt("time");

        TextView myScoreText = (TextView) view.findViewById(R.id.myScoreText);
        myScoreText.setText("0/" + numberOfQuestions);
        //Set score to 0 each time quiz is created
        score = 0;
        timerTextView = (TextView) view.findViewById(R.id.timeElapsedTitle);
        runTimer(timerTextView);
        possibleStates = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            possibleStates.add(i); // adds indexes 1-50 to the list
        }
        Collections.shuffle(possibleStates); // randomizes the list
        possibleStates = new ArrayList<Integer>(possibleStates.subList(0,numberOfQuestions));

        addListeners(view);

        statesDataSource = new StatesDataSource(getContext());
        statesDataSource.open();
        setupDatabase(view);
        setImage(view);
        return view;
    }

    // Added this for navcontroller and another fragment had it
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    public boolean isCorrect(View view, View buttonView) {
        Button clickedButton = (Button) view.findViewById(buttonView.getId());
        if (currentStateName.equalsIgnoreCase(clickedButton.getTag().toString())) {
            buttonView.setBackgroundColor(getResources().getColor(R.color.green));
            buttonView.setEnabled(false);
            return true;
        } else {
            buttonView.setBackgroundColor(getResources().getColor(R.color.red));
            return false;
        }
    }

    public void runTimer(View view){
        timer = new CountDownTimer(maxTimerSeconds*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                currentMillis = millisUntilFinished;
            }

            public void onFinish() {
                timerTextView.setText("Time's Up!");
                time = maxTimerSeconds - (double) currentMillis/1000;
                leaderboardDataSource = new LeaderboardDataSource(getContext());
                leaderboardDataSource.open();
                leaderboardDataSource.insertEntry(playerName,time,score);
                leaderboardDataSource.close();
                Bundle bundle = new Bundle();
                bundle.putString("playerName",playerName);
                bundle.putDouble("time",time);
                bundle.putInt("score",score);
                navController.navigate(R.id.action_quiz_to_quizResults,bundle);
            }

        }.start();
    }

    public void setupDatabase(View view) {
        try {
            statesCursor = statesDataSource.getCursor();
            if (statesCursor.moveToFirst()) {
                for (int i = 1; i < possibleStates.get(currentQuestion); i++) {
                    statesCursor.moveToNext();
                }
                int currentId = statesCursor.getInt(0);
                currentStateName = statesCursor.getString(1);
                currentStateDescription = statesCursor.getString(2);
                currentStateImageLink = statesCursor.getString(3);
                setImage(view);
            }
        } catch (SQLException e) {
            System.out.println("Database didnt load");
        }
    }

    public void addListeners(View view) {
        ArrayList<View> allButtons = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            int id = this.getResources().getIdentifier("button_"+i,"id", getContext().getPackageName());
            Button btn = (Button)view.findViewById(id);
            allButtons.add(btn);

        }

        for (int i = 0; i < allButtons.size(); i++) {
            allButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isCorrect(view,v)) {
                        score++;
                    }
                    if (currentQuestion < numberOfQuestions - 1) {
                        TextView myScoreText = (TextView) view.findViewById(R.id.myScoreText);
                        myScoreText.setText(score + "/" + numberOfQuestions);
                        currentQuestion++;
                    } else {
                        // move onto results screen
                        timer.cancel();
                        time = maxTimerSeconds - (double) currentMillis/1000;
                        leaderboardDataSource = new LeaderboardDataSource(getContext());
                        leaderboardDataSource.open();
                        leaderboardDataSource.insertEntry(playerName,time,score);
                        leaderboardDataSource.close();
                        getArguments().putString("playerName",playerName);
                        getArguments().putDouble("timeDouble",time);
                        getArguments().putInt("score",score);
                        getArguments().putInt("questions",numberOfQuestions);
                        navController.navigate(R.id.action_quiz_to_quizResults,getArguments());
                    }
                    setupDatabase(view);
                }
            });
        }
    }
    public void setImage(View view) {
        if (currentStateImageLink != null) {
            new DownloadImageTask((ImageView) view.findViewById(R.id.StateImage)).execute(currentStateImageLink);
            ImageView imageView = (ImageView)view.findViewById(R.id.StateImage);
            imageView.setTag(currentStateName);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        statesDataSource.open();




    }

    @Override
    public void onPause() {
        super.onPause();
        statesDataSource.close();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }
}