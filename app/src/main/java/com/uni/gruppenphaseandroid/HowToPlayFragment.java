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

        return inflater.inflate(R.layout.fragment_howtoplay, container, false);

    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        TextView tv = getView().findViewById(R.id.tv_instructions);
        tv.setText("Spielanleitung hier einfügen");
    }
}
