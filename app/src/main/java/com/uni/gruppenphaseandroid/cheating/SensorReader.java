package com.uni.gruppenphaseandroid.cheating;



import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.view.View;

import android.widget.TextView;

import com.uni.gruppenphaseandroid.MainActivity;
import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.cheating.Cheater;

/**
 *
 * how to call on the class:
 *
 * SensorReader sensorReader;
 * sensorReader.registerUnregister(false);
 * sensorReader= new SensorReader(context, DEFAULT_SAMPLE_NUMBER);
 * sensorReader.registerUnregister(true);
 *
 */


public class SensorReader {//implements EventListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    Context context;
    Cheater cheater;
    TextView textView;


    public SensorReader (MainActivity context){
        this.context = context;

        //declaring Sensor Manager and sensor type
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void registerUnregister (boolean register){
        if (register)
            sensorManager.registerListener(mSensorListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        else
            sensorManager.unregisterListener(mSensorListener);
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y = event.values[1];


                if (Math.abs(x) > Math.abs(y)) {
                    if (x < 0) { //tilt to right
                        //TODO Manipulate move -1
                        //TODO cheater.cheatingAllowed(GameManager.getInstance().*playerID*);


                        //shows a textView that is gone after 5 seconds
                        textView.findViewById(R.id.tv_cheater);
                        CountDownTimer timer = new CountDownTimer(5000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                textView .setVisibility(View.INVISIBLE); //(or GONE)
                            }
                        }.start();



                    } else {
                        if (x > 0) { //tilt to right
                            //TODO Manipulate move +1

                        }
                    }
                }else
                    registerUnregister(false);

            }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

}

