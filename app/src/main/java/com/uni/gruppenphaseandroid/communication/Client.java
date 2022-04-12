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
        // this method is overridden when we create the client
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d("websocket", "Connection closed" + code);
    }

    @Override
    public void onError(Exception e) {
        Log.d("websocket", "Error: ", e);
    }
}
