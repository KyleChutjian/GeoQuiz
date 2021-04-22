package com.example.geoquiz;

import android.content.Intent;
import android.database.Cursor;
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

public class QuizResultsFragment extends Fragment {

    private NavController navController;
    private String playerName = null;
    private double playerTime = 0;
    private int playerScore = 0;

    private String firstPlaceNameString = null, firstPlaceTimeString = null, firstPlaceScoreString;
    private String secondPlaceNameString = null, secondPlaceTimeString = null, secondPlaceScoreString;
    private String thirdPlaceNameString = null, thirdPlaceTimeString = null, thirdPlaceScoreString;

    // Databases
    private Cursor cursor;
    private LeaderboardDataSource leaderboardDataSource;



    public QuizResultsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quiz_results, container, false);
        playerName = getArguments().getString("playerName");
        playerTime = getArguments().getDouble("time");
        playerScore = getArguments().getInt("score");

        TextView scoreTextView = view.findViewById(R.id.ScoreText);
        scoreTextView.setText(playerScore + "/50 STATES");

        TextView timeTextView = view.findViewById(R.id.TimeText);

        timeTextView.setText(convertTime(playerTime));

        leaderboardDataSource = new LeaderboardDataSource(getContext());
        leaderboardDataSource.open();

        setupLeaderboard(view);

        TextView firstPlaceName = (TextView) view.findViewById(R.id.FirstPlaceName);
//        firstPlaceName.setText();

        view.findViewById(R.id.TryAgainButton).setOnClickListener(btnListener);
        view.findViewById(R.id.QuizToLearnButton).setOnClickListener(btnListener);
        view.findViewById(R.id.ShareButton).setOnClickListener(btnListener);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void setupLeaderboard(View view) {
        cursor = leaderboardDataSource.queryTopThree();

        System.out.println("Query Number:" + cursor.getCount());


        if (cursor.moveToFirst()) {
            firstPlaceNameString = cursor.getString(0);
            firstPlaceTimeString = convertTime(cursor.getDouble(1));
            firstPlaceScoreString = String.valueOf(cursor.getInt(2));
            cursor.moveToNext();
            if (cursor.isAfterLast()) {
                return;
            }
            secondPlaceNameString = cursor.getString(0);
            secondPlaceTimeString = convertTime(cursor.getDouble(1));
            secondPlaceScoreString = String.valueOf(cursor.getInt(2));
            cursor.moveToNext();
            if (cursor.isAfterLast()) {
                return;
            }
            thirdPlaceNameString = cursor.getString(0);
            thirdPlaceTimeString = convertTime(cursor.getDouble(1));
            thirdPlaceScoreString = String.valueOf(cursor.getInt(2));

            System.out.println(firstPlaceNameString + "," + firstPlaceTimeString + "," + firstPlaceScoreString);
            System.out.println(secondPlaceNameString + "," + secondPlaceTimeString + "," + secondPlaceScoreString);
            System.out.println(thirdPlaceNameString + "," + thirdPlaceTimeString + "," + thirdPlaceScoreString);

            TextView firstPlaceName = (TextView) view.findViewById(R.id.FirstPlaceName);
            TextView firstPlaceTime = (TextView) view.findViewById(R.id.FirstPlaceTime);
//            TextView firstPlaceScore = (TextView) view.findViewById(R.id.FirstPlaceScore);

            TextView secondPlaceName = (TextView) view.findViewById(R.id.SecondPlaceName);
            TextView secondPlaceTime = (TextView) view.findViewById(R.id.SecondPlaceTime);
//            TextView secondPlaceScore = (TextView) view.findViewById(R.id.SecondPlaceScore);

            TextView thirdPlaceName = (TextView) view.findViewById(R.id.ThirdPlaceName);
            TextView thirdPlaceTime = (TextView) view.findViewById(R.id.ThirdPlaceTime);
//            TextView thirdPlaceScore = (TextView) view.findViewById(R.id.ThirdPlaceScore);

            firstPlaceName.setText(firstPlaceNameString);
            firstPlaceTime.setText(firstPlaceTimeString);
//            firstPlaceScore.setText(firstPlaceScoreString);

            secondPlaceName.setText(secondPlaceNameString);
            secondPlaceTime.setText(secondPlaceTimeString);
//            secondPlaceScore.setText(secondPlaceScoreString);

            thirdPlaceName.setText(thirdPlaceNameString);
            thirdPlaceTime.setText(thirdPlaceTimeString);
//            thirdPlaceScore.setText(thirdPlaceScoreString);
        }
    }

    public String convertTime(double time) {
        int mins = (int)time / 60;
        int secs = (int)time % 60;
        int millis = (int)((time % secs) * 100);
        return String.format("%02d:%02d:%02d", mins, secs, millis);
    }



    // Need to implement buttons with listeners - I'll come back to this - max

        private final View.OnClickListener btnListener = new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.TryAgainButton:
                        // Navigate to Pre Quiz Screen
                        System.out.println("Try Again clicked");
                        navController.navigate(R.id.action_QuizResultsFragment_to_PreQuizFragment);
                        break;
                    case R.id.QuizToLearnButton:
                        // Navigate to Learn Page
                        System.out.println("Learn clicked");
                        navController.navigate(R.id.action_QuizResultsFragment_to_LearnFragment);

                        break;
                    case R.id.ShareButton:
                        System.out.println("Share clicked");
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        String subject = "Download GeoQuiz!";
                        String shareText = "I got " + playerScore + "/50 States correct on my GeoQuiz!\nMy time was " + playerTime + "\nTry and beat that!";
                        share.putExtra(Intent.EXTRA_SUBJECT, subject);
                        share.putExtra(Intent.EXTRA_TEXT, shareText);
                        startActivity(Intent.createChooser(share, "My Score on GeoQuiz"));
                        break;
                }
            }
    };

    @Override
    public void onResume() {
        super.onResume();
        leaderboardDataSource.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        leaderboardDataSource.close();

    }
}