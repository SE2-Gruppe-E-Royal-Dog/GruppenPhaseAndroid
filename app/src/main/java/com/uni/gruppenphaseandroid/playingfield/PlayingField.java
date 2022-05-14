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
            Wormhole wormhole = new Wormhole(fieldToChange.getFieldUIobject(), fieldToChange.getNextField() , fieldToChange.getPreviousField(), fieldToChange.getCurrentFigure(), fieldToChange.getFieldID());
            fieldToChange.getPreviousField().setNextField(wormhole);
            fieldToChange.getNextField().setPreviousField(wormhole);
            wormholeList.add(wormhole);
        }

        wormholeList.get(0).setPartnerWormhole(wormholeList.get(1));
        wormholeList.get(1).setPartnerWormhole(wormholeList.get(0));
        wormholeList.get(2).setPartnerWormhole(wormholeList.get(3));
        wormholeList.get(3).setPartnerWormhole(wormholeList.get(2));

      //  moveAllWormholesRandomly(); just show effect

    }

    private void generateStartingFields() {

        greenStartingField = generateSingleStartField(12, Color.GREEN);
        yellowStartingField = generateSingleStartField(28, Color.YELLOW);
        redStartingField = generateSingleStartField(44, Color.RED);
        blueStartingField = generateSingleStartField(60, Color.BLUE);
    }

    private StartingField generateSingleStartField(int distanceFromStart, Color color ){
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

    private void generateStartingAreasOfColor(Field startingField, int id, Color color){

        Field nextField = startingField;

        for(int i = 0; i < 4;i++){
            StartingAreaField startingAreaField = new StartingAreaField(new FieldUIimpl(view), nextField, null, null,id - i, color);
            startingAreaField.getFieldUIobject().registerUIobject(generateStartingAreaTag(color, 4 - i));
            if(i==0){
                ((StartingField)startingField).setPreviousStartingArea(startingAreaField);
            }
            else{
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



    private void generateGoalFieldsOfColor(Field startingField, int id, Color color){
        Field previousField = startingField;
        for(int i = 0; i < 4; i++){
            GoalField goalField = new GoalField(new FieldUIimpl(view), null, previousField, null, id + i, color);
            goalField.getFieldUIobject().registerUIobject(generateGoalTag(color, i + 1));
            if(i==0){
                ((StartingField)startingField).setNextGoalField(goalField);
            }else{
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
            newPositionFigure1.triggerSpecialFieldEffect();

            // TODO: Wurmlöcher einfügen
            // TODO: Schummeln einfügen
            // TODO: Karten: Figuren tauschen

            LastTurn lastTurn = new LastTurn(figure1, figure2, newPositionFigure1, figure2.getCurrentField(), fieldsToMove);

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

    public boolean checkOvertakingPossible (Figure figure1) {
        if (checkGreenCard(card)) {
            return true;
        } else {
            return figure1.checkOvertaking(figure1);
        }
    }

    public boolean checkGreenCard(Card card) {
        //if (card.getColor() == GREEN) { // TODO: Farbe Karte einbauen
            //return true;
        //} else {
            return false;
        //}

    }

    public boolean checkBeatenPossible (Figure figure1) {
        return figure1.checkBeaten(figure1);
    }

    public void moveAllWormholesRandomly(){
        for (int j = 0; j<4; j++){
            wormholeList.get(j).moveWormholeToRandomPosition();
            repairRootField();
        }

        repairWormholeVisuals();
    }

    public Field getFieldWithID(int ID){
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
            rootField = getFieldWithID(1);
        }
    }

    public void repairWormholeVisuals(){
        for(int i = 1; i <=64;i++){
            if(getFieldWithID(i).getClass() != Wormhole.class){
                getFieldWithID(i).getFieldUIobject().turnIntoRegularField();
            }
        }
    }

    public ArrayList<Wormhole> getWormholeList() {
        return wormholeList;
    }




    public View getView() {
        return view;
    }

}
