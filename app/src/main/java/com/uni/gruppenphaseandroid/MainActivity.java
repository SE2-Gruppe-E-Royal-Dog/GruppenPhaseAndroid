package com.uni.gruppenphaseandroid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.se2.communication.Client;
import com.se2.communication.dto.JoinedLobbyPayload;
import com.se2.communication.dto.Message;
import com.se2.communication.dto.MessageType;
import com.se2.communication.dto.NewPlayerJoinedLobbyPayload;

import com.se2.communication.dto.Payload;
import com.se2.communication.dto.SendCardsPayload;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.Handcards;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

import com.se2.communication.dto.PlayerLeftLobbyPayload;

import com.uni.gruppenphaseandroid.service.WebSocketService;

public class MainActivity extends AppCompatActivity {
    private Client websocketClient;
    private WebSocketService.WebSocketBinder binder;
    private WebSocketService service;
    private String lobbyId;
    private String playerId;
    private final Gson gson = new Gson();

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("MainActivity", "service bound successfully");
            binder = (WebSocketService.WebSocketBinder) iBinder;
            service = binder.getService();
            websocketClient = service.getClient();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("MainActivity", "service disconnected successfully");
        }
    };

    private void bindService() {
        var bindIntent = new Intent(MainActivity.this, WebSocketService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindService();
        doRegisterReceiver();

        var fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doRegisterReceiver() {
        var chatMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter("com.xch.servicecallback.content");
        registerReceiver(chatMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            handleMessage(intent.getStringExtra("message"));
        }
    }

    private void handleMessage(String message) {
        Message msg = gson.fromJson(message, Message.class);
        switch (msg.getType()) {
            case JOINED_LOBBY:
                handleJoinedLobbyMessage(msg.getPayload());
                break;
            case NEW_PLAYER_JOINED:
                handleNewPlayerJoinedMessage(msg.getPayload());
                break;
            case START_GAME:
                handleStartGame(msg.getPayload());
                break;
            case UPDATEBOARD:
                handleUpdateBoard(msg.getPayload());
            case PLAYER_LEFT_LOBBY:
                handlePlayerLeftMessage(msg.getPayload());
            case SEND_CARDS:
                handleSendCardsMessage(msg.getPayload());
        }
    }

    private void handleSendCardsMessage(String sendCardsPayload){
        var payload = gson.fromJson(sendCardsPayload, SendCardsPayload.class);

        if(payload.getLobbyID().equals(lobbyId) && payload.getPlayerID().equals(playerId)){
            Handcards.getInstance().addCardToHand(payload.getCards());
        }
    }

    private void handlePlayerLeftMessage(String body) {
        var payload = gson.fromJson(body, PlayerLeftLobbyPayload.class);

        showPlayerToast(String.format("Player %s left your lobby", payload.getPlayerName()));
    }

    private void handleNewPlayerJoinedMessage(String body) {
        var payload = gson.fromJson(body, NewPlayerJoinedLobbyPayload.class);

        showPlayerToast(String.format("Player %s joined your lobby", payload.getPlayerName()));
    }

    private void handleJoinedLobbyMessage(String body) {
        var payload = gson.fromJson(body, JoinedLobbyPayload.class);
        lobbyId = payload.getLobbyId();
        playerId = payload.getPlayerId();
        Log.d("lobby", "Joined lobby with id: " + playerId);
    }

    private void showPlayerToast(String message) {
        var toast = Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG);

        toast.show();
    }

    private void handleStartGame(String body){

        String[] splitString = body.split("_");
        int numberOfPlayers = Integer.parseInt(splitString[0]);
        int playerTurnNumber = Integer.parseInt(splitString[1]);
        //start game
        GameManager.getInstance().startGame(numberOfPlayers, playerTurnNumber);

    }

    private void handleUpdateBoard(String body){
        String[] splitString = body.split("_");
        GameManager.getInstance().updateBoard(splitString);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public Client getWebsocketClient() {
        return websocketClient;
    }

    public WebSocketService getService() {
        return service;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void sendMessage(MessageType messageType, Payload payload){
        websocketClient = getService().getClient();
        var message = new Message();
        message.setType(messageType);

        message.setPayload(gson.toJson(payload));

        websocketClient.send(message);
    }
}