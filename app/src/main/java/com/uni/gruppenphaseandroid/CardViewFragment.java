package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.uni.gruppenphaseandroid.cards.CardAdapter;
import com.uni.gruppenphaseandroid.cheating.Cheater;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.EventListener;
import java.util.Objects;


public class CardViewFragment extends DialogFragment implements EventListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textView;
    private Button btnPlayCard;
    private String clickedCard;
    private int roundIndex; // TODO get from Game Manager ... needed?
    private int postitionCardToDischarge;


    private static String playerId;

    public interface OnInputListener{
        void sendInputCardFragment(String input);
    }

    public OnInputListener cardInputListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_view, container, false);
        btnPlayCard = view.findViewById(R.id.btn_playCard);
        //set card default
        clickedCard = "-1";
        if(GameManager.getInstance().isThereAnyPossibleMove()){
            view.findViewById(R.id.tv_instrucitons).setVisibility(View.VISIBLE);
        }

        //set up for recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewCard);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(new CardAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int card, int possition) {

                if(GameManager.getInstance().isItMyTurn()) {
                    btnPlayCard.setVisibility(View.VISIBLE);
                    clickedCard = Integer.toString(card);
                    postitionCardToDischarge = possition;

                }
            }
        });
        recyclerView.setAdapter(cardAdapter);
        recyclerView.scrollToPosition(((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager()))
                .findFirstCompletelyVisibleItemPosition());

        //return to board button
        view.findViewById(R.id.btn_returnToGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });


        //select card and return to board
        view.findViewById(R.id.btn_playCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("card_input", "input:" + clickedCard);
                //capture input
                if (!clickedCard.equals("")) {
                    if(GameManager.getInstance().isThereAnyPossibleMove()){
                        textView.setText("Select one card to discharge:");
                        cardInputListener.sendInputCardFragment("-1");
                        getDialog().dismiss();
                    }else {
                        cardInputListener.sendInputCardFragment(clickedCard);
                        getDialog().dismiss();
                    }
                }
            }
        });
        return view;
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
            Log.d("Sensor error tilt", e.getMessage());
        };

    }

    @Override
    public void onAttach(@NonNull Context context) {            //Methode für DialogFragement communication
        super.onAttach(context);
        try{
            cardInputListener = (OnInputListener) getTargetFragment();
        }catch (ClassCastException e){

            Log.e("CardViewFragment", "onAttach: ClassCastException: " + e.getMessage());
        }
    }





    /**
     * Mehtoden die notwendig für den Sensor sind
     */
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
        Cheater cheater = new Cheater(GameManager.getInstance().getCurrentTurnPlayerNumber(), GameManager.getInstance().getRoundIndex());
        TextView note = getActivity().findViewById(R.id.tv_cheater);

        textView = getView().findViewById(R.id.tv_cheater);
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];

        boolean cheating = cheater.cheatingAllowed(playerId);
        if (Math.abs(x) > Math.abs(y)) {

            if (x < 0 && cheating) { //tilt to right
                cheater.cheating(cheater);
                GameManager.getInstance().setCheatModifier(-1);
                note.setText("-1");
                note.setVisibility(View.VISIBLE);

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
                if (x > 0 && cheating) { //tilt to left
                    cheater.cheating(cheater);
                    GameManager.getInstance().setCheatModifier(+1);
                    note.setText("+1");
                    note.setVisibility(View.VISIBLE);

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

    public static void setPlayerId(String playerId) {
        CardViewFragment.playerId = playerId;
    }

    public int getPostitionCardToDischarge() {
        return postitionCardToDischarge;
    }
}