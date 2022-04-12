package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.NewPlayerPayload;

public class MenuFragment extends Fragment {
    private Client websocketClient;
    private Gson gson = new Gson();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.bttn_join_game).setOnClickListener(view1 -> {
            websocketClient = ((MainActivity) getContext()).getWebsocketClient();
            var message = new Message();
            message.setType(MessageType.JOIN_LOBBY);

            var playerName = (EditText) view.findViewById(R.id.txt_player_name);

            var payload = new NewPlayerPayload(playerName.getText().toString());

            message.setBody(gson.toJson(payload));

            websocketClient.send(message);
            NavHostFragment.findNavController(MenuFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }
}