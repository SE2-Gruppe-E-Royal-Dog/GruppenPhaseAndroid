package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;

public class LastTurn {

    private Figure figure1;
    private Figure figure2;
    private Field newFigure1Field;
    private Field newFigure2Field;
    private int distanceMovedByFigure1;
    private Cardtype cardtype;

    public String generateServerMessage(){
        String figure2ID = (figure2 == null)?"-1":""+figure2.getId();
        String newFigure2FieldID = (newFigure2Field == null)?"-1":""+newFigure2Field.getFieldID();

        return figure1.getId() + "_" + newFigure1Field.getFieldID() + "_" + figure2ID + "_" + newFigure2FieldID + "_" + cardtype.ordinal();
    }

    public LastTurn(Figure figure1, Figure figure2, Field newFigure1Field, Field newFigure2Field, int distanceMovedByFigure1, Cardtype cardtype) {
        this.figure1 = figure1;
        this.figure2 = figure2;
        this.newFigure1Field = newFigure1Field;
        this.newFigure2Field = newFigure2Field;
        this.distanceMovedByFigure1 = distanceMovedByFigure1;
        this.cardtype = cardtype;
    }

    public Figure getFigure1() {
        return figure1;
    }

    public void setFigure1(Figure figure1) {
        this.figure1 = figure1;
    }

    public Figure getFigure2() {
        return figure2;
    }

    public void setFigure2(Figure figure2) {
        this.figure2 = figure2;
    }

    public Field getNewFigure1Field() {
        return newFigure1Field;
    }

    public void setNewFigure1Field(Field newFigure1Field) {
        this.newFigure1Field = newFigure1Field;
    }

    public Field getNewFigure2Field() {
        return newFigure2Field;
    }

    public void setNewFigure2Field(Field newFigure2Field) {
        this.newFigure2Field = newFigure2Field;
    }

    public int getDistanceMovedByFigure1() {
        return distanceMovedByFigure1;
    }

    public void setDistanceMovedByFigure1(int distanceMovedByFigure1) {
        this.distanceMovedByFigure1 = distanceMovedByFigure1;
    }

}
