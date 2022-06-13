package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.communication.Client;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import java.util.List;

public class CommunicationManager {
    Client websocketClient;
    String lobbyID;
    String playerID;

    public CommunicationManager(Client websocketClient, String lobbyID, String playerID){
        this.websocketClient = websocketClient;
        this.lobbyID = lobbyID;
        this.playerID = playerID;
    }


    public void sendUpdateBoardMessage(LastTurn lastTurn){
        websocketClient.send(lastTurn.generateServerMessage(lobbyID, playerID));
    }

    public void sendMoveWormholeMessage(List<Wormhole> wormholeList){

    }
}
