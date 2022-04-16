package com.example.gruppenphaseandroid.playingfield;

import android.widget.ImageView;

import java.util.zip.Inflater;

public class PlayingField{

    private Field rootField;
    private StartingField blueStartingField;
    private StartingField redStartingField;
    private StartingField yellowStartingField;
    private StartingField greenStartingField;

    public PlayingField(){
        generateRegularFields();
        generateStartingFields();
        generateStartingAreaFields();
        generateGoalFields();
    }

    private void generateRegularFields(){
        Field rootField = new Field(new FieldUIimpl(), null, null, 1);
        rootField.getFieldUIobject().registerUIobject(generateRegularFieldTag(1));
        Field lastField = rootField;
        for(int i = 2; i <= 64;i++){

            Field nextField = new Field(null, null, lastField, i);
            nextField.getFieldUIobject().registerUIobject(generateRegularFieldTag(i));
            lastField.setNextField(nextField);
            lastField = nextField;
        }
        lastField.setNextField(rootField);
        rootField.setPreviousField(lastField);
    }

    private void generateStartingFields(){

        Field oldField = rootField.getFieldAtDistance(12, Color.GREEN);
        greenStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, oldField.getFieldID(), Color.GREEN);
        oldField.getNextField().setPreviousField(greenStartingField);
        oldField.getPreviousField().setNextField(greenStartingField);

        oldField = rootField.getFieldAtDistance(28, Color.YELLOW);
        yellowStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, oldField.getFieldID(), Color.YELLOW);
        oldField.getNextField().setPreviousField(yellowStartingField);
        oldField.getPreviousField().setNextField(yellowStartingField);

        oldField = rootField.getFieldAtDistance(44, Color.RED);
        redStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, oldField.getFieldID(), Color.RED);
        oldField.getNextField().setPreviousField(redStartingField);
        oldField.getPreviousField().setNextField(redStartingField);

        oldField = rootField.getFieldAtDistance(60, Color.BLUE);
        blueStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, oldField.getFieldID(), Color.BLUE);
        oldField.getNextField().setPreviousField(blueStartingField);
        oldField.getPreviousField().setNextField(blueStartingField);
    }
    private void generateStartingAreaFields(){


    }
    private void generateGoalFields(){

    }

    private String generateRegularFieldTag(int id){
        return "f" + id;
    }
    private String generateStartingAreaTag(Color color, int id){
        String tag;
        switch (color){
            case RED: tag = "rs"+id;
            break;
            case BLUE:tag = "bs"+id;
            break;
            case GREEN:tag="gs"+id;
            break;
            case YELLOW:tag="ys"+id;
            break;
            default: tag = "unknown";
        }
        return tag;
    }
    private String generateGoalTag(Color color, int id){
        String tag;
        switch (color){
            case RED: tag = "rg"+id;
                break;
            case BLUE:tag = "bg"+id;
                break;
            case GREEN:tag="gg"+id;
                break;
            case YELLOW:tag="yg"+id;
                break;
            default: tag = "unknown";
        }
        return tag;
    }
}
