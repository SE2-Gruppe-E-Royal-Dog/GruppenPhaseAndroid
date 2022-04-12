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
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.JoinedLobbyPayload;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.NewPlayerJoinedLobbyPayload;
import com.uni.gruppenphaseandroid.service.WebSocketService;

public class MainActivity extends AppCompatActivity {
    private Client websocketClient;
    private WebSocketService.WebSocketBinder binder;
    private WebSocketService service;
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
                handleJoinedLobbyMessage(msg.getBody());
                break;
            case NEW_PLAYER_JOINED:
                handleNewPlayerJoinedMessage(msg.getBody());
        }
    }

    private void handleNewPlayerJoinedMessage(String body) {
        var payload = gson.fromJson(body, NewPlayerJoinedLobbyPayload.class);

        var toast = Toast.makeText(getApplicationContext(),
                "Player " + payload.getPlayerName() + " joined your lobby",
                Toast.LENGTH_LONG);

        toast.show();
    }

    private void handleJoinedLobbyMessage(String body) {
        var payload = gson.fromJson(body, JoinedLobbyPayload.class);
        Log.d("lobby", "Joined lobby with id: " + payload.getLobbyId());
    }

    @Override
    public void finish() {
        super.finish();
        websocketClient.close();
    }

    public Client getWebsocketClient() {
        return websocketClient;
    }
}