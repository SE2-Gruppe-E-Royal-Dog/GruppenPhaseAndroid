package com.uni.gruppenphaseandroid.playingfield;


import android.hardware.camera2.params.BlackLevelPattern;

public class Wormhole extends Field {


    Field wormhole;
    Field wormholeField_1;
    Field wormholeField_2;
    Field wormholeField_3;
    Field wormholeField_4;

    public static void main(String[] args) {


        //TODO wenn Nachricht von Server kommt, Sensor 0 dann müssen sich die Felder in Random ändern

        Field wormholeField_1 = wormholeField_1.moveWormholeToRandomPosition();
        Field wormholeField_2 = wormholeField_2.moveWormholeToRandomPosition();
        Field wormholeField_3 = wormholeField_3.moveWormholeToRandomPosition();
        Field wormholeField_4 = wormholeField_4.moveWormholeToRandomPosition();



    }
}
