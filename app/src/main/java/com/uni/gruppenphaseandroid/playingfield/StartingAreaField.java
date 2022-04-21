package com.uni.gruppenphaseandroid.playingfield;

public class StartingAreaField extends Field{

    private Color color;

    public StartingAreaField(FieldUI fieldUIobject, Field nextField, Field previousField, int fieldID, Color color) {
        super(fieldUIobject, nextField, previousField, fieldID);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
