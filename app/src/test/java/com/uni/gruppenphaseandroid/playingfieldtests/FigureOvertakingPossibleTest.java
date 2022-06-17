package com.uni.gruppenphaseandroid.playingfieldtests; // fertig

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Citizen;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Jerk;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.Knight;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.Assert;
import org.junit.Test;

public class FigureTestOvertakingPossible extends FigureTest {

    /**
     * Test Rangfolge innerhalb einer Farbe: Jerk -> Citizen -> Knight -> King
     */
    @Test
    public void checkOvertakingRedJerkByRedKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByRedKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByRedJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByRedCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByRedKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByRedJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByRedCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByRedKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isOvertaking());
    }

    /**
     * Test Rangfolge zwischen Farben: Jerk -> Citizen -> Knight -> King
     */
    @Test
    public void checkOvertakingRedJerkByBlueJerk() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByBlueCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByBlueKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByBlueKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueCitizen() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueKnight() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueKing() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isOvertaking());
    }

     /**
     * Test Überholverbot auf eigenem Startfeld
     * Ausnahme: Knight darf immer überholen innerhalb der Rangfolge
     */
    @Test
    public void checkOvertakingGreenJerkByRedJerkOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenJerkByRedCitizenOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenJerkByRedKnightOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenJerkByRedKingOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedJerkOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedCitizenOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedKnightOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedKingOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedJerkOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedCitizenOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedKingOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedJerkOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedCitizenOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field11, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByBlueKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByBlueKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field14, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByBlueKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByBlueKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByBlueKnightOnNormalField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field14, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field13, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertaking());
    }

    /**
     * CheckGreenCard und checkOvertakingPossible mit Green Card
     */
    @Test
    public void checkGreenCardOvertakingFourPlusMinus() { // OK
        Card greencard = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingTen() { // OK
        Card greencard = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingThree() { // OK
        Card greencard = new Card(Cardtype.THREE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingOneToSeven() { // OK
        Card greencard = new Card(Cardtype.ONETOSEVEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingGreenKingByBlueKnightStartingFieldFourPlusMinus() { // OK
        Card greencard = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(greencard);
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldFourPlusMinus() { // OK
        Card greencard = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingGreenKingByBlueKnightStartingFieldTen() { // OK
        Card greencard = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldTen() { // OK
        Card greencard = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingGreenKingByBlueKnightStartingFieldThirteenStart() { // OK
        Card greencard = new Card(Cardtype.THIRTEEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldSwitch() { // OK
        Card greencard = new Card(Cardtype.SWITCH);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldTwo() { // OK
        Card greencard = new Card(Cardtype.TWO);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldFive() { // OK
        Card greencard = new Card(Cardtype.FIVE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldSix() { // OK
        Card greencard = new Card(Cardtype.SIX);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldEigth() { // OK
        Card greencard = new Card(Cardtype.EIGTH);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldNine() { // OK
        Card greencard = new Card(Cardtype.NINE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldOneOrElevenStart() { // OK
        Card greencard = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldTwelve() { // OK
        Card greencard = new Card(Cardtype.TWELVE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldEqual() { // OK
        Card greencard = new Card(Cardtype.EQUAL);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldMagnet() { // OK
        Card greencard = new Card(Cardtype.MAGNET);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isOvertakingPossible());
    }
}
