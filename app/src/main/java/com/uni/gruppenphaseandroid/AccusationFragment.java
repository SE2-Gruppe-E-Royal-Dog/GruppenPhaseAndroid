package com.uni.gruppenphaseandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.uni.gruppenphaseandroid.cheating.Cheater;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class AccusationFragment extends DialogFragment {
    Button playerOne;
    Button playerTwo;
    Button playerThree;
    Button leaveFragment;
    TextView text;
    com.uni.gruppenphaseandroid.playingfield.Color buttonOneColor;
    com.uni.gruppenphaseandroid.playingfield.Color buttonTwoColor;
    com.uni.gruppenphaseandroid.playingfield.Color buttonThreeColor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accusation, container, false);

        playerOne = view.findViewById(R.id.btn_PlayerOne);
        playerTwo = view.findViewById(R.id.btn_PlayerTwo);
        playerThree = view.findViewById(R.id.btn_PlayerThree);
        leaveFragment = view.findViewById(R.id.btn_returnGame);
        text = view.findViewById(R.id.textView);


        if (GameManager.getInstance().isItMyTurn() && GameManager.getInstance().hasThisClientFigureOnBoard()) {
            text.setText("Who do you accuse");
            setButtons();
        } else if (GameManager.getInstance().isItMyTurn() && !(GameManager.getInstance().hasThisClientFigureOnBoard())) {
            text.setText("No Figure on Board");
        } else {
            text.setText("Wait until your turn!");
        }


        leaveFragment.setOnClickListener(view1 -> getDialog().dismiss());

        playerOne.setOnClickListener(view12 -> {

            getDialog().dismiss();
            Cheater.makeAccusation(buttonOneColor.ordinal(), GameManager.getInstance().getRoundIndex(), GameManager.getInstance().getNumberOfPlayers());
        });

        playerTwo.setOnClickListener(view13 -> {
            getDialog().dismiss();
            Cheater.makeAccusation(buttonTwoColor.ordinal(), GameManager.getInstance().getRoundIndex(), GameManager.getInstance().getNumberOfPlayers());
        });

        playerThree.setOnClickListener(view14 -> {
            getDialog().dismiss();
            Cheater.makeAccusation(buttonThreeColor.ordinal(), GameManager.getInstance().getRoundIndex(), GameManager.getInstance().getNumberOfPlayers());
        });


        return view;
    }

    public void setButtons() {
        int players = GameManager.getInstance().getNumberOfPlayers();
        switch (players - 1) {
            case 3:
                playerThree.setVisibility(View.VISIBLE);
                break;
            case 2:
                playerTwo.setVisibility(View.VISIBLE);
                break;
            case 1:
                playerOne.setVisibility(View.VISIBLE);
                break;
        }

        int i = 0;
        int j = 0;
        do {
            if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient()) {
                switch (j) {
                    case 2:
                        if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient())
                            buttonThreeColor = GameManager.getInstance().getColorOfClient(i);
                            playerThree.setBackgroundColor(assignColor(i));
                            playerThree.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                        i++;
                        j++;
                        break;
                    case 1:
                        if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient())
                            buttonTwoColor = GameManager.getInstance().getColorOfClient(i);
                            playerTwo.setBackgroundColor(assignColor(i));
                            playerTwo.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                        i++;
                        j++;
                        break;
                    case 0:
                        if (GameManager.getInstance().getColorOfClient(i) != GameManager.getInstance().getColorOfMyClient())
                            buttonOneColor = GameManager.getInstance().getColorOfClient(i);
                            playerOne.setBackgroundColor(assignColor(i));
                            playerOne.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                        i++;
                        j++;
                        break;
                }
            } else {
                i++;
            }

        } while (i < players);


    }

    public int assignColor(int playerIndex) {
        switch (GameManager.getInstance().getColorOfClient(playerIndex)) {
            case GREEN:
                return Color.GREEN;
            case RED:
                return Color.RED;
            case YELLOW:
                return Color.YELLOW;
            case BLUE:
                return Color.BLUE;

        }
        return 0;
    }
}


