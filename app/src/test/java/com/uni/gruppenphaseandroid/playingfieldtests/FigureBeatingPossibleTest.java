package com.uni.gruppenphaseandroid.playingfieldtests;
// TODO: König noch offen - Code umschreiben

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Citizen;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Jerk;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.Knight;
import com.uni.gruppenphaseandroid.playingfield.StartingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.Assert;
import org.junit.Test;

public class FigureTestBeatingPossible extends FigureTest {

    @Override
    public void setUp(){
        super.setUp();
        Card greencard = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
    }

    /**
     * Rausschmeißen innerhalb einer Farbe
     * Ausnahme King kann nur von King geschmissen werden.
     */
    @Test
    public void checkBeatenRedJerkByRedCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenRedJerkByRedKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isBeaten());
    }

    @Test
    public void checkBeatenRedJerkByRedKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByRedJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByRedKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByRedKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByRedJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByRedCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByRedKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByRedJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByRedCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByRedKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isBeaten());
    }

    /**
     * Test Rausschmeißen zwischen Farben
     * Ausnahme King kann nur von King geschmissen werden.
     */
    @Test
    public void checkBeatenRedJerkByBlueJerk() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedJerkByBlueCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedJerkByBlueKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedJerkByBlueKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueCitizen() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueKnight() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueKing() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isBeaten());
    }

    /**
     * Test Rausschmeißverbot auf eigenem Startfeld.
     */
    @Test
    public void checkBeatenGreenJerkByRedJerkOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenJerkByRedCitizenOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenJerkByRedKnightOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isBeaten());
    }

    @Test
    public void checkColorStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        Assert.assertTrue(field13 instanceof StartingField);
        Assert.assertSame(((StartingField) field13).getColor(), jerkGreen.getColor());
    }

    @Test
    public void checkBeatenGreenJerkByRedKingOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedJerkOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedCitizenOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedKnightOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedKingOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedJerkOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedCitizenOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedKingOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedJerkOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedCitizenOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field11, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByBlueKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByBlueKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field14, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.isBeaten());
    }

    @Test
    public void checkBeatenGreenKingByBlueKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByBlueKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByBlueKnightOnNormalField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field14, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field13, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.isBeaten());
    }

    /**
     * König kann auf fremdem Startfeld von der Figur dieser fremden Farbe geschmissen werden.
     * Gleiche Farbe kann Figur der gleichen Farbe auf eigenem Startfeld nicht schmeißen.
     */
    @Test
    public void checkBeatenBlueKingByGreenKingOnGreenStartingField() { // OK
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        Assert.assertTrue(kingGreen.isBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByGreenCitizenOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        Assert.assertFalse(citizenGreen.isBeaten());
    }

    /*
    @Test
    public void checkBeatenBlueKingByGreenCitizenOnGreenStartingField() { // TODO: NOK - König hier keine Ausnahme!
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        Assert.assertTrue(citizenGreen.checkBeaten());
    }
    */

    @Test
    public void checkBeatenBlueKnightByGreenCitizenOnGreenStartingField() { // OK
        knightBlue = new Knight(7, Color.BLUE, field13, Typ.KNIGHT, figureUI7);
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        Assert.assertTrue(citizenGreen.isBeaten());
    }

    @Test
    public void checkBeatenBlueKingByRedKingOnGreenStartingField() { // OK
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.isBeaten());
    }

    /**
     * Rausschmeißen im Ziel nicht möglich.
     */
    @Test
    public void checkBeatingGreenJerkByGreenCitizenInGoalFalse() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        playingField.move(jerkGreen, 3);
        citizenGreen = new Citizen(10, Color.GREEN, field11, Typ.CITIZEN, figureUI10);
        playingField.move(citizenGreen, 3);
        Assert.assertFalse(citizenGreen.isBeaten());
    }

    @Test
    public void checkBeatingGreenKingByGreenKnightInGoalFalse() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        playingField.move(kingGreen, 4);
        knightGreen = new Knight(11, Color.GREEN, field11, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 4);
        Assert.assertFalse(knightGreen.isBeaten());
    }



}
