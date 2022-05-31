package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

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
import com.uni.gruppenphaseandroid.playingfield.StartingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

FigureTestBeaten {
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
     * Rausschmeißen innerhalb einer Farbe
     * Ausnahme King kann nur von King geschmissen werden.
     */
    @Test
    public void checkBeatenRedJerkByRedCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedJerkByRedKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedJerkByRedKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByRedJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByRedKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(knightRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByRedKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByRedJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByRedCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByRedKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByRedJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByRedCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByRedKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkBeaten());
    }

    /**
     * Test Rausschmeißen zwischen Farben
     * Ausnahme King kann nur von King geschmissen werden.
     */
    @Test
    public void checkBeatenRedJerkByBlueJerk() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedJerkByBlueCitizen() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedJerkByBlueKnight() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedJerkByBlueKing() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueJerk() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueCitizen() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueKnight() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedCitizenByBlueKing() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueJerk() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertTrue(jerkBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueCitizen() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertTrue(citizenBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueKnight() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKnightByBlueKing() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueJerk() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertFalse(jerkBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueCitizen() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertFalse(citizenBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueKnight() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkBeaten());
    }

    @Test
    public void checkBeatenRedKingByBlueKing() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkBeaten());
    }

    /**
     * Test Rausschmeißverbot auf eigenem Startfeld.
     */
    @Test
    public void checkBeatenGreenJerkByRedJerkOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenJerkByRedCitizenOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenJerkByRedKnightOnGreenStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkBeaten());
    }

    @Test
    public void checkColorStartingField() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        Assert.assertTrue(field13 instanceof StartingField);
        //Assert.assertSame(((StartingField) field13).getColor(), Jerk.getColor);
    }

    @Test
    public void checkBeatenGreenJerkByRedKingOnGreenStartingField() { // OK!!
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedJerkOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedCitizenOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedKnightOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenCitizenByRedKingOnGreenStartingField() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedJerkOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedCitizenOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByRedKingOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedJerkOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertFalse(jerkRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedCitizenOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertFalse(citizenRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertFalse(knightRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertFalse(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByRedKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        kingRed = new King(4, Color.RED, field11, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByBlueKingOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertFalse(kingBlue.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByBlueKingOnNormalField() { // OK
        kingGreen = new King(12, Color.GREEN, field14, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        Assert.assertTrue(kingBlue.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKingByBlueKnightOnGreenStartingField() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByBlueKnightOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertFalse(knightBlue.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByBlueKnightOnNormalField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field14, Typ.KNIGHT, figureUI11);
        knightBlue = new Knight(7, Color.BLUE, field13, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkBeaten());
    }

    /**
     * König kann auf fremdem Startfeld von der Figur dieser Farbe geschmissen werden.
     * Gleiche Farbe kann Figur der gleichen Farbe auf eigenem Startfeld nicht schmeißen.
     */
    @Test
    public void checkBeatenBlueKingByGreenKingOnGreenStartingField() { // OK
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        Assert.assertTrue(kingGreen.checkBeaten());
    }

    @Test
    public void checkBeatenGreenKnightByGreenCitizenOnGreenStartingField() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        Assert.assertFalse(citizenGreen.checkBeaten());
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
        Assert.assertTrue(citizenGreen.checkBeaten());
    }

    @Test
    public void checkBeatenBlueKingByRedKingOnGreenStartingField() { // OK
        kingBlue = new King(8, Color.BLUE, field13, Typ.KING, figureUI8);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertTrue(kingRed.checkBeaten());
    }

    /**
     * Rausschmeißen im Ziel nicht möglich.
     */
    // TODO: Test Noch offen

}
