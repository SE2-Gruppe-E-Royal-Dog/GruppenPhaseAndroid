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


        GameManager.getInstance().setPlayingField(new PlayingField(view));
        GameManager.getInstance().setWebSocketClient(((MainActivity) getContext()).getWebsocketClient());

        //detect button inputs and send signal to gamemananger

        playingField = new PlayingField(view);

        figureManager = new FigureManager();
        figureManager.createFigureSetOfColor(Color.GREEN, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
        figureManager.createFigureSetOfColor(Color.BLUE, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
        figureManager.createFigureSetOfColor(Color.YELLOW, playingField, view.findViewById(R.id.playingFieldRelativeLayout));
        figureManager.createFigureSetOfColor(Color.RED, playingField, view.findViewById(R.id.playingFieldRelativeLayout));

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


        view.findViewById(R.id.move_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figureManager.moveFigureTest(playingField);
            }
        });


        view.findViewById(R.id.move2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figureManager.moveFigureTest_2(playingField);
            }
        });
    }

}
