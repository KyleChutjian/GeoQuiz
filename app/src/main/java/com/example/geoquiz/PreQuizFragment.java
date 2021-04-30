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
import android.widget.EditText;
import android.widget.Toast;

public class PreQuizFragment extends Fragment implements View.OnClickListener {
    NavController navController = null;

    public PreQuizFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (getArguments() != null) {
            int quizQuestions = getArguments().getInt("questions");
            int quizTime = getArguments().getInt("time");
        }
        return inflater.inflate(R.layout.fragment_pre_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.startQuizButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.startQuizButton) {
            EditText playerNameEditText = (EditText) getView().findViewById(R.id.playerName);
            if (playerNameEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(),"Enter your name!",Toast.LENGTH_LONG).show();
            } else {
                if (getArguments() != null) {
                    getArguments().putString("playerName",playerNameEditText.getText().toString());
                }
                navController.navigate(R.id.action_preQuizFragment_to_quiz,getArguments());
            }
        }
    }
}