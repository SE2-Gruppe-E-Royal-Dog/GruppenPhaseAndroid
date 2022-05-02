package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class InGameFragment extends Fragment {

    PlayingField playingField;
    FigureManager figureManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_ingame, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playingField = new PlayingField(view);
        figureManager = new FigureManager();
        figureManager.createFigureSetOfColor(Color.GREEN, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
        figureManager.createFigureSetOfColor(Color.BLUE, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
        figureManager.createFigureSetOfColor(Color.YELLOW, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
        figureManager.createFigureSetOfColor(Color.RED, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
    }
}
