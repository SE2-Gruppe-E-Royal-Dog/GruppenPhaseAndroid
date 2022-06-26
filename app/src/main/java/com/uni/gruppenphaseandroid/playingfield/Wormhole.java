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

    /**
     * hier wird die figur auf das PartnerWormhole gesetzt und es wird geprüft ob das PartnerWormhole frei ist.
     * Ist es frei, dann wird einfach ein neue FieldUIObject dort platziert und wenn es besetzt ist, dann
     * wird die darauf befindliche Figure auf das aktuelle Wormhole (auf welches jemand gefahren ist ersetzt)
     *
     */

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

    /**
     * Wormhole wird erzeugt
     */
    public Wormhole(FieldUI fieldUIObject, Field nextField, Field previousField, Figure currentFigure, int fieldID) {
        super(fieldUIObject, nextField, previousField, currentFigure, fieldID);
    }

    /** die Methode macht den eigentlichen random move,
     * zwecks Testbarkeit habe ich diese in weitere Methoden unterteilt
     */
    public void moveWormholeToRandomPosition() {
        int value = generateRandomNumber();
        Field targetField = getNewFieldForWormholeSwitch(value);

        switchField(targetField);
    }

    /** hier wird die Nummer für die Random Verschiebung erzeugt
     */
    public int generateRandomNumber() {
        int min = 2;
        int max = 63;

        return random.nextInt(max - min) + min;
    }

    /**
     * hier referenzieren wir auf ein neues Feld um das Wormhole hinzusetzten
     * in der While erfolgen die Prüfungen über Startfeld, Zielfeld, oder ob das Feld von einer anderen Figur besetzt wird
     * ist dies der Fall wird nochmal random getauscht, bis ein passendes Feld gefunden wurde
     */
    public Field getNewFieldForWormholeSwitch(int value) {
        Field targetField = getFieldAtDistance(value, Color.BLACK);

        while (targetField instanceof StartingField || targetField instanceof GoalField || targetField instanceof  Wormhole || targetField.getCurrentFigure() != null) {
            value = generateRandomNumber();

            targetField = getFieldAtDistance(value, Color.BLACK);
        }

        return targetField;

    }

}
