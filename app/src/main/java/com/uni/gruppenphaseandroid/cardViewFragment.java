package com.uni.gruppenphaseandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uni.gruppenphaseandroid.cheating.SensorReader;


public class cardViewFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            SensorReader sensorReader;
            sensorReader= new SensorReader((MainActivity) getContext());
            sensorReader.registerUnregister(true);



            view.findViewById(R.id.btn_returnToGame).setOnClickListener(view1 -> {
                    NavHostFragment.findNavController(cardViewFragment.this)
                            .navigate(R.id.action_cardViewFragment2_to_InGameFragment2);
            });
    }


}