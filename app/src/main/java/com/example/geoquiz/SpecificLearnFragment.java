package com.example.geoquiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class SpecificLearnFragment extends Fragment {

    public SpecificLearnFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specific_learn, container, false);
        String stateName = getArguments().getString("stateName");
        String stateDescription = getArguments().getString("stateDescription");
        String imageLink = getArguments().getString("imageLink");

        TextView stateNameText = (TextView) view.findViewById(R.id.LearnStateName);
        stateNameText.setText(stateName);

        TextView stateDescriptionText = (TextView) view.findViewById(R.id.LearnStateDescription);
        stateDescriptionText.setText(stateDescription);

        ImageView stateImage = (ImageView) view.findViewById(R.id.LearnStateImage);
        new DownloadImageTask(stateImage).execute(imageLink);
        return view;
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
}