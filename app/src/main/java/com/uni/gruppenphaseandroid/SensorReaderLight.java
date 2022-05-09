package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;

import java.util.EventListener;

public class SensorReaderLight extends Fragment implements EventListener, SensorEventListener{

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
              /*  var message = new Message();
                message.setType(MessageType.CHEATING_LIGHT);
                websocketClient.send(message);
            */
                //TODO send message to GameManager (prüft vorab ob schummeln möglich ist, wenn ja wird die Message den Server geschickt)
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
