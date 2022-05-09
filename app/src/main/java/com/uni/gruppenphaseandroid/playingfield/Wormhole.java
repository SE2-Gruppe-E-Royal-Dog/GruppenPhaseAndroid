package com.uni.gruppenphaseandroid.playingfield;


import android.hardware.camera2.params.BlackLevelPattern;

import java.util.Random;

public class Wormhole extends Field {

    Wormhole partnerWormhole;
    static Random random = new Random();

    public Wormhole() {

    }

    @Override
    protected void triggerSpecialFieldEffect() {
        Figure myFigure;
        Figure partnerFigure;

        myFigure = getCurrentFigure();
        myFigure.setCurrentField(partnerWormhole);

        partnerFigure = partnerWormhole.getCurrentFigure();
        setCurrentFigure(partnerFigure);

        partnerWormhole.setCurrentFigure(myFigure);

        if (partnerFigure != null) {
            partnerFigure.setCurrentField(this);
            partnerFigure.getFigureUI().moveFigureToPosition(getFieldUIobject());
        }

        myFigure.getFigureUI().moveFigureToPosition(partnerWormhole.getFieldUIobject());



    }

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


        int value = generateRandomNumber();
        Field targetField = getNewFieldforWormholeSwitch(value);

        switchField(targetField);

    }

    public int generateRandomNumber(){
        int min = 2;
        int max = 63;

        return random.nextInt(max - min) + min;

    }

    public Field getNewFieldforWormholeSwitch(int value) {
        Field targetField = getFieldAtDistance(value, Color.BLACK);

        while (targetField instanceof StartingField || targetField instanceof GoalField || targetField.getCurrentFigure() != null) {
            value = generateRandomNumber();

            targetField = getFieldAtDistance(value, Color.BLACK);


        }
        return targetField;

    }

}
