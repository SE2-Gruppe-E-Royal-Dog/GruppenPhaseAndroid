package com.uni.gruppenphaseandroid;

import com.uni.gruppenphaseandroid.playingfield.Figure;

public class Cheat extends Thread {

    private int round;
    private String playerID;

    public Cheat() {
    }

    public Cheat(String playerID){
        this.playerID = playerID;
    }
}
