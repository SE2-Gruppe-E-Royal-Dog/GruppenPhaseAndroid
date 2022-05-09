package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.se2.communication.dto.StartGamePayload;
import com.uni.gruppenphaseandroid.manager.GameManager;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.google.gson.Gson;
import com.se2.communication.Client;
import com.se2.communication.dto.LeaveLobbyPayload;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;
import com.se2.communication.dto.NewPlayerPayload;

import com.uni.gruppenphaseandroid.playingfield.PlayingField;

import org.java_websocket.client.WebSocketClient;
   
public class InGameFragment extends Fragment {
    FigureManager figureManager;
    private PlayingField playingField;
    private Client websocketClient;
    private Gson gson = new Gson();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_ingame, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playingField = new PlayingField(view);
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


        view.findViewById(R.id.fab_cardholder).setOnClickListener(view1 -> {
            NavHostFragment.findNavController(InGameFragment.this)
                    .navigate(R.id.action_InGameFragment_to_cardViewFragment2);
        });

        view.findViewById(R.id.start_game_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websocketClient = ((MainActivity) getContext()).getService().getClient();
                var lobbyId = ((MainActivity) getContext()).getLobbyId();
                var message = new Message();
                message.setType(MessageType.START_GAME);

                var payload = new StartGamePayload(lobbyId, 0, 0);
                message.setPayload(gson.toJson(payload));

                websocketClient.send(message);
            }
        });
    }
}
