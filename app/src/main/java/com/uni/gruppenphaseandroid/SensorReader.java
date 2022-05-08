package com.uni.gruppenphaseandroid;



import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.se2.communication.Client;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;



import java.util.EventListener;

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

