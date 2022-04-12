package com.uni.gruppenphaseandroid.communication;

import android.util.Log;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.communication.dto.Message;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class Client extends WebSocketClient {
    private final Gson gson = new Gson();

    public Client(URI serverUri) throws InterruptedException {
        super(serverUri);
        connectBlocking();
    }

    public void send(Message webSocketMessage) {
        send(gson.toJson(webSocketMessage));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.d("websocket", "Connection opened");
    }

    @Override
    public void onMessage(String message) {

    }

    /*    @Override
    public void onMessage(String message) {
        Message msg = gson.fromJson(message, Message.class);
        switch (msg.getType()) {
            case JOINED_LOBBY:
                handleJoinedLobbyMessage(msg.getBody());
                break;
            case NEW_PLAYER_JOINED:
                handleNewPlayerJoinedMessage(msg.getBody());
        }
    }

    private void handleNewPlayerJoinedMessage(String body) {
        var payload = gson.fromJson(body, NewPlayerJoinedLobbyPayload.class);
        Log.d("player joined", "player " + payload.getPlayerName() + " joined your lobby");
    }

    private void handleJoinedLobbyMessage(String body) {
        var payload = gson.fromJson(body, JoinedLobbyPayload.class);
        Log.d("lobby", "Joined lobby with id: " + payload.getLobbyId());
    }*/

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d("websocket", "Connection closed" + code);
    }

    @Override
    public void onError(Exception e) {
        Log.d("websocket", "Error: ", e);
    }
}
