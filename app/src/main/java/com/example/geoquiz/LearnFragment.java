package com.example.geoquiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

public class LearnFragment extends Fragment {
    private NavController navController;
    private StatesDataSource statesDataSource;

    public LearnFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        CardView cardView = (CardView)view.findViewById(R.id.card_1);
        statesDataSource = new StatesDataSource(getContext());
        statesDataSource.open();
        setImages(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    public void setImages(View view) {
        ArrayList<View> allImages = new ArrayList<>();
        ArrayList<View> allCards = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            int imageId = this.getResources().getIdentifier("image_"+i,"id", "com.mk.geoquiz");
            int cardId = this.getResources().getIdentifier("card_"+i,"id", "com.mk.geoquiz");
            ImageView image = (ImageView)view.findViewById(imageId);
            CardView card = (CardView)view.findViewById(cardId);
            allImages.add(image);
            allCards.add(card);
        }
        for (int i = 0; i < allImages.size(); i++) {
            new DownloadImageTask((ImageView) view.findViewById(allImages.get(i).getId())).execute(allImages.get(i).getTag().toString());
            allCards.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = Integer.parseInt(v.getTag().toString()) - 1;
                    String imageLink = allImages.get(id).getTag().toString();
                    String[] stateData = statesDataSource.queryWithImageLink(imageLink);
                    String stateName = stateData[0];
                    String stateDescription = stateData[1];

                    Bundle bundle = new Bundle();
                    bundle.putString("stateName",stateName);
                    bundle.putString("stateDescription",stateDescription);
                    bundle.putString("imageLink",imageLink);
                    navController.navigate(R.id.action_learn_to_specificLearn,bundle);
                }
            });
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
}