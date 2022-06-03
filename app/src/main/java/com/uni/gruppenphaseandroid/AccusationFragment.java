package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.uni.gruppenphaseandroid.manager.GameManager;

public class AccusationFragment extends DialogFragment {
    Button playerOne, playerTwo, playerThree, leaveFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_accusation, container, false);

    playerOne = view.findViewById(R.id.btn_PlayerOne);
    playerTwo = view.findViewById(R.id.btn_PlayerTwo);
    playerThree = view.findViewById(R.id.btn_PlayerThree);
    leaveFragment = view.findViewById(R.id.btn_returnGame);

    if (GameManager.getInstance().isItMyTurn()) {
        setButtons();
    }

    leaveFragment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    });

       playerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().dismiss();
            }
        });

        playerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        playerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });


       return view;
    }

    public void setButtons (){
        int players = GameManager.getInstance().getNumberOfPlayers();
        switch (players-1) {
            case 2: playerThree.setVisibility(View.VISIBLE);
            case 1: playerTwo.setVisibility(View.VISIBLE);
            case 0: playerOne.setVisibility(View.VISIBLE);
        }

        int i = 0;
        int j = 0;
        do{
            if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient()){
                switch (j) {
                    case 2:
                        if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient())
                            playerThree.setBackgroundColor(assignColor(2));
                        i++;
                        j++;
                        break;
                    case 1:
                        if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient())
                            playerTwo.setBackgroundColor(assignColor(1));
                        i++;
                        j++;
                        break;
                    case 0:
                        if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient())
                            playerOne.setBackgroundColor(assignColor(0));
                        i++;
                        j++;
                        break;
                }
            } else {
                i++;
            }

        } while (i < players);


    }
    public int assignColor(int playerIndex){
        switch (GameManager.getInstance().getColorOfClient(playerIndex)){
            case GREEN: return Color.GREEN;
            case RED: return Color.RED;
            case YELLOW: return Color.YELLOW;
            case BLUE: return Color.BLUE;

        }
        return 0;
    }


}
