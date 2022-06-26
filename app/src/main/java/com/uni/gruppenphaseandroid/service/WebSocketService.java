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

/***
 * diese Klasse ist ein Android Service, der im Hintergrund läuft und normalerweise für arbeitsintensive
 * Aufgaben, die asynchron bearbeitet werden können, oder für die Server Kommunikation. Wir verwenden
 * den Service für das erstellen und halten des WebSocket Clients. Aktivities/Fragments die mit dem Server
 * kommunizieren wollen können sich von diesem Service den Client holen.
 * Ist nötig da wir nur eine Connection/Client zum server haben wollen.
 */
public class WebSocketService extends Service {
    private final Client client;

    public WebSocketService() throws URISyntaxException {
        client = new Client(new URI("ws://10.0.2.2:8080/dog-royal")) {
            /**
             * Diese methode ist wichtig, da diese für jede message die wir vom server erhalten,
             * einen Intent anlegt und diesen weiter sendet. Dieser Intent wird in der MainActivity
             * vom MessageReceiver empfangen und dort verabeitet. War nötig, weil die Messages vom Server
             * die UI verändern können musste. Wöre jetzt mit dem GameManager wahrscheinlich auch leichter zu lösen
             */
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

    /**
     * Wird von außen verwendet um die Client Instanz zu erhalten. Wenn der Client noch nicht
     * mit dem Server verbunden ist wird auch die Verbindung hergestellt
     */
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