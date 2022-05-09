package com.se2.communication.dto;

public class WormholeSwitchPayload {
    int newWormholeFieldPosition_1;
    int newWormholeFieldPosition_2;
    int newWormholeFieldPosition_3;
    int newWormholeFieldPosition_4;

    public WormholeSwitchPayload(int newWormholeFieldPosition_1, int newWormholeFieldPosition_2, int newWormholeFieldPosition_3, int newWormholeFieldPosition_4) {
        this.newWormholeFieldPosition_1 = newWormholeFieldPosition_1;
        this.newWormholeFieldPosition_2 = newWormholeFieldPosition_2;
        this.newWormholeFieldPosition_3 = newWormholeFieldPosition_3;
        this.newWormholeFieldPosition_4 = newWormholeFieldPosition_4;
    }


    public int getNewWormholeFieldPosition_1() {
        return newWormholeFieldPosition_1;
    }



    public int getNewWormholeFieldPosition_2() {
        return newWormholeFieldPosition_2;
    }



    public int getNewWormholeFieldPosition_3() {
        return newWormholeFieldPosition_3;
    }



    public int getNewWormholeFieldPosition_4() {
        return newWormholeFieldPosition_4;
    }


}
