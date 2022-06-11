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
     * Test auf reine Vorwärtsbewegung
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
    public void checkMoveKingRedOvertakingJerkGreenPositionKing() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field3, kingRed.getCurrentField());
    }

}
