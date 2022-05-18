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

    /**
     * A figure cannot be overtaken by another one (no matter which color),
     * if its current position is the own starting field.
     * Exception: Knight is allowed to overtake but has to consider degree.
     * @param figure1 - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    public boolean checkOvertaking(Figure figure1) { // fertig!
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (figure1.getTyp() == Typ.KNIGHT) {
            return true;
        } else {
            if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor()) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     *  A figure cannot be beaten by another one (no matter which color),
     *  if its current position is the own starting field or the own goal area.
     * @param figure1 - figure who moves
     * figure2 - figure to be beaten
     * @return true if beating is possible
     */
    public boolean checkBeaten(Figure figure1) { // fertig!
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor() || newPosition instanceof GoalField) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * A figure cannot be changed with another one (no matter which color),
     * if its current position is the own starting field or own goal area.
     * @param figure1 - figure who moves
     * @param fieldsToMove
     * @return true if moving is possible
     */
    public boolean checkMoving(Figure figure1, int fieldsToMove) { return true; } // TODO: Standardfall einbauen


    // Getter and Setter:

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
