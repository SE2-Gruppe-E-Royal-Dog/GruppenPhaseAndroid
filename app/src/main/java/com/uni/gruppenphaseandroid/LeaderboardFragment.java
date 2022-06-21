package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.uni.gruppenphaseandroid.manager.PlayerRanking;

public class LeaderboardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        TextView firstPlayerName = view.findViewById(R.id.first_playerName);
        TextView secondPlayerName = view.findViewById(R.id.second_playerName);
        TextView thirdPlayerName = view.findViewById(R.id.third_playerName);
        TextView fourthPlayerName = view.findViewById(R.id.fourth_playerName);
        Button exitButton = view.findViewById(R.id.exit_button);

        PlayerRanking playerRanking = new PlayerRanking();

        String[] names = playerRanking.getRankedNames();

        firstPlayerName.setText(names[0]);
        secondPlayerName.setText(names[1]);
        thirdPlayerName.setText(names[2]);
        fourthPlayerName.setText(names[3]);


        exitButton.setOnClickListener(view1 -> {

        });
    }
}