package com.uni.gruppenphaseandroid.playingfield;

import android.view.View;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.manager.GameManager;
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

    private void generateWormholeFields() {
        wormholeList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Field fieldToChange = rootField.getFieldAtDistance(6 + 16 * i, Color.BLACK);
            Wormhole wormhole = new Wormhole(fieldToChange.getFieldUIobject(), fieldToChange.getNextField(), fieldToChange.getPreviousField(), fieldToChange.getCurrentFigure(), fieldToChange.getFieldID());
            fieldToChange.getPreviousField().setNextField(wormhole);
            fieldToChange.getNextField().setPreviousField(wormhole);
            wormholeList.add(wormhole);
        }

        wormholeList.get(0).setPartnerWormhole(wormholeList.get(1));
        wormholeList.get(2).setPartnerWormhole(wormholeList.get(3));

        //  moveAllWormholesRandomly(); just show effect

    }

    private void generateStartingFields() {

        greenStartingField = generateSingleStartField(12, Color.GREEN);
        yellowStartingField = generateSingleStartField(28, Color.YELLOW);
        redStartingField = generateSingleStartField(44, Color.RED);
        blueStartingField = generateSingleStartField(60, Color.BLUE);
    }

    private StartingField generateSingleStartField(int distanceFromStart, Color color) {
        Field oldField = rootField.getFieldAtDistance(distanceFromStart, color);
        StartingField startingField = new StartingField(oldField.getFieldUIobject(), oldField.getNextField(), oldField.getPreviousField(), null, null, null, oldField.getFieldID(), color);
        oldField.getNextField().setPreviousField(startingField);
        oldField.getPreviousField().setNextField(startingField);
        oldField.setNextField(null);
        oldField.setPreviousField(null);

        return startingField;
    }

    private void generateStartingAreaFields() {

        generateStartingAreasOfColor(greenStartingField, 68, Color.GREEN);
        generateStartingAreasOfColor(yellowStartingField, 72, Color.YELLOW);
        generateStartingAreasOfColor(redStartingField, 76, Color.RED);
        generateStartingAreasOfColor(blueStartingField, 80, Color.BLUE);
    }

    private void generateStartingAreasOfColor(Field startingField, int id, Color color) {

        Field nextField = startingField;

        for (int i = 0; i < 4; i++) {
            StartingAreaField startingAreaField = new StartingAreaField(new FieldUIimpl(view), nextField, null, null, id - i, color);
            startingAreaField.getFieldUIobject().registerUIobject(generateStartingAreaTag(color, 4 - i));
            if (i == 0) {
                ((StartingField) startingField).setPreviousStartingArea(startingAreaField);
            } else {
                nextField.setPreviousField(startingAreaField);
            }
            nextField = startingAreaField;
        }
    }

    private void generateGoalFields() {

        generateGoalFieldsOfColor(greenStartingField, 81, Color.GREEN);
        generateGoalFieldsOfColor(yellowStartingField, 85, Color.YELLOW);
        generateGoalFieldsOfColor(redStartingField, 89, Color.RED);
        generateGoalFieldsOfColor(blueStartingField, 93, Color.BLUE);
    }

    private void generateGoalFieldsOfColor(Field startingField, int id, Color color) {
        Field previousField = startingField;
        for (int i = 0; i < 4; i++) {
            GoalField goalField = new GoalField(new FieldUIimpl(view), null, previousField, null, id + i, color);
            goalField.getFieldUIobject().registerUIobject(generateGoalTag(color, i + 1));
            if (i == 0) {
                ((StartingField) startingField).setNextGoalField(goalField);
            } else {
                previousField.setNextField(goalField);
            }

            previousField = goalField;
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

    public Field getRightStartingAreaField(Color color) {
        Field startingAreaField = ((StartingField) getStartingFieldWithColor(color)).getPreviousStartingArea();

        while (startingAreaField != null) {
            if (startingAreaField.getCurrentFigure() == null) {
                break;
            }
            startingAreaField = startingAreaField.getPreviousField();
        }
        return startingAreaField;
    }

    public Field getStartingFieldWithColor(Color color) {
        Field startingAreaField;
        if (color == Color.GREEN) {
            startingAreaField = greenStartingField;
        } else if (color == Color.YELLOW) {
            startingAreaField = yellowStartingField;
        } else if (color == Color.RED) {
            startingAreaField = redStartingField;
        } else {
            startingAreaField = blueStartingField;
        }
        return startingAreaField;
    }

    public Field moveToStart(Figure figure) {
        Field newField;
        switch (figure.getColor()) {
            case BLUE:
                newField = blueStartingField;
                break;
            case RED:
                newField = redStartingField;
                break;
            case GREEN:
                newField = greenStartingField;
                break;
            case YELLOW:
                newField = yellowStartingField;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + figure.getColor());
        }

        Figure figureToOvertake = newField.getCurrentFigure();
        if (figureToOvertake != null) {
            beat(figureToOvertake);
        }

        figure.getCurrentField().setCurrentFigure(null);
        newField.setCurrentFigure(figure);
        figure.setCurrentField(newField);
        figure.getFigureUI().moveFigureToPosition(newField.getFieldUIobject());
        newField.triggerSpecialFieldEffect();

        LastTurn lastTurn = new LastTurn(figure, figureToOvertake, figure.getCurrentField(), (figureToOvertake != null) ? figureToOvertake.getCurrentField() : null);
        GameManager.getInstance().setLastTurn(lastTurn);

        return newField;
    }

    public Field switchPositions(Figure figure1, Figure figure2) {
        Field field1 = figure1.getCurrentField();
        Field field2 = figure2.getCurrentField();

        figure1.setCurrentField(field2);
        field2.setCurrentFigure(figure1);
        figure1.getFigureUI().moveFigureToPosition(field2.getFieldUIobject());

        figure2.setCurrentField(field1);
        field1.setCurrentFigure(figure2);
        figure2.getFigureUI().moveFigureToPosition(field1.getFieldUIobject());

        handleWormhole(true, field2, null);
        handleWormhole(true, field1, null);

        LastTurn lastTurn = new LastTurn(figure1, figure2, figure1.getCurrentField(), figure2.getCurrentField());
        GameManager.getInstance().setLastTurn(lastTurn);
        return figure1.getCurrentField();
    }

    public Field moveToNextFigure(Figure myFigure) {
        Field current = myFigure.getCurrentField();

        while (current.getNextField().getCurrentFigure() == null) {
            current = current.getNextField();
        }
        myFigure.getCurrentField().setCurrentFigure(null);
        current.setCurrentFigure(myFigure);
        myFigure.setCurrentField(current);
        myFigure.getFigureUI().moveFigureToPosition(current.getFieldUIobject());

        LastTurn lastTurn = new LastTurn(myFigure, null, myFigure.getCurrentField(), null);
        GameManager.getInstance().setLastTurn(lastTurn);
        return current;
    }

    public void move(Figure figure1, int fieldsToMove) {
        Field newPositionFigure1 = setNewPosition(figure1, fieldsToMove); // includes all checks for moving to new Position incl. new position
        Figure figure2;

        newPositionFigure1 = applyCheatModifier(newPositionFigure1, figure1.getColor());

        figure2 = newPositionFigure1.getCurrentFigure();
        if (figure2 != null) {
            beat(figure2);
        }

        figure1.getCurrentField().setCurrentFigure(null);
        newPositionFigure1.setCurrentFigure(figure1);
        figure1.setCurrentField(newPositionFigure1);
        figure1.getFigureUI().moveFigureToPosition(newPositionFigure1.getFieldUIobject()); // visual movement on board

        LastTurn lastTurn = new LastTurn();
        handleWormhole((figure2 != null), newPositionFigure1, lastTurn);

        lastTurn.setFigure1(figure1);
        lastTurn.setNewFigure1Field(figure1.getCurrentField());
        if (lastTurn.getFigure2() == null){
            lastTurn.setFigure2(figure2);
            lastTurn.setNewFigure2Field(figure2 != null ? figure2.getCurrentField() : null);
        }
        lastTurn.setCheatModifier(GameManager.getInstance().getCheatModifier());
        GameManager.getInstance().setLastTurn(lastTurn);


    }

    private Field setNewPosition(Figure figure, int fieldsToMove) { // includes all checks for overtaking, moving, beaten
        return figure.setNewPosition(fieldsToMove);
    }

    public void moveAllWormholesRandomly() {
        for (int j = 0; j < 4; j++) {
            wormholeList.get(j).moveWormholeToRandomPosition();
            repairRootField();
        }

        repairWormholeVisuals();
    }

    public Field getFieldWithID(int id) {
        if (id < 1 || id > 96) {
            return null;
        } else {
            Field targetField = rootField;
            while (targetField.getFieldID() != id) {
                targetField = targetField.getNextField();
                if (targetField instanceof StartingField) {
                    Field goalOrStartingField = getFieldWithStartingAndGoalID((StartingField) targetField, id);
                    if (goalOrStartingField != null) {
                        return goalOrStartingField;
                    }
                }
            }
            return targetField;
        }
    }

    public void repairRootField() {
        if (rootField.getFieldID() != 1) {
            rootField = getFieldWithID(1);
        }
    }

    public void repairWormholeVisuals() {
        for (int i = 1; i <= 64; i++) {
            if (getFieldWithID(i).getClass() != Wormhole.class) {
                getFieldWithID(i).getFieldUIobject().turnIntoRegularField();
            }
        }
    }

    private Field getFieldWithStartingAndGoalID(StartingField startingField, int id) {
        Field goalField = startingField.getNextGoalField();
        Field startingAreaField = startingField.getPreviousStartingArea();
        for (int i = 0; i < 4; i++) {
            if (goalField.getFieldID() == id) {
                return goalField;
            }
            if (startingAreaField.getFieldID() == id) {
                return startingAreaField;
            }
            goalField = goalField.getNextField();
            startingAreaField = startingAreaField.getPreviousField();
        }
        return null;
    }

    public ArrayList<Wormhole> getWormholeList() {
        return wormholeList;
    }


    public View getView() {
        return view;
    }

    public void moveFigureToField(Figure figure, Field field) {
        figure.getCurrentField().setCurrentFigure(null);
        figure.setCurrentField(field);
        field.setCurrentFigure(figure);
        figure.getFigureUI().moveFigureToPosition(field.getFieldUIobject());
    }

    private Field applyCheatModifierForStartingField(StartingField startingField, Color figureColor) {
        if (GameManager.getInstance().getCheatModifier() == 1) {
            if (startingField.getColor() == figureColor && startingField.getNextGoalField().getCurrentFigure() == null) {
                return startingField.getNextGoalField();
            } else {
                return startingField.getNextField();
            }
        } else { //CheatModifier == -1
            return startingField.getPreviousField();
        }
    }

    private Field applyCheatModifierForGoalField(Field field) {
        if (GameManager.getInstance().getCheatModifier() == -1) {
            return field.getPreviousField();
        } else { // CheatModifier == 1
            if (field.getNextField() == null) {
                return field;
            } else { //CheatModifier == -1
                return field.getNextField();
            }
        }
    }

    public Field applyCheatModifier(Field field, Color figureColor) {
        if (GameManager.getInstance().getCheatModifier() == 0) {
            return field;
        }
        GameManager.getInstance().setHasCheated(true);
        if (field instanceof StartingField) {
            return applyCheatModifierForStartingField((StartingField) field, figureColor);
        } else if (field instanceof GoalField) {
            return applyCheatModifierForGoalField(field);
        } else { // Field is regular field
            return field.getFieldAtDistance(GameManager.getInstance().getCheatModifier(), Color.BLACK);
        }
    }

    public void beat(Figure figureToBeat) {
        figureToBeat.getCurrentField().setCurrentFigure(null);
        Field startingAreaField = getRightStartingAreaField(figureToBeat.getColor());
        figureToBeat.setCurrentField(startingAreaField);
        startingAreaField.setCurrentFigure(figureToBeat);
        figureToBeat.getFigureUI().moveFigureToPosition(figureToBeat.getCurrentField().getFieldUIobject()); // visual movement on board
    }

    private void handleWormhole(boolean hasAnotherFigureMovedInThisTurn, Field field, LastTurn lastTurn) {
        if (field instanceof Wormhole) {
            if(!hasAnotherFigureMovedInThisTurn || (((Wormhole) field).getPartnerWormhole().getCurrentFigure() == null)) {
                field.triggerSpecialFieldEffect();
                if (lastTurn != null) { // switch figure operation will sends empty last turn to this methode
                    lastTurn.setFigure2(field.getCurrentFigure());
                    lastTurn.setNewFigure2Field(field.getCurrentFigure() != null ? field : null);
                }
            } else {
                field.triggerSpecialFieldEffect();
            }


        }
    }
}




