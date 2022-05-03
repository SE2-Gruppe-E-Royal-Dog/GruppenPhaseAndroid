package com.uni.gruppenphaseandroid.playingfield;


import android.hardware.camera2.params.BlackLevelPattern;

import java.util.Random;

public class Wormhole extends Field {

    Wormhole partnerWormhole;

    public Wormhole getPartnerWormhole() {
        return partnerWormhole;
    }

    public void setPartnerWormhole(Wormhole partnerWormhole) {
        this.partnerWormhole = partnerWormhole;
    }



    public Wormhole(FieldUI fieldUIobject, Field nextField, Field previousField, Figure currentFigure, int fieldID) {
        super (fieldUIobject, nextField, previousField, currentFigure, fieldID);

    }


    public void moveWormholeToRandomPosition(){
        int min = 2;
        int max = 63;
        Random random = new Random();
        int value = random.nextInt(max - min) + min;

        Field targetField = getFieldAtDistance(value, Color.BLACK);

        while ( targetField instanceof StartingField || targetField instanceof GoalField || targetField.getCurrentFigure() != null) {
            value = random.nextInt(max - min) + min;

            targetField = getFieldAtDistance(value, Color.BLACK);

        }

        switchField(targetField);

    }



}
