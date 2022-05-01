package com.uni.gruppenphaseandroid.playingfield;

public class Figure {
    private int id;
    private Color color;
    private Field currentField;
    private PlayingField playingField;

    private Field wormholeField_1;
    private Field wormholeField_2;
    private Field worhmholeField_3;
    private Field worhmholeField_4;

    public Figure(int id, Color color, Field currentField, PlayingField playingField) {
        this.id = id;
        this.color = color;
        this.currentField = currentField;
        this.playingField = playingField;
    }

    public Figure(){

    }

    public Field move (int fieldsToMove) { // Input von Karten: wie viel fahren
        Field newPosition = currentField.getFieldAtDistance(fieldsToMove, color);
            if (newPosition.getCurrentFigure() != null) {
                Figure beaten = newPosition.getCurrentFigure(); // figure was beaten and has to be set to Starting Area
                beaten.currentField = playingField.getRightStartingAreaField(beaten.color);
            }
            newPosition.setCurrentFigure(this);
            this.currentField = newPosition;

            if(currentField ==  wormholeField_1) {
                currentField = wormholeField_2;
                if(currentField ==  worhmholeField_3){
                    currentField = worhmholeField_4;
                }
            }

        return currentField;
    }





    public Field getRightStartingArea (Field field) { // if figure is beaten: find an empty space in the right Starting Area
        int id;
        switch (color) {
            case GREEN: id = 68;
                break;
            case YELLOW: id = 72;
                break;
            case RED: id = 76;
                break;
            case BLUE: id = 80;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + color);
        }
        for (int i = 0 ; i < 4; i++) {
            field.setFieldID(id);
            if (field.getCurrentFigure() == null) {
                field.setCurrentFigure(this);
            } else {
                field.getFieldAtDistance(1,color);
            }
        }
        return field;
    }

    public Field getRightGoalArea (Field field) { // if figure finished: find an empty space in the right Goal Area
        int id;
        switch (color) {
            case GREEN: id = 81;
                break;
            case YELLOW: id = 85;
                break;
            case RED: id = 89;
                break;
            case BLUE: id = 93;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + color);
        }
        return currentField;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id++; } // GUI fehlt

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

}
