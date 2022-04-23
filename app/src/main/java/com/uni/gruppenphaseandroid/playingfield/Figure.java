package com.uni.gruppenphaseandroid.playingfield;

public class Figure {
    private int id;
    private Color color;
    private Field currentPosition;
    private boolean inPlayingField;
    private boolean inStartingArea;
    private boolean inGoalField;


    public Figure(int id, Color color, Field currentPosition, boolean inPlayingField, boolean inStartField, boolean inGoalField) {
        this.id = id;
        this.color = color;
        this.currentPosition = currentPosition;
        this.inPlayingField = inPlayingField;
        this.inStartingArea = inStartField;
        this.inGoalField = inGoalField;
    }




    public Field move (Field field, int fieldsToMove) {
        // StartingArea[] und ZielArea[] einbauen => check wo Figur ist
        if (inStartingArea == true) {

        }

        if (inPlayingField == true) {
            Field newPosition = field.getFieldAtDistance(fieldsToMove, color);
            if (newPosition == null) {
                newPosition = currentPosition;
            } else {


                newPosition = currentPosition;
            }
        }
        return currentPosition;
    }



    public int getId() { return id; }

    public void setId(int id) { this.id = id++; } // Player 1 bekommt rot mit ID 1 - 4 etc....NOCH OFFEN, check mit Nermin!! + GUI

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public Field getCurrentPosition() { return currentPosition; }

    public void setCurrentPosition(Field currentPosition) { this.currentPosition = currentPosition; }

    public boolean isInPlayingField() { return inPlayingField; }

    public void setInPlayingField(boolean inPlayingField) { this.inPlayingField = inPlayingField; }

    public boolean isInStartingArea() { return inStartingArea; }

    public void setInStartingArea(boolean inStartingArea) { this.inStartingArea = inStartingArea; }

    public boolean isInGoalField() { return inGoalField; }

    public void setInGoalField(boolean inGoalField) { this.inGoalField = inGoalField; }
}
