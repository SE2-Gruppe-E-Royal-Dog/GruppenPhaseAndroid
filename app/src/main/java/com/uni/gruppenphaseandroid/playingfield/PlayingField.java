package com.uni.gruppenphaseandroid.playingfield;

import android.view.View;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.manager.LastTurn;

import java.util.ArrayList;

public class PlayingField {

    private Field rootField;

    private StartingField greenStartingField;
    private StartingField yellowStartingField;
    private StartingField redStartingField;
    private StartingField blueStartingField;
    private View view;
    private ArrayList<Wormhole> wormholeList;
    private Card card;

    public PlayingField(View view) {
        this.view = view;
        generateRegularFields();
        generateStartingFields();
        generateStartingAreaFields();
        generateGoalFields();
        generateWormholeFields();
    }

    private void generateRegularFields() {
        rootField = new Field(new FieldUIimpl(view), null, null, null, 1);
        rootField.getFieldUIobject().registerUIobject(generateRegularFieldTag(1));
        Field lastField = rootField;
        for (int i = 2; i <= 64; i++) {

            Field nextField = new Field(new FieldUIimpl(view), null, lastField, null, i);
            nextField.getFieldUIobject().registerUIobject(generateRegularFieldTag(i));
            lastField.setNextField(nextField);
            lastField = nextField;
        }
        lastField.setNextField(rootField);
        rootField.setPreviousField(lastField);
    }

    private void generateWormholeFields(){
        wormholeList = new ArrayList<>();

        for (int i = 0; i<4; i++){
            Field fieldToChange = rootField.getFieldAtDistance(6+16*i, Color.BLACK);
            Wormhole wormhole = new Wormhole(fieldToChange.getFieldUIobject(), null , null, fieldToChange.getCurrentFigure(), fieldToChange.getFieldID());
            fieldToChange.switchField(wormhole);

            wormholeList.add(wormhole);
        }

        wormholeList.get(0).setPartnerWormhole(wormholeList.get(1));
        wormholeList.get(1).setPartnerWormhole(wormholeList.get(0));
        wormholeList.get(2).setPartnerWormhole(wormholeList.get(3));
        wormholeList.get(3).setPartnerWormhole(wormholeList.get(2));


        /** für Testzwecke **/
        for (int j = 0; j<4; j++){
            wormholeList.get(j).moveWormholeToRandomPosition();
            repairRootField();
        }
    }

    private void generateStartingFields() {

        Field oldField = rootField.getFieldAtDistance(12, Color.GREEN);
        greenStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, null, oldField.getFieldID(), Color.GREEN);
        oldField.getNextField().setPreviousField(greenStartingField);
        oldField.getPreviousField().setNextField(greenStartingField);

        oldField = rootField.getFieldAtDistance(28, Color.YELLOW);
        yellowStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, null, oldField.getFieldID(), Color.YELLOW);
        oldField.getNextField().setPreviousField(yellowStartingField);
        oldField.getPreviousField().setNextField(yellowStartingField);

        oldField = rootField.getFieldAtDistance(44, Color.RED);
        redStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, null, oldField.getFieldID(), Color.RED);
        oldField.getNextField().setPreviousField(redStartingField);
        oldField.getPreviousField().setNextField(redStartingField);

        oldField = rootField.getFieldAtDistance(60, Color.BLUE);
        blueStartingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, null, oldField.getFieldID(), Color.BLUE);
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
            StartingAreaField startingAreaFieldGreen = new StartingAreaField(new FieldUIimpl(view), nextFieldGreen, null, null,greenID - i, Color.GREEN);
            startingAreaFieldGreen.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.GREEN, 4 - i));

            StartingAreaField startingAreaFieldYellow = new StartingAreaField(new FieldUIimpl(view), nextFieldYellow, null, null, yellowID - i, Color.YELLOW);
            startingAreaFieldYellow.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.YELLOW, 4 - i));

            StartingAreaField startingAreaFieldRed = new StartingAreaField(new FieldUIimpl(view), nextFieldRed, null, null,redID - i, Color.RED);
            startingAreaFieldRed.getFieldUIobject().registerUIobject(generateStartingAreaTag(Color.RED, 4 - i));

            StartingAreaField startingAreaFieldBlue = new StartingAreaField(new FieldUIimpl(view), nextFieldBlue, null, null,blueID - i, Color.BLUE);
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
            GoalField goalFieldGreen = new GoalField(new FieldUIimpl(view), null, previousGreenField, null, greenID + i, Color.GREEN);
            goalFieldGreen.getFieldUIobject().registerUIobject(generateGoalTag(Color.GREEN, i + 1));

            GoalField goalFieldYellow = new GoalField(new FieldUIimpl(view), null, previousYellowField, null,yellowID + i, Color.YELLOW);
            goalFieldYellow.getFieldUIobject().registerUIobject(generateGoalTag(Color.YELLOW, i + 1));

            GoalField goalFieldRed = new GoalField(new FieldUIimpl(view), null, previousRedField, null,redID + i, Color.RED);
            goalFieldRed.getFieldUIobject().registerUIobject(generateGoalTag(Color.RED, i + 1));

            GoalField goalFieldBlue = new GoalField(new FieldUIimpl(view), null, previousBlueField, null,blueID + i, Color.BLUE);
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

    public Field getRightStartingAreaField(Color color){
        Field startingAreaField = ((StartingField)getStartingFieldWithColor(color)).getPreviousStartingArea();

        while (startingAreaField != null){
            if(startingAreaField.getCurrentFigure() == null){
                break;
            }
            startingAreaField = startingAreaField.getPreviousField();
        }
        return startingAreaField;
    }

    public Field getStartingFieldWithColor(Color color){
        Field startingAreaField;
        if(color == Color.GREEN){
            startingAreaField = greenStartingField;
        } else if(color == Color.YELLOW){
            startingAreaField = yellowStartingField;
        }else if(color == Color.RED){
            startingAreaField = redStartingField;
        }else {
            startingAreaField = blueStartingField;
        }
        return startingAreaField;
    }

    public Field move (Figure figure1, int fieldsToMove) throws Exception { // TODO: Input von Karten: wie viel fahren
        Field newPositionFigure1 = figure1.getCurrentField().getFieldAtDistance(fieldsToMove, figure1.getColor());
        Figure figure2;

        try {
            if (newPositionFigure1.getCurrentFigure() != null) { // TODO: Checks für Goal Area
                figure2 = newPositionFigure1.getCurrentFigure();
                figure2.setCurrentField(getRightStartingAreaField(figure2.getColor()));
                figure2.getFigureUI().moveFigureToPosition(figure2.getCurrentField().getFieldUIobject()); // visual movement on board
            } else {
                figure2 = null;
            }
            figure1.getCurrentField().setCurrentFigure(null);
            newPositionFigure1.setCurrentFigure(figure1);
            figure1.setCurrentField(newPositionFigure1);
            figure1.getFigureUI().moveFigureToPosition(newPositionFigure1.getFieldUIobject()); // visual movement on board

            // TODO: Wurmlöcher einfügen
            // TODO: Schummeln einfügen
            // TODO: Karten: Figuren tauschen

            LastTurn lastTurn = new LastTurn(figure1, figure2, newPositionFigure1, figure2.getCurrentField(), fieldsToMove, card.getCardID());

            return newPositionFigure1;
        } catch (Exception e) {
            e.getMessage();
            return figure1.getCurrentField();
        }
    }

    public boolean checkMovingPossible (Figure figure, int fieldsToMove) { // TODO: Übergabe Kartenwert bei GameManager/KartenManager einbauen
        Field originField = figure.getCurrentField();

        for (int i = 0; i < fieldsToMove - 1; i++) {// TODO: Spezialfall CheckOvertaking wenn Goalfield erlauben?
            if (figure.getCurrentField().getNextField().getCurrentFigure() != null) {
                if (!checkOvertakingPossible(figure)) { // check if figure is allowed to overtake own figure
                    return false;
                }
            }
            if (figure.getCurrentField() instanceof StartingField) {
                if (((StartingField) figure.getCurrentField()).getColor() == figure.getColor()) {
                    GoalField goalfield = ((StartingField) figure.getCurrentField()).getNextGoalField();
                    if (fieldsToMove <= 4) {
                        figure.setCurrentField(goalfield);
                        continue;
                    }
                }
            }
            figure.setCurrentField(figure.getCurrentField().getFieldAtDistance(1, figure.getColor()));
        }
        figure.setCurrentField(originField);

        Field newPosition =  figure.getCurrentField().getFieldAtDistance(fieldsToMove, figure.getColor()); // TODO: Check ob newPosition ist Starting Field oder Goal Field => beaten not allowed!
        if (newPosition.getCurrentFigure() != null) { // TODO: Checks für Goal Area
            Figure beaten = newPosition.getCurrentFigure(); // figure was beaten and has to be set to Starting Area
            if (beaten.getTyp() == Typ.KING && figure.getTyp() != Typ.KING) {
                return false;
            }
        }
        return true;
    }

    public boolean checkOvertakingPossible (Figure figure) {
        return figure.checkOvertaking();
    }

    public boolean checkBeatenPossible (Figure figure) {
        return figure.checkBeaten();
    }

    public Field getFieldWithUI(int ID){
        if(ID < 1 || ID > 64){
            return null;
        }
        else {
            Field targetField = rootField;
            while(targetField.getFieldID() != ID){
                targetField = targetField.getNextField();
            }
            return targetField;
        }
    }

    public void repairRootField(){
        if(rootField.getFieldID() != 1){
            rootField = getFieldWithUI(1);
        }
    }
}
