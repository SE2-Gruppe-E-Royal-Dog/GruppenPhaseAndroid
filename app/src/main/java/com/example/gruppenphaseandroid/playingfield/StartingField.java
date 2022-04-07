package com.example.gruppenphaseandroid.playingfield;

public class StartingField extends Field{

    private GoalField nextGoalField;
    private StartingAreaField previousStartingArea;

    public StartingField(FieldUI fieldUIobject, Field nextField, Field previousField, GoalField nextGoalField, StartingAreaField previousStartingArea) {
        super(fieldUIobject, nextField, previousField);
        this.nextGoalField = nextGoalField;
        this.previousStartingArea = previousStartingArea;
    }

    public StartingField(){

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
}
