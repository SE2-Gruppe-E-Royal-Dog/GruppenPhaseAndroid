package com.uni.gruppenphaseandroid.playingfield;

public class Figure {
    private int id;
    private Color color;
    private Field currentField;
    private Typ typ;

    public Figure(int id, Color color, Field currentField, Typ typ) {
        this.id = id;
        this.color = color;
        this.currentField = currentField;
        this.typ = typ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id++;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }
}
