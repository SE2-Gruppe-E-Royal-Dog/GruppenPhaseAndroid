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

    private TextView first_playerName;
    private TextView second_playerName;
    private TextView third_playerName;
    private TextView fourth_playerName;
    private Button exit_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        first_playerName = view.findViewById(R.id.first_playerName);
        second_playerName = view.findViewById(R.id.second_playerName);
        third_playerName = view.findViewById(R.id.third_playerName);
        fourth_playerName = view.findViewById(R.id.fourth_playerName);
        exit_button = view.findViewById(R.id.exit_button);

        PlayerRanking playerRanking = new PlayerRanking();

        String[] names = playerRanking.getRankedNames();

        first_playerName.setText(names[0]);
        second_playerName.setText(names[1]);
        third_playerName.setText(names[2]);
        fourth_playerName.setText(names[3]);


        exit_button.setOnClickListener(view1 -> {
            /*
            TODO: Exit Game
             */
        });
    }
}