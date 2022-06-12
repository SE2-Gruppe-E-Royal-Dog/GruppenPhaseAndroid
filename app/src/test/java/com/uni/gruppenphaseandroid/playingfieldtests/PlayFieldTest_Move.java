package com.uni.gruppenphaseandroid.playingfieldtests;

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
import com.uni.gruppenphaseandroid.playingfield.StartingAreaField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayFieldTest_Move {
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
    Field startingAreaField;


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

        startingAreaField = playingField.getGreenStartingField().getPreviousStartingArea();
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
     * Test auf alle Figuren von Green ohne GreenCard - Normales Feld
     */
    @Test
    public void checkMoveJerkRed() { // OK
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,4);
        Assert.assertEquals(field5, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatenJerkGreenPositionJerkRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field2, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatenJerkGreenPositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkRedOvertakingJerkGreenPositionJerkRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,2);
        Assert.assertEquals(field3, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatenJerkGreenPositionCitizenRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertEquals(field2, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatenJerkGreenPositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenRedOvertakingJerkGreenPositionCitizenRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,2);
        Assert.assertEquals(field3, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatenJerkGreenPositionKnightRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertEquals(field2, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatenJerkGreenPositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightRedOvertakingJerkGreenPositionKnightRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field3, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenJerkGreenPositionKingRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertEquals(field2, kingRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenJerkGreenPositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingRedOvertakingJerkGreenPositionKingRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field3, kingRed.getCurrentField());
    }


    @Test
    public void checkMoveJerkBlueBeatenCitizenGreenPositionJerkBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertEquals(field2, jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveJerkBlueBeatenCitizenGreenPositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    /*@Test
    public void checkMoveJerkBlueOvertakingCitizenGreenPositionJerkBlue() { // NOK TODO: Check mit Nermin ob Null ok
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,2);
        Assert.assertNull(jerkBlue.getCurrentField());
    }

     */

    @Test
    public void checkMoveCitizenBlueBeatenCitizenGreenPositionCitizenBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertEquals(field2, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatenCitizenGreenPositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenBlueOvertakingCitizenGreenPositionCitizenBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,2);
        Assert.assertEquals(field3, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenCitizenGreenPositionKnightBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertEquals(field2, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenCitizenGreenPositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightBlueOvertakingCitizenGreenPositionKnightBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,2);
        Assert.assertEquals(field3, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatenCitizenGreenPositionKingBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertEquals(field2, kingBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatenCitizenGreenPositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingBlueOvertakingCitizenGreenPositionKingBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,2);
        Assert.assertEquals(field3, kingBlue.getCurrentField());
    }


    @Test
    public void checkMoveJerkRedBeatenKnightGreenPositionJerkRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field2, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatenKnightGreenPositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    /*@Test
    public void checkMoveJerkRedOvertakingKnightGreenPositionJerkRed() { // TODO: NOK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,2);
        Assert.assertEquals(field3, jerkRed.getCurrentField());
    }

     */

    @Test
    public void checkMoveCitizenRedBeatenKnightGreenPositionCitizenRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertEquals(field2, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatenKnightGreenPositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    /*@Test
    public void checkMoveCitizenRedOvertakingKnightGreenPositionCitizenRed() { // TODO: NOK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,2);
        Assert.assertEquals(field3, citizenRed.getCurrentField());
    }

     */

    @Test
    public void checkMoveKnightRedBeatenKnightGreenPositionKnightRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertEquals(field2, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatenKnightGreenPositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightRedOvertakingKnightGreenPositionKnightRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field3, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenKnightGreenPositionKingRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertEquals(field2, kingRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenKnightGreenPositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingRedOvertakingKnightGreenPositionKingRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field3, kingRed.getCurrentField());
    }


    /*@Test
    public void checkMoveJerkBlueBeatenKingGreenPositionJerkBlue() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertEquals(field2, jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveJerkBlueBeatenKingGreenPositionKingGreen() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkBlueOvertakingKingGreenPositionJerkBlue() { // NOK TODO: Check mit Nermin ob Null ok
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,2);
        Assert.assertNull(jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatenKingGreenPositionCitizenBlue() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertEquals(field2, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatenKingGreenPositionKingGreen() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenBlueOvertakingKingGreenPositionCitizenBlue() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,2);
        Assert.assertEquals(field3, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenKingGreenPositionKnightBlue() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertEquals(field2, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenKingGreenPositionKingGreen() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightBlueOvertakingKingGreenPositionKnightBlue() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,2);
        Assert.assertEquals(field3, knightBlue.getCurrentField());
    }

     */

    @Test
    public void checkMoveKingBlueBeatenKingGreenPositionKingBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertEquals(field2, kingBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatenKingGreenPositionKingGreen() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertTrue(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingBlueOvertakingKingGreenPositionKingBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,2);
        Assert.assertEquals(field3, kingBlue.getCurrentField());
    }


    /**
     * Test auf alle Figuren von Green ohne GreenCard - Startfeld
     */
    @Test
    public void checkMoveJerkRedStart() { // OK
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field13, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatenJerkGreenPositionJerkRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field12, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatenJerkGreenPositionJerkGreenStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertFalse(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkRedOvertakingJerkGreenPositionJerkRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,2);
        Assert.assertEquals(field14, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatenJerkGreenPositionCitizenRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertEquals(field12, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatenJerkGreenPositionJerkGreenStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertFalse(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenRedOvertakingJerkGreenPositionCitizenRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,2);
        Assert.assertEquals(field14, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatenJerkGreenPositionKnightRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertEquals(field12, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatenJerkGreenPositionJerkGreenStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertFalse(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightRedOvertakingJerkGreenPositionKnightRedStart() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field14, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenJerkGreenPositionKingRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertEquals(field12, kingRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenJerkGreenPositionJerkGreenStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertFalse(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingRedOvertakingJerkGreenPositionKingRedStart() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field14, kingRed.getCurrentField());
    }


    @Test
    public void checkMoveJerkBlueBeatenCitizenGreenPositionJerkBlueStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertEquals(field12, jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveJerkBlueBeatenCitizenGreenPositionCitizenGreenStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertFalse(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkBlueOvertakingCitizenGreenPositionJerkBlueStart() { // NOK TODO: Check mit Nermin ob Null ok
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,2);
        Assert.assertEquals(field12, jerkBlue.getCurrentField());
    }


    @Test
    public void checkMoveCitizenBlueBeatenCitizenGreenPositionCitizenBlueStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertEquals(field12, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatenCitizenGreenPositionCitizenGreenStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertFalse(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenBlueOvertakingCitizenGreenPositionCitizenBlueStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,2);
        Assert.assertEquals(field14, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenCitizenGreenPositionKnightBlueStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertEquals(field12, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenCitizenGreenPositionCitizenGreenStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertFalse(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightBlueOvertakingCitizenGreenPositionKnightBlueStart() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,2);
        Assert.assertEquals(field14, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatenCitizenGreenPositionKingBlueStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertEquals(field12, kingBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatenCitizenGreenPositionCitizenGreenStart() { // NOK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertFalse(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingBlueOvertakingCitizenGreenPositionKingBlueStart() { // NOK??
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        playingField.move(kingBlue,2);
        Assert.assertEquals(field14, kingBlue.getCurrentField());
    }


    @Test
    public void checkMoveJerkRedBeatenKnightGreenPositionJerkRedStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field12, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatenKnightGreenPositionKnightGreenStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertFalse(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkRedOvertakingKnightGreenPositionJerkRedStart() { // TODO: NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,2);
        Assert.assertEquals(field12, jerkRed.getCurrentField());
    }


    @Test
    public void checkMoveCitizenRedBeatenKnightGreenPositionCitizenRedStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertEquals(field12, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatenKnightGreenPositionKnightGreenStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertFalse(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenRedOvertakingKnightGreenPositionCitizenRedStart() { // TODO: NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,2);
        Assert.assertEquals(field12, citizenRed.getCurrentField());
    }


    @Test
    public void checkMoveKnightRedBeatenKnightGreenPositionKnightRedStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertEquals(field12, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatenKnightGreenPositionKnightGreenStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertFalse(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightRedOvertakingKnightGreenPositionKnightRedStart() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field14, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenKnightGreenPositionKingRedStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertEquals(field12, kingRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenKnightGreenPositionKnightGreenStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertFalse(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingRedOvertakingKnightGreenPositionKingRedStart() { // NOK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field14, kingRed.getCurrentField());
    }


    @Test
    public void checkMoveJerkBlueBeatenKingGreenPositionJerkBlueStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertEquals(field12, jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveJerkBlueBeatenKingGreenPositionKingGreenStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkBlueOvertakingKingGreenPositionJerkBlueStart() { // NOK TODO: Check mit Nermin ob Null ok
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,2);
        Assert.assertEquals(field12, jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatenKingGreenPositionCitizenBlueStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertEquals(field12, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatenKingGreenPositionKingGreenStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenBlueOvertakingKingGreenPositionCitizenBlue() { // NOK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,2);
        Assert.assertEquals(field12, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenKingGreenPositionKnightBlueStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertEquals(field12, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatenKingGreenPositionKingGreenStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightBlueOvertakingKingGreenPositionKnightBlueStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,2);
        Assert.assertEquals(field12, knightBlue.getCurrentField());
    }


    @Test
    public void checkMoveKingBlueBeatenKingGreenPositionKingBlueStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertEquals(field12, kingBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatenKingGreenPositionKingGreenStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingBlueOvertakingKingGreenPositionKingBlueStart() { // NOK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        playingField.move(kingBlue,2);
        Assert.assertEquals(field14, kingBlue.getCurrentField());
    }

}
