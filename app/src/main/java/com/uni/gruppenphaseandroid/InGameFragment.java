package com.uni.gruppenphaseandroid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.LeaveLobbyPayload;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.StartGamePayload;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class InGameFragment extends Fragment implements SensorEventListener {
    private Client websocketClient;
    private final Gson gson = new Gson();
    private SensorManager sensorManager;
    private Sensor sensor;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.activity_ingame, container, false);

        if (CardViewFragment.selectedCard != -1){
            ImageButton btn = root.findViewById(R.id.btn_cardholderButton);
            btn.setImageResource(CardViewFragment.selectedCard);
        } //TODO else set default Image which isnt drawn yet


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayingField playingField = new PlayingField(view);
        GameManager.getInstance().setPlayingField(playingField);
        GameManager.getInstance().setWebSocketClient(((MainActivity) getContext()).getWebsocketClient());


        view.findViewById(R.id.bttn_leave_game).setOnClickListener(view1 -> {
            websocketClient = ((MainActivity) getContext()).getService().getClient();
            var lobbyId = ((MainActivity) getContext()).getLobbyId();
            var playerId = ((MainActivity) getContext()).getPlayerId();
            var message = new Message();
            message.setType(MessageType.LEAVE_LOBBY);
            var payload = new LeaveLobbyPayload(lobbyId, playerId);

            message.setPayload(gson.toJson(payload));

            websocketClient.send(message);
            NavHostFragment.findNavController(InGameFragment.this)
                    .navigate(R.id.action_InGameFragment_to_FirstFragment);
        });


        view.findViewById(R.id.btn_cardholderButton).setOnClickListener(view1 -> NavHostFragment.findNavController(InGameFragment.this)
                .navigate(R.id.action_InGameFragment_to_cardViewFragment2));


        view.findViewById(R.id.move_button).setOnClickListener(view13 -> GameManager.getInstance().moveFigureShowcase(1, 1));


        view.findViewById(R.id.move2).setOnClickListener(view14 -> GameManager.getInstance().moveFigureShowcase(3, 3));


        view.findViewById(R.id.start_game_button).setOnClickListener(view12 -> {
            //deactivate start game button
            playingField.getView().findViewById(R.id.start_game_button).setVisibility(View.INVISIBLE);

            websocketClient = ((MainActivity) getContext()).getService().getClient();
            var lobbyId = ((MainActivity) getContext()).getLobbyId();
            var message = new Message();
            message.setType(MessageType.START_GAME);

            var payload = new StartGamePayload(lobbyId, 0, 0);
            message.setPayload(gson.toJson(payload));

            websocketClient.send(message);
        });



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); // Type_Light ist der int Wert 5
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];

        if (x < 40 && !GameManager.getInstance().hasCheated()) {
            Log.e("Code", "sensor_light");
            GameManager.getInstance().initiateMoveWormholes();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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
