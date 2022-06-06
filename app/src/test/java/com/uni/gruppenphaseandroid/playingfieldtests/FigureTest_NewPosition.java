package com.uni.gruppenphaseandroid.playingfieldtests;
// TODO: Optionen für Startfeld und Zielfeld testen => grüne Figuren
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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

public class FigureTest_NewPosition {
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

        Card greencard = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
    }

    @After
    public void tearDown(){
        view = null;
        playingField = null;
        imageView = null;
    }

    /**
     * Test innerhalb einer Farbe auf New Position
     * 2 => Überholen; 1 => Rausschmeißen
     */
    @Test
    public void checkNewPositionRedJerkByRedCitizen2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertEquals(field3, citizenRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedJerkByRedCitizen1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertEquals(field2, citizenRed.setNewPosition(1));
    }

    @Test
    public void checkNewPositionRedJerkByRedKnight2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertEquals(field3, knightRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedJerkByRedKnight1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertEquals(field2, knightRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedJerkByRedKing2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertEquals(field3, kingRed.setNewPosition(2));
    }
    @Test
    public void checkNewPositionRedJerkByRedKing1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertEquals(field2, kingRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByRedJerk2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByRedJerk1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertEquals(field2, jerkRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByRedKnight2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertEquals(field3, knightRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByRedKnight1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertEquals(field2, knightRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByRedKing2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertEquals(field3, kingRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByRedKing1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertEquals(field2, kingRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKnightByRedJerk2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKnightByRedJerk1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertEquals(field2, jerkRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKnightByRedCitizen2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKnightByRedCitizen1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertEquals(field2, citizenRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKnightByRedKing2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertEquals(field3, kingRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKnightByRedKing1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertEquals(field2, kingRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByRedJerk2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByRedJerk1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByRedCitizen2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByRedCitizen1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByRedKnight2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertNull(knightRed.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByRedKnight1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertNull(knightRed.setNewPosition(1));
    }

    /**
     * Test Rausschmeißen zwischen Farben
     * Ausnahme King kann nur von King geschmissen werden.
     */
    @Test
    public void checkNewPositionRedJerkByBlueJerk2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertEquals(field3, jerkBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedJerkByBlueJerk1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertEquals(field2, jerkBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedJerkByBlueCitizen2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertEquals(field3, citizenBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedJerkByBlueCitizen1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertEquals(field2, citizenBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedJerkByBlueKnight2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertEquals(field3, knightBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedJerkByBlueKnight1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertEquals(field2, knightBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedJerkByBlueKing2() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field3, kingBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedJerkByBlueKing1() { // OK
        jerkRed = new Jerk(1, Color.RED, field2, Typ.JERK, figureUI1);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field2, kingBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByBlueJerk2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByBlueJerk1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertEquals(field2, jerkBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByBlueCitizen2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertEquals(field3, citizenBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByBlueCitizen1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertEquals(field2, citizenBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByBlueKnight2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertEquals(field3, knightBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByBlueKnight1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertEquals(field2, knightBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedCitizenByBlueKing2() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field3, kingBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedCitizenByBlueKing1() { // OK
        citizenRed = new Citizen(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field2, kingBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKnightByBlueJerk2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKnightByBlueJerk1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertEquals(field2, jerkBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKnightByBlueCitizen2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKnightByBlueCitizen1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertEquals(field2, citizenBlue.setNewPosition(1));
    }


    @Test
    public void checkMovingRedKnightByBlueKnight2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertTrue(knightBlue.checkMoving(2));
    }

    @Test
    public void checkNewPositionRedKnightByBlueKnight1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertEquals(field2, knightBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKnightByBlueKing2() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field3, kingBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKnightByBlueKing1() { // OK
        knightRed = new Knight(3, Color.RED, field2, Typ.KNIGHT, figureUI3);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field2, kingBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByBlueJerk2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByBlueJerk1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByBlueCitizen2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByBlueCitizen1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByBlueKnight2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByBlueKnight1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(1));
    }


    @Test
    public void checkNewPositionRedKingByBlueKing2() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field3, kingBlue.setNewPosition(2));
    }

    @Test
    public void checkNewPositionRedKingByBlueKing1() { // OK
        kingRed = new King(4, Color.RED, field2, Typ.KING, figureUI4);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        Assert.assertEquals(field2, kingBlue.setNewPosition(1));
    }

    /**
     * Test: Einfahrt ins Ziel // TODO: Zieleinfahrt check Nermin
     */
    /*@Test
    public void checkGoalNormalJerkGreen2() {
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        Assert.assertNotEquals(field14, kingBlue.setNewPosition(2));


        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);

    }

     */


    /**
     * Test: Jerk zieht in GoalArea und kann bis zu 2 Felder verfallen lassen
     */
}
