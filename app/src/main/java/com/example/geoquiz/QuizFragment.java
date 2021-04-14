package com.example.geoquiz;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class QuizFragment extends Fragment {
    private TextView timer;
    private Cursor cursor;
    private ArrayList<Integer> possibleStates;
    private StatesDataSource dataSource;
    private int currentQuestion = 0;
    private String currentStateName = null;
    private String currentStateDescription = null;
    private String currentStateImageLink = null;
    private int score = 0;

    public QuizFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quiz, container, false);
        timer = (TextView) view.findViewById(R.id.timeElapsedTitle);
        runTimer(timer);
        possibleStates = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            possibleStates.add(i); // adds indexes 1-50 to the list
        }
        Collections.shuffle(possibleStates); // randomizes the list

        addListeners(view);
        dataSource = new StatesDataSource(getContext());
        dataSource.open();
        setupDatabase(view);
        setImage(view);
        return view;
    }

    public boolean isCorrect(View view, View buttonView) {
        Button clickedButton = (Button) view.findViewById(buttonView.getId());
        System.out.println("Checking if " + clickedButton.getTag().toString() + " is equal to " + currentStateName);
        if (currentStateName.equalsIgnoreCase(clickedButton.getTag().toString())) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect!");
            return false;
        }
    }

    public void runTimer(View view){
        new CountDownTimer(3*60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                timer.setText("Time's Up!");
            }
        }.start();
    }

    public void setupDatabase(View view) {
        try {
            cursor = dataSource.getCursor();
            if (cursor.moveToFirst()) {
                for (int i = 1; i < possibleStates.get(currentQuestion); i++) {
                    cursor.moveToNext();
                }
                int currentId = cursor.getInt(0);
                currentStateName = cursor.getString(1);
                currentStateDescription = cursor.getString(2);
                currentStateImageLink = cursor.getString(3);
                setImage(view);
                System.out.println("CurrentStateName: " + currentStateName);
            }
        } catch (SQLException e) {
            System.out.println("Database didnt load");
        }
    }

    public void addListeners(View view) {
        ArrayList<View> allButtons = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            int id = this.getResources().getIdentifier("button_"+i,"id", "com.example.geoquiz");
            Button btn = (Button)view.findViewById(id);
            allButtons.add(btn);
        }

        for (int i = 0; i < allButtons.size(); i++) {
            allButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isCorrect(view,v)) {
                        score++;
                        // Make button text green later
                    }
                    if (currentQuestion<49) {
                        currentQuestion++;
                    } else {
                        // move onto results screen
                        System.out.println("No more states left to display.");
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
        dataSource.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("CLOSED");
        dataSource.close();
    }
}