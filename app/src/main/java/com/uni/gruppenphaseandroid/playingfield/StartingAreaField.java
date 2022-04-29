package com.uni.gruppenphaseandroid.playingfield;

public class StartingAreaField extends Field{

    private Color color;

    public StartingAreaField(FieldUI fieldUIobject, Field nextField, Field previousField, Figure currentFigure, int fieldID, Color color) {
        super(fieldUIobject, nextField, previousField, currentFigure, fieldID);
        this.color = color;
    }

    public StartingAreaField(){

    }

    @Override
    protected Field getFieldAtDistanceRecursive(int remainingDistance, Color color, Field originField) {
        if(remainingDistance == 0){
            return this;
        }
        else if(remainingDistance < 0){
            if(getPreviousField() == null){
                return null;
            }else{
                return getPreviousField().getFieldAtDistanceRecursive(remainingDistance+1, color, originField);
            }
        }
        else {//remainingFields > 0
            return getNextField().getFieldAtDistanceRecursive(remainingDistance-1, color, originField);

        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
