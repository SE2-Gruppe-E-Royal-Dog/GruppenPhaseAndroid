package com.uni.gruppenphaseandroid.playingfield;

public class StartingField extends Field{

    private GoalField nextGoalField;
    private StartingAreaField previousStartingArea;
    private Color color;

    public StartingField(FieldUI fieldUIobject, Field nextField, Field previousField, GoalField nextGoalField, StartingAreaField previousStartingArea, int fieldID, Color color) {
        super(fieldUIobject, nextField, previousField, fieldID);
        this.nextGoalField = nextGoalField;
        this.previousStartingArea = previousStartingArea;
        this.color = color;
    }

    public StartingField(){

    }

    @Override
    protected Field getFieldAtDistanceRecursive(int remainingDistance, Color color, Field originField) {
        if (originField != this && getColor() == color && remainingDistance > 0){//path toward goalFields
            Field destinationField = getNextGoalField().getFieldAtDistanceRecursive(remainingDistance -1, color, originField);
            if(destinationField != null){
                return destinationField;
            }
        }
        //regular path
        return super.getFieldAtDistanceRecursive(remainingDistance, color, originField);
    }

    public GoalField getNextGoalField() {
        return nextGoalField;
    }

    public void setNextGoalField(GoalField nextGoalField) {
        this.nextGoalField = nextGoalField;
    }

    public StartingAreaField getPreviousStartingArea() {
        return previousStartingArea;
    }

    public void setPreviousStartingArea(StartingAreaField previousStartingArea) {
        this.previousStartingArea = previousStartingArea;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
