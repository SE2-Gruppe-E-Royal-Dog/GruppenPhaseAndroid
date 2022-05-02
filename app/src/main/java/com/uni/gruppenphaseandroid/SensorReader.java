package com.uni.gruppenphaseandroid;



import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import com.se2.communication.Client;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;



import java.util.EventListener;


public class SensorReader extends Fragment implements EventListener, SensorEventListener {

    private Client websocketClient;
    private SensorManager sensorManager;
    private Sensor sensor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        websocketClient = ((MainActivity) getContext()).getWebsocketClient();

        //declaring Sensor Manager and sensor type
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        var message = new Message();
        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) { //tilt to right
                //TODO Manipulate move -1
                message.setType(MessageType.CHEATING_TILT_RIGHT);

            } else {
                if (x > 0) { //tilt to right
                    //TODO Manipulate move +1
                    message.setType(MessageType.CHEATING_TILT_LEFT);
                }
            }
        }
        websocketClient.send(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister Sensor listener
        sensorManager.unregisterListener(this);
    }
}

