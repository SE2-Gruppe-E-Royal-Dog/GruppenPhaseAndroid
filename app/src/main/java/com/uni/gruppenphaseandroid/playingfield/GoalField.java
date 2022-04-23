package com.uni.gruppenphaseandroid.playingfield;

public class GoalField extends Field{

    private Color color;

    public GoalField(FieldUI fieldUIobject, Field nextField, Field previousField, Figure currentFigure, int fieldID, Color color) {
        super(fieldUIobject, nextField, previousField, currentFigure, fieldID);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
