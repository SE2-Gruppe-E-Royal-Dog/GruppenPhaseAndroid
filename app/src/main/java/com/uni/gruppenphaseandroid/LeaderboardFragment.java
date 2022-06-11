package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class LeaderboardFragment extends Fragment {

    private TextView firstPlayerName;
    private TextView secondPlayerName;
    private TextView thirdPlayerName;
    private TextView fourthPlayerName;
    private Button exitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        firstPlayerName = view.findViewById(R.id.first_playerName);
        secondPlayerName = view.findViewById(R.id.second_playerName);
        thirdPlayerName = view.findViewById(R.id.third_playerName);
        fourthPlayerName = view.findViewById(R.id.fourth_playerName);
        exitButton = view.findViewById(R.id.exit_button);

        /*
        TODO:
        first_playerName.setText();
        second_playerName.setText();
        third_playerName.setText();
        fourth_playerName.setText();
         */

        exitButton.setOnClickListener(view1 -> {
            /*
            TODO: Exit Game
             */
        });
    }
}