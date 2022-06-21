package com.uni.gruppenphaseandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.uni.gruppenphaseandroid.communication.Client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class WebSocketService extends Service {
    private final Client client;

    public WebSocketService() throws URISyntaxException {
        client = new Client(new URI("ws://10.0.2.2::8080/dog-royal")) {
            @Override
            public void onMessage(String message) {
                super.onMessage(message);
                Intent intent = new Intent();
                intent.setAction("com.xch.servicecallback.content");
                intent.putExtra("message", message);
                sendBroadcast(intent);
            }
        };
    }

    public Client getClient() throws InterruptedException {
        if (client.isOpen()) {
            return client;
        }

        try {
            client.connectToServer();
        } catch (InterruptedException e) {
            Log.d("websocket", "Unable to get client", e);
            throw e;
        }
        return client;
    }

    public class WebSocketBinder extends Binder {
        public WebSocketService getService() {
            return WebSocketService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new WebSocketBinder();
    }
}