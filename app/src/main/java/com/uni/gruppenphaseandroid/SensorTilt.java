package com.uni.gruppenphaseandroid;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;

import com.uni.gruppenphaseandroid.cheating.Cheater;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.EventListener;

public class SensorTilt implements EventListener, SensorEventListener {

    public String playerID;

    public SensorTilt(String playerID) {
        this.playerID = playerID;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Cheater cheater = new Cheater(GameManager.getInstance().getCurrentTurnPlayerNumber(), GameManager.getInstance().getRoundIndex());

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];

        boolean cheating = cheater.cheatingAllowed(playerID);
        if (Math.abs(x) > Math.abs(y)) {

            if (x < 0 && cheating) { //tilt to right
                //cheater.cheating(cheater);
                GameManager.getInstance().setCheatModifier(1);
                //cheaterNote = "+1";

                /*textView.setText("Cheater +1");
                textView.setVisibility(View.VISIBLE);
*/

            } else if (x > 0 && cheating) { //tilt to left
                //cheater.cheating(cheater);
                GameManager.getInstance().setCheatModifier(-1);
                //cheaterNote = "-1";

               /* textView.setText("Cheater - 1");
                textView.setVisibility(View.VISIBLE);
*/

            }else{
                GameManager.getInstance().setCheatModifier(0);
                //cheaterNote = "0";
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
