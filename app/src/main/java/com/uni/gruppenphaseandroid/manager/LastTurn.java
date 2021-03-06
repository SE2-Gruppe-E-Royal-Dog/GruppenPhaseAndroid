package com.uni.gruppenphaseandroid.manager;

import com.google.gson.Gson;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.dto.Message;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.communication.dto.UpdateBoardPayload;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class LastTurn {

    private Figure figure1;
    private Figure figure2;
    private Field newFigure1Field;
    private Field newFigure2Field;
    private Cardtype cardtype;
    private int cheatModifier = 0;

    public Message generateServerMessage(String lobbyID, String playerID) {
        Message message = new Message();
        message.setType(MessageType.UPDATE_BOARD);
        int figure2ID = (figure2 == null) ? -1 : figure2.getId();
        int newFigure2FieldID = (newFigure2Field == null) ? -1 : newFigure2Field.getFieldID();
        var payload = new UpdateBoardPayload(figure1.getId(), figure2ID, newFigure1Field.getFieldID(), newFigure2FieldID, cardtype.ordinal(), cheatModifier, lobbyID, playerID);
        message.setPayload(new Gson().toJson(payload));
        return message;
    }

    public LastTurn(Figure figure1, Figure figure2, Field newFigure1Field, Field newFigure2Field) {
        this.figure1 = figure1;
        this.figure2 = figure2;
        this.newFigure1Field = newFigure1Field;
        this.newFigure2Field = newFigure2Field;
    }

    public LastTurn(){}

    public static LastTurn generateLastTurnObject(UpdateBoardPayload updateBoardPayload, FigureManager figureManager, PlayingField playingField){
        Figure figure1 = figureManager.getFigureWithID(updateBoardPayload.getFigure1ID());
        Figure figure2 = (updateBoardPayload.getFigure2ID() == -1) ? null : figureManager.getFigureWithID(updateBoardPayload.getFigure2ID());
        Field figure1newField = playingField.getFieldWithID(updateBoardPayload.getNewField1ID());
        Field figure2newField = (updateBoardPayload.getNewField2ID() == -1) ? null : playingField.getFieldWithID(updateBoardPayload.getNewField2ID());

        LastTurn lastTurn = new LastTurn(figure1, figure2, figure1newField, figure2newField);
        lastTurn.setCardtype(Cardtype.values()[updateBoardPayload.getCardType()]);
        return lastTurn;
    }

    public Figure getFigure1() {
        return figure1;
    }

    public void setFigure1(Figure figure1) {
        this.figure1 = figure1;
    }

    public Figure getFigure2() {
        return figure2;
    }

    public void setFigure2(Figure figure2) {
        this.figure2 = figure2;
    }

    public Field getNewFigure1Field() {
        return newFigure1Field;
    }

    public void setNewFigure1Field(Field newFigure1Field) {
        this.newFigure1Field = newFigure1Field;
    }

    public Field getNewFigure2Field() {
        return newFigure2Field;
    }

    public void setNewFigure2Field(Field newFigure2Field) {
        this.newFigure2Field = newFigure2Field;
    }
    public Cardtype getCardtype() {
        return cardtype;
    }

    public void setCardtype(Cardtype cardtype) {
        this.cardtype = cardtype;
    }

    public int getCheatModifier() {
        return cheatModifier;
    }

    public void setCheatModifier(int cheatModifier) {
        this.cheatModifier = cheatModifier;
    }
}
