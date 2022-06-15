package com.uni.gruppenphaseandroid;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.gruppenphaseandroid.cards.CardAdapter;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.Objects;

public class HowToPlayFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_howtoplay, container, false);

        TextView tv = view.findViewById(R.id.tv_instructions);
        tv.setText("Spielanleitung hier einfügen");
        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

    }
}
