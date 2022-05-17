package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.CardUI;
import com.uni.gruppenphaseandroid.Cards.Cardtype;

import java.util.EventListener;


public class CardViewFragment extends Fragment implements EventListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //return to board button
        view.findViewById(R.id.btn_returnToGame).setOnClickListener(view1 -> NavHostFragment.findNavController(CardViewFragment.this)
                .navigate(R.id.action_cardViewFragment2_to_InGameFragment2));

        //set view in cardUi fpr linearlayout
        CardUI.getInstance().setView(view);

        //TODO on click show button play card
        view.findViewById(R.id.linlayout_cardHolder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }


    //on create --> creats seonsorListener
    //creats "hand" --> where cards will be stored --> dunno if it's the smartes way
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        //initialization of sensor
        try {
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    //if in CardViewFragment --> listen, otherwise sensor on pause
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


    //What happens if sensor change detected
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
                new CountDownTimer(3000, 1000) {

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
                    new CountDownTimer(3000, 1000) {
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