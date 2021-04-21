package com.example.geoquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuizResultsFragment extends Fragment {

    public QuizResultsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz_results, container, false);
    }

    // Need to implement buttons with listeners - I'll come back to this - max

    //    private final View.OnClickListener btnListener = new View.OnClickListener() {
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.TryAgainButton:
//                    // Navigate to Pre Quiz Screen
//                    System.out.println("Try Again clicked");
//                    break;
//                case R.id.QuizToLearnButton:
//                    // Navigate to Learn Page
//                    System.out.println("Learn clicked");
//                    break;
//                case R.id.ShareButton:
//                    System.out.println("Share clicked");
//                    Intent share = new Intent(Intent.ACTION_SEND);
//                    share.setType("text/plain");
//                    String subject = "Download GeoQuiz!";
//                    String shareText = "I got " + score + "/50 States correct on my GeoQuiz!\nMy time was " + timeTaken + "\nTry and beat that!";
//                    share.putExtra(Intent.EXTRA_SUBJECT, subject);
//                    share.putExtra(Intent.EXTRA_TEXT, shareText);
//                    startActivity(Intent.createChooser(share, "My Score on GeoQuiz"));
//                    break;
//            }
//        }
//    };
}