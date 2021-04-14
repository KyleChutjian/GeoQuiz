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

public class StartScreenFragment extends Fragment implements View.OnClickListener{
    NavController navController = null;
    public StartScreenFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.us_states_quiz).setOnClickListener(this);
        view.findViewById(R.id.learn).setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_screen, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.us_states_quiz:
                navController.navigate(R.id.action_startScreenFragment_to_preQuizFragment);
                break;
            case R.id.learn:
                navController.navigate(R.id.action_startScreenFragment_to_learn);
                break;
        }
    }
}