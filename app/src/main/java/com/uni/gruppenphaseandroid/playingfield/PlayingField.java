package com.uni.gruppenphaseandroid.playingfield;

import android.view.View;

public class PlayingField {

    private Field rootField;
    private StartingField greenStartingField;
    private StartingField yellowStartingField;
    private StartingField redStartingField;
    private StartingField blueStartingField;
    private View view;

    public PlayingField(View view) {
        this.view = view;
        generateRegularFields();
        generateStartingFields();
        generateStartingAreaFields();
        generateGoalFields();
    }

    private void generateRegularFields() {
        rootField = new Field(new FieldUIimpl(view), null, null, 1);
        rootField.getFieldUIobject().registerUIobject(generateRegularFieldTag(1));
        Field lastField = rootField;
        for (int i = 2; i <= 64; i++) {

            Field nextField = new Field(new FieldUIimpl(view), null, lastField, i);
            nextField.getFieldUIobject().registerUIobject(generateRegularFieldTag(i));
            lastField.setNextField(nextField);
            lastField = nextField;
        }
        lastField.setNextField(rootField);
        rootField.setPreviousField(lastField);
    }

    private void generateStartingFields() {

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

    private void generateStartingAreaFields() {

        Field nextFieldGreen = greenStartingField;
        int greenID = 68;
        Field nextFieldYellow = yellowStartingField;
        int yellowID = 72;
        Field nextFieldRed = redStartingField;
        int redID = 76;
        Field nextFieldBlue = blueStartingField;
        int blueID = 80;

        for (int i = 0; i < 4; i++) {
            StartingAreaField startingAreaFieldGreen = new StartingAreaField(new FieldUIimpl(view), nextFieldGreen, null, greenID - i, Color.GREEN);
            startingAreaFieldGreen.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.GREEN, 4 - i));

            StartingAreaField startingAreaFieldYellow = new StartingAreaField(new FieldUIimpl(view), nextFieldYellow, null, yellowID - i, Color.YELLOW);
            startingAreaFieldYellow.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.YELLOW, 4 - i));

            StartingAreaField startingAreaFieldRed = new StartingAreaField(new FieldUIimpl(view), nextFieldRed, null, redID - i, Color.RED);
            startingAreaFieldRed.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.RED, 4 - i));

            StartingAreaField startingAreaFieldBlue = new StartingAreaField(new FieldUIimpl(view), nextFieldBlue, null, blueID - i, Color.BLUE);
            startingAreaFieldBlue.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.BLUE, 4 - i));

            if (i == 0) {
                greenStartingField.setPreviousStartingArea(startingAreaFieldGreen);
                yellowStartingField.setPreviousStartingArea(startingAreaFieldYellow);
                redStartingField.setPreviousStartingArea(startingAreaFieldRed);
                blueStartingField.setPreviousStartingArea(startingAreaFieldBlue);
            } else {
                nextFieldGreen.setPreviousField(startingAreaFieldGreen);
                nextFieldYellow.setPreviousField(startingAreaFieldYellow);
                nextFieldRed.setPreviousField(startingAreaFieldRed);
                nextFieldBlue.setPreviousField(startingAreaFieldBlue);
            }
            nextFieldGreen = startingAreaFieldGreen;
            nextFieldYellow = startingAreaFieldYellow;
            nextFieldRed = startingAreaFieldRed;
            nextFieldBlue = startingAreaFieldBlue;

        }
    }

    private void generateGoalFields() {
        Field previousGreenField = greenStartingField;
        int greenID = 81;
        Field previousYellowField = yellowStartingField;
        int yellowID = 85;
        Field previousRedField = redStartingField;
        int redID = 89;
        Field previousBlueField = blueStartingField;
        int blueID = 93;

        for (int i = 0; i < 4; i++) {
            GoalField goalFieldGreen = new GoalField(new FieldUIimpl(view), null, previousGreenField, greenID + i, Color.GREEN);
            goalFieldGreen.getFieldUIobject().registerUIobject(generateGoalTag(Color.GREEN, i + 1));

            GoalField goalFieldYellow = new GoalField(new FieldUIimpl(view), null, previousYellowField, yellowID + i, Color.YELLOW);
            goalFieldYellow.getFieldUIobject().registerUIobject(generateGoalTag(Color.YELLOW, i + 1));

            GoalField goalFieldRed = new GoalField(new FieldUIimpl(view), null, previousRedField, redID + i, Color.RED);
            goalFieldRed.getFieldUIobject().registerUIobject(generateGoalTag(Color.RED, i + 1));

            GoalField goalFieldBlue = new GoalField(new FieldUIimpl(view), null, previousBlueField, blueID + i, Color.BLUE);
            goalFieldBlue.getFieldUIobject().registerUIobject(generateGoalTag(Color.BLUE, i + 1));

            if (i == 0) {
                greenStartingField.setNextGoalField(goalFieldGreen);
                yellowStartingField.setNextGoalField(goalFieldYellow);
                redStartingField.setNextGoalField(goalFieldRed);
                blueStartingField.setNextGoalField(goalFieldBlue);
            } else {
                previousGreenField.setNextField(goalFieldGreen);
                previousYellowField.setNextField(goalFieldYellow);
                previousRedField.setNextField(goalFieldRed);
                previousBlueField.setNextField(goalFieldBlue);
            }
            previousGreenField = goalFieldGreen;
            previousYellowField = goalFieldYellow;
            previousRedField = goalFieldRed;
            previousBlueField = goalFieldBlue;
        }
    }

    private String generateRegularFieldTag(int id) {
        return "f" + id;
    }

    private String generateStartingAreaTag(Color color, int id) {
        String tag;
        switch (color) {
            case RED:
                tag = "rs" + id;
                break;
            case BLUE:
                tag = "bs" + id;
                break;
            case GREEN:
                tag = "gs" + id;
                break;
            case YELLOW:
                tag = "ys" + id;
                break;
            default:
                tag = "unknown";
        }
        return tag;
    }

    private String generateGoalTag(Color color, int id) {
        String tag;
        switch (color) {
            case RED:
                tag = "rg" + id;
                break;
            case BLUE:
                tag = "bg" + id;
                break;
            case GREEN:
                tag = "gg" + id;
                break;
            case YELLOW:
                tag = "yg" + id;
                break;
            default:
                tag = "unknown";
        }
        return tag;
    }

    public Field getRootField() {
        return rootField;
    }

    public StartingField getGreenStartingField() {
        return greenStartingField;
    }

    public StartingField getYellowStartingField() {
        return yellowStartingField;
    }

    public StartingField getRedStartingField() {
        return redStartingField;
    }

    public StartingField getBlueStartingField() {
        return blueStartingField;
    }
}