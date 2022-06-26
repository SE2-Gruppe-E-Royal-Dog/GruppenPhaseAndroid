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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.cards.CardUI;
import com.uni.gruppenphaseandroid.cheating.Cheater;
import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.communication.dto.JoinedLobbyPayload;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.NewPlayerJoinedLobbyPayload;
import com.uni.gruppenphaseandroid.communication.dto.PlayerLeftLobbyPayload;
import com.uni.gruppenphaseandroid.communication.dto.PunishPayload;
import com.uni.gruppenphaseandroid.communication.dto.SendCardsPayload;
import com.uni.gruppenphaseandroid.communication.dto.StartGamePayload;
import com.uni.gruppenphaseandroid.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.communication.dto.WormholeSwitchPayload;
import com.uni.gruppenphaseandroid.manager.CardManager;
import com.uni.gruppenphaseandroid.manager.CommunicationManager;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.VisualEffectsManagerImpl;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.service.WebSocketService;

public class MainActivity extends AppCompatActivity {
    private Client websocketClient;
    private WebSocketService.WebSocketBinder binder;
    private WebSocketService service;
    private String lobbyId;
    private String playerId;
    private final Gson gson = new Gson();

    /**
     * Eine Connection mit dem Service der im Hintergrund läuft. Benötigt um den Service
     * and die Activity zu binden
     */
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("MainActivity", "service bound successfully");
            binder = (WebSocketService.WebSocketBinder) iBinder;
            service = binder.getService();
            websocketClient = getWebSocketClient();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("MainActivity", "service disconnected successfully");
        }
    };

    /**
     * Bindet den Service zur MainActivty
     */
    private void bindService() {
        var bindIntent = new Intent(MainActivity.this, WebSocketService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    private Client getWebSocketClient() {
        try {
            return getService().getClient();
        } catch (InterruptedException e) {
            Log.d("thread", "Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" CAT ROYAL");
        getSupportActionBar().setIcon(R.drawable.ic_action_cat);

        bindService();
        doRegisterReceiver();
        setPlayerIdInCardView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();       //to get rid of dublicated menu items
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(2).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_howPlay: {
                DialogFragment dialog = new HowToPlayFragment();
                dialog.show(getSupportFragmentManager(), "howPlayDialogFragment");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Client getClient() {
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

    /**
     * Hier wird der MessageReceiver angelegt, und an die com.xch.servicecallback.content gebunden
     * für diese der WebSocketClient broadcasted
     */
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
                case UPDATE_BOARD:
                    handleUpdateBoard(msg.getPayload());
                    break;
                case PLAYER_LEFT_LOBBY:
                    handlePlayerLeftMessage(msg.getPayload());
                    break;
                case SEND_CARDS:
                    handleSendCardsMessage(msg.getPayload());
                    break;
                case WORMHOLE_MOVE:
                    handleWormholeMove(msg.getPayload());
                    break;
                case PUNISHMENT_MESSAGE:
                    handlePunishmentMessage(msg.getPayload());
                    break;
                default:
                    Log.d("message_handler", "Unknown MessageType: " + msg.getType());
            }
        }
    }

    private void handleSendCardsMessage(String sendCardsPayload) {
        var payload = gson.fromJson(sendCardsPayload, SendCardsPayload.class);

        GameManager.getInstance().getCardManager().addCardToHand(payload.getCards());
        GameManager.getInstance().getCardUI().addCardToHand(); //set UI Card Hand
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

    private void handleStartGame(String body) {

        var payload = gson.fromJson(body, StartGamePayload.class);
        GameManager.getInstance().setPlayerNames(payload.getPlayerNames());
        CardManager cardManager = new CardManager();
        FigureManager figureManager = new FigureManager();
        CardUI cardUI = new CardUI();
        cardManager.setFigureManager(figureManager);
        CommunicationManager communicationManager = new CommunicationManager(websocketClient, lobbyId, playerId);
        GameManager.getInstance().startGame(payload.getNumberOfPlayers(), payload.getClientPlayerNumber(), figureManager, new VisualEffectsManagerImpl(findViewById(R.id.stack), getApplicationContext(), findViewById(R.id.btn_cardholderButton), findViewById(R.id.txt_cheater)), cardManager, communicationManager, cardUI);

        findViewById(R.id.btn_cardholderButton).setVisibility(View.VISIBLE);
        findViewById(R.id.start_game_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.btn_accusation).setVisibility(View.VISIBLE);
        showPlayerToast("Your color is: " + GameManager.getInstance().getColorOfMyClient());


        for (int i = 0; i < payload.getNumberOfPlayers(); i++)
            Log.e("Handlestart", GameManager.getInstance().getPlayerNameWithIndex(i));

        setPlayerNamesOnBoard();
    }

    private void handleUpdateBoard(String body) {
        var updateBoardPayload = gson.fromJson(body, UpdateBoardPayload.class);
        if (updateBoardPayload.getCheatModifier() == 1 || updateBoardPayload.getCheatModifier() == -1) {
            Cheater.noteCheating(new Cheater(GameManager.getInstance().getCurrentTurnPlayerNumber(), GameManager.getInstance().getRoundIndex()));
        }
        GameManager.getInstance().updateBoard(updateBoardPayload);
        GameManager.getInstance().setCheatModifier(0);

    }

    private void handleWormholeMove(String body) {
        var updateWormholePayload = gson.fromJson(body, WormholeSwitchPayload.class);
        int[] wormholeIDs = {updateWormholePayload.getNewWormholeFieldPosition1(), updateWormholePayload.getNewWormholeFieldPosition2(), updateWormholePayload.getNewWormholeFieldPosition3(), updateWormholePayload.getNewWormholeFieldPosition4()};
        GameManager.getInstance().moveWormholes(wormholeIDs);
    }


    private void removePlayerNamesOnBoard(String playerId) {        //TODO remove name when player leaves the lobby - nessesary??
        TextView name;
        for (int i = 0; i < GameManager.getInstance().getNumberOfPlayers(); i++) {
            if (GameManager.getInstance().getPlayerNameWithIndex(i).equals(playerId)) {
                switch (i) {
                    case 0:
                        name = findViewById(R.id.tv_playerGreen);
                        name.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        name = findViewById(R.id.tv_playerYellow);
                        name.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        name = findViewById(R.id.tv_playerRed);
                        name.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        name = findViewById(R.id.tv_playerBlue);
                        name.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        }
    }

    private void setPlayerNamesOnBoard() {
        TextView name;
        for (int i = 0; i < GameManager.getInstance().getNumberOfPlayers(); i++) {
            switch (i) {
                case 0:
                    name = findViewById(R.id.tv_playerGreen);
                    name.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                    name.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    name = findViewById(R.id.tv_playerYellow);
                    name.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                    name.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    name = findViewById(R.id.tv_playerRed);
                    name.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                    name.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    name = findViewById(R.id.tv_playerBlue);
                    name.setText(GameManager.getInstance().getPlayerNameWithIndex(i));
                    name.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void setPlayerIdInCardView() {
        CardViewFragment.setPlayerId(playerId);

    }

    private void handlePunishmentMessage(String body) {
        var updatePunishPayload = gson.fromJson(body, PunishPayload.class);
        GameManager.getInstance().executePunishment(updatePunishPayload.getFigureID());
    }
}
