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

    public void moveWormholeToRandomPosition(Wormhole wormhole){
        int min = 1;
        int max = 64;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;


        wormhole.getFieldAtDistance(value, Color.BLACK);
      if ( wormhole instanceof GoalField) {
          Random random1 = new Random();
          int value1 = random.nextInt(max + min) + min;
          wormhole.getFieldAtDistance(value1, Color.BLACK);
          else if (wormhole instanceof StartingField){
              Random random2 = new Random();
              int value2 = random.nextInt(max + min) + min;
              wormhole.getFieldAtDistance(value2, Color.BLACK);
          } else {
              Field helpField = nextField;
              nextField = previousField;
              previousField = helpField;
          }
      }


        
    }

}
