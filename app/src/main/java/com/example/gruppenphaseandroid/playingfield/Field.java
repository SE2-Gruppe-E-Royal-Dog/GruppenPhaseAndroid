package com.example.gruppenphaseandroid.playingfield;

public class Field {

    private Field nextField;
    private Field previousField;

    //private Figure currentFigure
    private FieldUI fieldUIobject;

    public Field(FieldUI fieldUIobject, Field nextField, Field previousField){
        this.fieldUIobject = fieldUIobject;
        this.nextField = nextField;
        this.previousField = previousField;
    }

    public Field(){

    }

    protected void triggerSpecialFieldEffect(){ }

    public void moveFigureToNextField(int numberOfStepsLeft){

        //TODO: move figure
    }

    public Field getNextField() {
        return nextField;
    }

    public void setNextField(Field nextField) {
        this.nextField = nextField;
    }

    public Field getPreviousField() {
        return previousField;
    }

    public void setPreviousField(Field previousField) {
        this.previousField = previousField;
    }

    public FieldUI getFieldUIobject() {
        return fieldUIobject;
    }

    public void setFieldUIobject(FieldUI fieldUIobject) {
        this.fieldUIobject = fieldUIobject;
    }
}
