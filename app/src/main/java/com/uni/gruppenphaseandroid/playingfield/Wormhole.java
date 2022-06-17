package com.uni.gruppenphaseandroid.playingfield;

import java.util.Random;

public class Wormhole extends Field {

    private final Random random = new Random();
    private Wormhole partnerWormhole;

    public Wormhole() {

    }

    public Wormhole(int fieldID){
        this.fieldID = fieldID;
    }

    @Override
    public void triggerSpecialFieldEffect() {
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
        partnerWormhole.partnerWormhole = this;
    }

    public Wormhole(FieldUI fieldUIObject, Field nextField, Field previousField, Figure currentFigure, int fieldID) {
        super(fieldUIObject, nextField, previousField, currentFigure, fieldID);
    }

    public void moveWormholeToRandomPosition() {
        int value = generateRandomNumber();
        Field targetField = getNewFieldForWormholeSwitch(value);

        switchField(targetField);
    }

    public int generateRandomNumber() {
        int min = 2;
        int max = 63;

        return random.nextInt(max - min) + min;
    }

    public Field getNewFieldForWormholeSwitch(int value) {
        Field targetField = getFieldAtDistance(value, Color.BLACK);

        while (targetField instanceof StartingField || targetField instanceof GoalField || targetField instanceof  Wormhole || targetField.getCurrentFigure() != null) {
            value = generateRandomNumber();

            targetField = getFieldAtDistance(value, Color.BLACK);
        }

        return targetField;

    }

}
