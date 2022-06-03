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

    setButtons();

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
        for (int i = 0; i<players; i++) {
            if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient()) {
                switch (i) {
                    case 2:
                        playerThree.setVisibility(View.VISIBLE);
                        playerThree.setBackgroundColor(assignColor(3));
                        break;
                    case 1:
                        playerTwo.setVisibility(View.VISIBLE);
                        playerTwo.setBackgroundColor(assignColor(2));
                        break;
                    case 0:
                        playerOne.setVisibility(View.VISIBLE);
                        playerOne.setBackgroundColor(assignColor(1));
                        break;
                }
            }
        }

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
