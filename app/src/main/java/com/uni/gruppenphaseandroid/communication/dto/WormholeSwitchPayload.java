package com.uni.gruppenphaseandroid.communication.dto;

/**
 * hier übergeben wir die 4 Wurmlöcher und die LobbyID,
 * es ist wichtig, dass bei jeder Verschiebung jedes einzelne Feld auf jedem
 * Client geupdated wird
 */
public class WormholeSwitchPayload {
    int newWormholeFieldPosition1;
    int newWormholeFieldPosition2;
    int newWormholeFieldPosition3;
    int newWormholeFieldPosition4;
    String lobbyID;

    public WormholeSwitchPayload(int newWormholeFieldPosition1, int newWormholeFieldPosition2, int newWormholeFieldPosition3, int newWormholeFieldPosition4, String lobbyID) {
        this.newWormholeFieldPosition1 = newWormholeFieldPosition1;
        this.newWormholeFieldPosition2 = newWormholeFieldPosition2;
        this.newWormholeFieldPosition3 = newWormholeFieldPosition3;
        this.newWormholeFieldPosition4 = newWormholeFieldPosition4;
        this.lobbyID = lobbyID;
    }


    public int getNewWormholeFieldPosition1() {
        return newWormholeFieldPosition1;
    }



    public int getNewWormholeFieldPosition2() {
        return newWormholeFieldPosition2;
    }



    public int getNewWormholeFieldPosition3() {
        return newWormholeFieldPosition3;
    }



    public int getNewWormholeFieldPosition4() {
        return newWormholeFieldPosition4;
    }


}
