package com.uni.gruppenphaseandroid.playingfieldtests;

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

public class FigureMovingPossibleTest extends FigureTest {

    @Override
    public void setUp(){
        super.setUp();
        Card greencard = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
    }

    /**
     * Test innerhalb einer Farbe auf Move
     * 2 => Überholen; 1 => Rausschmeißen
     */
    @Test
    public void checkMovingRedJerkByRedCitizen2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.isMoving(2));
    }

    @Test
    public void checkMovingRedJerkByRedCitizen1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.isMoving(1));
    }


    @Test
    public void checkMovingRedJerkByRedKnight2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isMoving(2));
    }

    @Test
    public void checkMovingRedJerkByRedKnight1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isMoving(1));
    }


    @Test
    public void checkMovingRedJerkByRedKing2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isMoving(2));
    }
    @Test
    public void checkMovingRedJerkByRedKing1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByRedJerk2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByRedJerk1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(jerkRed.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByRedKnight2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByRedKnight1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByRedKing2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByRedKing1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByRedJerk2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByRedJerk1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(jerkRed.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByRedCitizen2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByRedCitizen1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByRedKing2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByRedKing1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByRedJerk2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByRedJerk1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByRedCitizen2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByRedCitizen1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByRedKnight2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByRedKnight1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isMoving(1));
    }


    /**
     * Test Rausschmeißen zwischen Farben
     * Ausnahme King kann nur von King geschmissen werden.
     */
    @Test
    public void checkMovingRedJerkByBlueJerk2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedJerkByBlueJerk1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedJerkByBlueCitizen2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedJerkByBlueCitizen1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedJerkByBlueKnight2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedJerkByBlueKnight1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedJerkByBlueKing2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedJerkByBlueKing1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByBlueJerk2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByBlueJerk1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByBlueCitizen2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByBlueCitizen1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByBlueKnight2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByBlueKnight1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedCitizenByBlueKing2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedCitizenByBlueKing1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByBlueJerk2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByBlueJerk1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByBlueCitizen2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByBlueCitizen1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByBlueKnight2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByBlueKnight1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKnightByBlueKing2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKnightByBlueKing1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByBlueJerk2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByBlueJerk1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByBlueCitizen2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByBlueCitizen1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByBlueKnight2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByBlueKnight1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isMoving(1));
    }


    @Test
    public void checkMovingRedKingByBlueKing2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingRedKingByBlueKing1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    /**
     * Test Moving auf Startfeld
     * rauswerfen = 1, überholen = 2
     */

    @Test
    public void checkMovingGreenKingOvertakenByBlueKingStartingFieldFalse() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingGreenKingBeatenByBlueKingOnStartingFieldFalse() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isMoving(1));
    }


    @Test
    public void checkMovingGreenKingOvertakenByBlueKingOnNormalFieldTrue() { // OK
        kingGreen = new King(12, Color.GREEN, field14, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(2));
    }

    @Test
    public void checkMovingGreenKingBeatenByBlueKingOnNormalFieldTrue() { // OK
        kingGreen = new King(12, Color.GREEN, field14, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isMoving(1));
    }

    /**
     * Test Moving auf Zielfeldern
     * rauswerfen = 1, überholen = 2
     */
    @Test
    public void checkMovingGreenKingBeatenByGreenKnightOnGoalFieldFalse() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        playingField.move(kingGreen, 3);
        knightGreen = new Knight(11, Color.GREEN, field11, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 3);
        Assert.assertFalse(knightGreen.isMoving(1));
    }

    @Test
    public void checkMovingGreenKingOvertakenByGreenKnightOnGoalFieldFalse() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        playingField.move(kingGreen, 3);
        knightGreen = new Knight(11, Color.GREEN, field11, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 3);
        Assert.assertFalse(knightGreen.isMoving(2));
    }

    @Test
    public void checkMovingGreenKnightBeatenByGreenKingOnGoalFieldFalse() { // OK
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 3);
        kingGreen = new King(12, Color.GREEN, field11, Typ.KING, figureUI12);
        playingField.move(kingGreen, 3);
        Assert.assertFalse(kingGreen.isMoving(1));
    }

    @Test
    public void checkMovingGreenKnightOvertakenByGreenKingOnGoalFieldTrue() { // OK
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 3);
        kingGreen = new King(12, Color.GREEN, field11, Typ.KING, figureUI12);
        playingField.move(kingGreen, 3);
        Assert.assertTrue(kingGreen.isMoving(2));
    }

    /**
     * Zieleinfahrt möglich mit Anzahl Feldern <=4 für alle
     * Ausnahme Jerk <= 6
     */
    @Test
    public void checkMovingGreenKnightIntoGoalFieldTrue() { // OK
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        Assert.assertTrue(knightGreen.isMoving(5));
    }

    @Test
    public void checkMovingGreenKnightIntoGoalFieldFalse() { // OK
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        Assert.assertTrue(knightGreen.isMoving(6));
    }

    @Test
    public void checkMovingGreenKingIntoGoalFieldTrue() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        Assert.assertTrue(kingGreen.isMoving(5));
    }

    @Test
    public void checkMovingGreenKingIntoGoalFieldFalse() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        Assert.assertTrue(kingGreen.isMoving(6));
    }

    @Test
    public void checkMovingGreenCitizenIntoGoalFieldTrue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        Assert.assertTrue(citizenGreen.isMoving(5));
    }

    @Test
    public void checkMovingGreenCitizenIntoGoalFieldFalse() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        Assert.assertTrue(citizenGreen.isMoving(6));
    }

    @Test
    public void checkMovingGreenJerkIntoGoalFieldTrue() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        Assert.assertTrue(jerkGreen.isMoving(5));
    }

    @Test
    public void checkMovingGreenJerkIntoGoalFieldFalse() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        Assert.assertTrue(jerkGreen.isMoving(8));
    }

}
