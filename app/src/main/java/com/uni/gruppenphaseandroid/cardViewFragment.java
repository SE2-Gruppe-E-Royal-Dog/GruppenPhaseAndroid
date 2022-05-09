package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.EventListener;


public class cardViewFragment extends Fragment implements EventListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_returnToGame).setOnClickListener(view1 -> {
            NavHostFragment.findNavController(cardViewFragment.this)
                    .navigate(R.id.action_cardViewFragment2_to_InGameFragment2);
        });


    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        //initialization of sensor
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        textView = getView().findViewById(R.id.tv_cheater);
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];

        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) { //tilt to right
                //TODO Manipulate move -1
                //TODO check cheating

                textView.setText("Cheater Cheater -1");
                textView.setVisibility(View.VISIBLE);


                //shows a textView that is gone after 5 seconds
                CountDownTimer timer = new CountDownTimer(3000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        textView.setVisibility(View.INVISIBLE); //(or GONE)
                    }
                }.start();

            } else {
                if (x > 0) { //tilt to left
                    //TODO Manipulate move +1
                    textView.setText("Cheater Cheater + 1");
                    textView.setVisibility(View.VISIBLE);

                    //shows a textView that is gone after 5 seconds
                    CountDownTimer timer = new CountDownTimer(3000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            textView.setVisibility(View.INVISIBLE); //(or GONE)
                        }
                    }.start();
                }

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {


    }


}