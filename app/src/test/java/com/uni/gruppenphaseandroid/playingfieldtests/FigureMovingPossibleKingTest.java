package com.uni.gruppenphaseandroid.playingfieldtests; //fertig

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.Assert;
import org.junit.Test;

public class FigureTestMovingPossibleKing extends FigureTest {

    /**
     * Test für King - Overtaking, Beaten, Move possible
     */
    @Test
    public void checkMovingKingOneToSeven2() { // OK
        Card card1 = new Card(Cardtype.ONETOSEVEN);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingOneOrElevenStart2() { // OK
        Card card1 = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingThirteenStart2() { // OK
        Card card1 = new Card(Cardtype.THIRTEEN_START);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingMagnet2() { // OK
        Card card1 = new Card(Cardtype.MAGNET);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingSwitch2() { // OK
        Card card1 = new Card(Cardtype.SWITCH);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingOneToSeven1() { // OK
        Card card1 = new Card(Cardtype.ONETOSEVEN);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingOneOrElevenStart1() { // OK
        Card card1 = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingThirteenStart1() { // OK
        Card card1 = new Card(Cardtype.THIRTEEN_START);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingMagnet1() { // OK
        Card card1 = new Card(Cardtype.MAGNET);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingSwitch1() { // OK
        Card card1 = new Card(Cardtype.SWITCH);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    /**
     * Test für King - Overtaking, Beaten, Move NOT possible
     */
    @Test
    public void checkMovingKingFourPlusMinus2() { // OK
        Card card1 = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingTen2() { // OK
        Card card1 = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingThree2() { // OK
        Card card1 = new Card(Cardtype.THREE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingTwo2() { // OK
        Card card1 = new Card(Cardtype.TWO);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingFive2() { // OK
        Card card1 = new Card(Cardtype.FIVE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingSix2() { // OK
        Card card1 = new Card(Cardtype.SIX);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingEigth2() { // OK
        Card card1 = new Card(Cardtype.EIGTH);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingNine2() { // OK
        Card card1 = new Card(Cardtype.NINE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingTwelve2() { // OK
        Card card1 = new Card(Cardtype.TWELVE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingEqual2() { // OK
        Card card1 = new Card(Cardtype.EQUAL);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingKingFourPlusMinus1() { // OK
        Card card1 = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingTen1() { // OK
        Card card1 = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingThree1() { // OK
        Card card1 = new Card(Cardtype.THREE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingTwo1() { // OK
        Card card1 = new Card(Cardtype.TWO);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingFive1() { // OK
        Card card1 = new Card(Cardtype.FIVE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingSix1() { // OK
        Card card1 = new Card(Cardtype.SIX);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingEigth1() { // OK
        Card card1 = new Card(Cardtype.EIGTH);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingNine1() { // OK
        Card card1 = new Card(Cardtype.NINE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingTwelve1() { // OK
        Card card1 = new Card(Cardtype.TWELVE);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

    @Test
    public void checkMovingKingEqual1() { // OK
        Card card1 = new Card(Cardtype.EQUAL);
        GameManager.getInstance().setSelectedCard(card1);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }

}