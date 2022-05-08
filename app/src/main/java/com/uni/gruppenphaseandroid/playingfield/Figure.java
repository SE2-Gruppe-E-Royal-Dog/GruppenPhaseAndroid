package com.uni.gruppenphaseandroid.playingfield;

public class Figure {
    private int id;
    private Color color;
    private Field currentField;
    private Typ typ;
    private FigureUI figureUI;

    public Figure(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        this.id = id;
        this.color = color;
        this.currentField = currentField;
        currentField.setCurrentFigure(this);
        this.typ = typ;
        this.figureUI = figureUI;
    }

    public Figure() {

    }

    public boolean checkOvertaking() {
        return true;
    } // TODO: grüne Karte => overtaking Regeln außer Kraft gesetzt

    public boolean checkBeaten() { return true; } //TODO: König kann nur von KÖnig geschlagen werden

    public boolean checkMoving() { return true; }


    // Getter and Setter

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

    public FigureUI getFigureUI() {
        return figureUI;
    }

    public void setFigureUI(FigureUI figureUI) {
        this.figureUI = figureUI;
    }

}
