package com.se2.communication;

import com.google.gson.Gson;
import com.se2.communication.dto.Message;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Client extends WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private final Gson gson = new Gson();

    public Client(URI serverUri) {
        super(serverUri);
    }

    public void connectToServer() throws InterruptedException {
        connectBlocking();
    }

    public void send(Message webSocketMessage) {
        send(gson.toJson(webSocketMessage));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {}

    @Override
    public void onMessage(String message) {}

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection closed {} ", code);
    }

    @Override
    public void onError(Exception e) {
        logger.error("Error: ", e);
    }
}
