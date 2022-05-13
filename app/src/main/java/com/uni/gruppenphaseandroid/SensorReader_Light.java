package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.se2.communication.Client;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.sql.SQLOutput;
import java.util.EventListener;

public class SensorReader_Light extends Fragment implements EventListener, SensorEventListener{


        private Client websocketClient;
        private SensorManager sensorManager;
        private Sensor sensor;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            websocketClient = ((MainActivity) getContext()).getWebsocketClient();


            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); // Type_Light ist der int Wert 5


        }

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {

            if (event.values[0] <= event.values[0]){
                System.out.println("sensor_light");
                GameManager.getInstance().moveWormholes();
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);
        }

}
