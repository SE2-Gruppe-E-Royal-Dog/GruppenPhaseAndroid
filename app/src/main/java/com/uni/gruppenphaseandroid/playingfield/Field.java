package com.uni.gruppenphaseandroid.playingfield;

import java.util.Random;

public class Field {

    private Field nextField;
    private Field previousField;
    private int fieldID;
    private Figure currentFigure;
    private FieldUI fieldUIobject;






    public Field(FieldUI fieldUIobject, Field nextField, Field previousField, Figure currentFigure, int fieldID){
        this.fieldUIobject = fieldUIobject;
        this.nextField = nextField;
        this.previousField = previousField;
        this.currentFigure = currentFigure;
        this.fieldID = fieldID;

    }

    public Field(){

    }

    protected void triggerSpecialFieldEffect(){ }

    public Field getFieldAtDistance(int distance, Color color){
        return getFieldAtDistanceRecursive(distance, color, this);
    }

    protected Field getFieldAtDistanceRecursive(int remainingDistance, Color color, Field originField){
        if(remainingDistance == 0){
            return this;
        }
        else if(remainingDistance < 0){
            return getPreviousField().getFieldAtDistanceRecursive(remainingDistance+1, color, originField);
        }
        else {//remainingFields > 0
            return getNextField().getFieldAtDistanceRecursive(remainingDistance-1, color, originField);
        }
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

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) { this.fieldID = fieldID; }

    public Figure getCurrentFigure() { return currentFigure; }

    public void setCurrentFigure(Figure currentFigure) { this.currentFigure = currentFigure; }

    public void switchField(Field targetField){

        Field currentNextField = getNextField();
        Field currentPreviousField = getPreviousField();
        Field targetNextField = targetField.getNextField();
        Field targetPreviousField = targetField.getPreviousField();

        targetField.setNextField(currentNextField);
        targetField.setPreviousField(currentPreviousField);

        setNextField(targetNextField);
        setPreviousField(targetPreviousField);

        currentNextField.setPreviousField(targetField);
        currentPreviousField.setNextField(targetField);


        if (targetNextField != null && targetPreviousField != null) {
            targetNextField.setPreviousField(this);
            targetPreviousField.setNextField(this);
        }

   //     fieldUIobject.switchFieldUI(targetField.getFieldUIobject());

        int helpID = targetField.getFieldID();
        targetField.setFieldID(getFieldID());
        setFieldID(helpID);

    }





    }


