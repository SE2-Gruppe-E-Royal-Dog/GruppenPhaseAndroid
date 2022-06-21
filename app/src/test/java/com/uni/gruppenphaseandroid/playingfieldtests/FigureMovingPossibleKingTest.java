package com.uni.gruppenphaseandroid.playingfieldtests;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.Assert;
import org.junit.Test;

public class FigureMovingPossibleKingTest extends FigureTest {

    @Override
    public void setUp(){
        super.setUp();
        Card card = new Card(Cardtype.ONEORELEVEN_START); //
        GameManager.getInstance().setSelectedCard(card);
    }

    /**
     * Test für King - Overtaking, Beaten, Move
     * Fields 1 - 7 possible
     */
    @Test
    public void checkMovingKing1True() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKing2True() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKing7True() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(7));
    }

    @Test
    public void checkMovingKing8False() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(8));
    }

    @Test
    public void checkMovingKing13False() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(13));
    }
}