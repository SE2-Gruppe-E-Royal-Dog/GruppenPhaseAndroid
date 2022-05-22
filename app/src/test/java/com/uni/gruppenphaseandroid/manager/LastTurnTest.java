package com.uni.gruppenphaseandroid.manager;

import static org.junit.Assert.assertEquals;

import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.communication.dto.MessageType;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUIimpl;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.Test;

public class LastTurnTest {

    @Test
    public void givenLastTurn_whenGeneratingUpdateBoardPayload_expectCorrectMessage() {
        var figure1 = new Figure(0, Color.BLACK, new Field(), Typ.KING, new FigureUIimpl());
        var figure2 = new Figure(1, Color.GREEN, new Field(), Typ.CITIZEN, new FigureUIimpl());

        var lastTurn = new LastTurn(figure1, figure2, new Field(), new Field(), 0);
        lastTurn.setCardtype(Cardtype.EIGTH);
        var message = lastTurn.generateServerMessage();
        assertEquals(MessageType.UPDATE_BOARD, message.getType());
        assertEquals("{\"figure1ID\":0,\"figure2ID\":1,\"newField1ID\":0,\"newField2ID\":0,\"cardType\":8,\"cheatModifier\":0}", message.getPayload());
    }
}
