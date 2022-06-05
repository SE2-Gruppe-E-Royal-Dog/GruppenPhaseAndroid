package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Citizen;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUI;
import com.uni.gruppenphaseandroid.playingfield.FigureUIimpl;
import com.uni.gruppenphaseandroid.playingfield.Jerk;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.Knight;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FigureTest_Overtaking {
    PlayingField playingField;
    View view;
    Jerk jerkRed; // Red
    FigureUI figureUI1;
    Citizen citizenRed;
    FigureUI figureUI2;
    Knight knightRed;
    FigureUI figureUI3;
    King kingRed;
    FigureUI figureUI4;
    Jerk jerkBlue; // Blue
    FigureUI figureUI5;
    Citizen citizenBlue;
    FigureUI figureUI6;
    Knight knightBlue;
    FigureUI figureUI7;
    King kingBlue;
    FigureUI figureUI8;
    Jerk jerkGreen; // Green
    FigureUI figureUI9;
    Citizen citizenGreen;
    FigureUI figureUI10;
    Knight knightGreen;
    FigureUI figureUI11;
    King kingGreen;
    FigureUI figureUI12;
    ImageView imageView;
    Field field1;
    Field field2;
    Field field3;
    Field field4;
    Field field5;
    Field field6;
    Field field7;
    Field field8;
    Field field9;
    Field field10;
    Field field11;
    Field field12;
    Field field13; // grünes Startfeld
    Field field14;

    @Before
    public void setUp(){
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);

        playingField = new PlayingField(view);
        GameManager.getInstance().setPlayingField(playingField);
        field1 = playingField.getRootField();
        field2 = field1.getNextField();
        field3 = field2.getNextField();
        field4 = field3.getNextField();
        field5 = field4.getNextField();
        field6 = field5.getNextField();
        field7 = field6.getNextField();
        field8 = field7.getNextField();
        field9 = field8.getNextField();
        field10 = field9.getNextField();
        field11 = field10.getNextField();
        field12 = field11.getNextField();
        field13 = field12.getNextField();
        field14 = field13.getNextField();

        Field startingAreaField = playingField.getGreenStartingField().getPreviousStartingArea();
        startingAreaField.setCurrentFigure(new Figure());
        startingAreaField.getPreviousField().setCurrentFigure(new Figure());
        figureUI1 = mock(FigureUIimpl.class);
        figureUI2 = mock(FigureUIimpl.class);
        figureUI3 = mock(FigureUIimpl.class);
        figureUI4 = mock(FigureUIimpl.class);
        figureUI5 = mock(FigureUIimpl.class);
        figureUI6 = mock(FigureUIimpl.class);
        figureUI7 = mock(FigureUIimpl.class);
        figureUI8 = mock(FigureUIimpl.class);
        figureUI9 = mock(FigureUIimpl.class);
        figureUI10 = mock(FigureUIimpl.class);
        figureUI11 = mock(FigureUIimpl.class);
        figureUI12 = mock(FigureUIimpl.class);
    }

    @After
    public void tearDown(){
        view = null;
        playingField = null;
        imageView = null;
    }


    /**
     * Test Rangfolge innerhalb einer Farbe: Jerk -> Citizen -> Knight -> King
     */
    @Test
    public void checkOvertakingRedJerkByRedCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByRedKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByRedKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByRedJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByRedCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByRedKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByRedJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByRedCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByRedKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkOvertaking());
    }

    /**
     * Test Rangfolge zwischen Farben: Jerk -> Citizen -> Knight -> King
     */
    @Test
    public void checkOvertakingRedJerkByBlueJerk() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByBlueCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByBlueKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByBlueKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueCitizen() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByBlueKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueKnight() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKnightByBlueKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedKingByBlueKing() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkOvertaking());
    }

     /**
     * Test Überholverbot auf eigenem Startfeld
     * Ausnahme: Knight darf immer überholen innerhalb der Rangfolge
     */
    @Test
    public void checkOvertakingGreenJerkByRedJerkOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenJerkByRedCitizenOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenJerkByRedKnightOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenJerkByRedKingOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedJerkOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedCitizenOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedKnightOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenCitizenByRedKingOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedJerkOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedCitizenOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByRedKingOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedJerkOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedCitizenOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByRedKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field11, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByBlueKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByBlueKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field14, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKingByBlueKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByBlueKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertaking());
    }

    @Test
    public void checkOvertakingGreenKnightByBlueKnightOnNormalField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field14, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field13, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertaking());
    }

    /**
     * CheckGreenCard und checkOvertakingPossible mit Green Card
     */
    @Test
    public void checkGreenCardOvertakingFourPlusMinus() { // OK
        Card greencard = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingTen() { // OK
        Card greencard = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingThree() { // OK
        Card greencard = new Card(Cardtype.THREE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingOneToSeven() { // OK
        Card greencard = new Card(Cardtype.ONETOSEVEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkGreenCard());
    }

    @Test
    public void checkGreenCardOvertakingGreenKingByBlueKnightStartingFieldFourPlusMinus() { // OK
        Card greencard = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(greencard);
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldFourPlusMinus() { // OK
        Card greencard = new Card(Cardtype.FOUR_PLUSMINUS);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingGreenKingByBlueKnightStartingFieldTen() { // OK
        Card greencard = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldTen() { // OK
        Card greencard = new Card(Cardtype.TEN);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingGreenKingByBlueKnightStartingFieldThirteenStart() { // OK
        Card greencard = new Card(Cardtype.THIRTEEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldSwitch() { // OK
        Card greencard = new Card(Cardtype.SWITCH);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldTwo() { // OK
        Card greencard = new Card(Cardtype.TWO);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldFive() { // OK
        Card greencard = new Card(Cardtype.FIVE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldSix() { // OK
        Card greencard = new Card(Cardtype.SIX);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldEigth() { // OK
        Card greencard = new Card(Cardtype.EIGTH);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldNine() { // OK
        Card greencard = new Card(Cardtype.NINE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldOneOrElevenStart() { // OK
        Card greencard = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldTwelve() { // OK
        Card greencard = new Card(Cardtype.TWELVE);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldEqual() { // OK
        Card greencard = new Card(Cardtype.EQUAL);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }

    @Test
    public void checkGreenCardOvertakingRedKingByBlueKnightNormalFieldMagnet() { // OK
        Card greencard = new Card(Cardtype.MAGNET);
        GameManager.getInstance().setSelectedCard(greencard);
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkOvertakingPossible());
    }
}
