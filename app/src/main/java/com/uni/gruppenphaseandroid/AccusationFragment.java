package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

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

    leaveFragment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    });

       playerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO PlayerID + PlayerName erforderlich um Information an den Game Manager zu schicken und mit der Liste abgleichen
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

}
