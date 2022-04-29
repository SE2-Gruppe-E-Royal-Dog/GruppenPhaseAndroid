package com.uni.gruppenphaseandroid.playingfield;

public class Figure {
    private int id;
    private Color color;
    private Field currentField;

    public Figure(int id, Color color, Field currentField) {
        this.id = id;
        this.color = color;
        this.currentField = currentField;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id++; } // GUI fehlt

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public Field getCurrentField() { return currentField; }

    public void setCurrentField(Field currentField) { this.currentField = currentField; }
}
