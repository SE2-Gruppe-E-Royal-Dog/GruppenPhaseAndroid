package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUI;
import com.uni.gruppenphaseandroid.playingfield.FigureUIimpl;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.StartingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayingFieldTest {

    PlayingField playingField;
    View view;
    Figure figure1;
    FigureUI figureUI1;
    Figure figure2;
    FigureUI figureUI2;
    ImageView imageView;
    Card card;

    @Before
    public void setUp() {
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);

        playingField = new PlayingField(view);

        Field startingAreaField = playingField.getRedStartingField().getPreviousStartingArea();
        startingAreaField.setCurrentFigure(new Figure());
        startingAreaField.getPreviousField().setCurrentFigure(new Figure());
        figureUI1 = mock(FigureUIimpl.class);
        figureUI2 = mock(FigureUIimpl.class);
        figure1 = new Figure(1, Color.RED, playingField.getRootField(), Typ.JERK, figureUI1);
        figure2 = new Figure(2, Color.BLUE, playingField.getRootField().getNextField(), Typ.KING, figureUI2);
    }

    @After
    public void tearDown() {
        view = null;
        playingField = null;
        imageView = null;
    }

    @Test
    public void regularFieldsConnectedTest() {

        Field expectedRootField = playingField.getRootField().getFieldAtDistance(64, Color.GREEN);
        Assert.assertEquals(playingField.getRootField(), expectedRootField);
        verify(view, times(96)).findViewWithTag(any());

    }

    @Test
    public void testRegularIDs() {
        Field currentField = playingField.getRootField();
        for (int i = 1; i <= 64; i++) {
            Assert.assertEquals(i, currentField.getFieldID());
            currentField = currentField.getNextField();
        }
    }

    @Test
    public void startingFieldsTest() {
        Assert.assertTrue(playingField.getBlueStartingField() instanceof StartingField);
        Assert.assertTrue(playingField.getRedStartingField() instanceof StartingField);
        Assert.assertTrue(playingField.getYellowStartingField() instanceof StartingField);
        Assert.assertTrue(playingField.getGreenStartingField() instanceof StartingField);
    }

    @Test
    public void getStartFieldWithColorTest() {
        Field expectedStartingArea = playingField.getBlueStartingField();
        Assert.assertEquals(expectedStartingArea, playingField.getStartingFieldWithColor(Color.BLUE));

        expectedStartingArea = playingField.getGreenStartingField();
        Assert.assertEquals(expectedStartingArea, playingField.getStartingFieldWithColor(Color.GREEN));

        expectedStartingArea = playingField.getYellowStartingField();
        Assert.assertEquals(expectedStartingArea, playingField.getStartingFieldWithColor(Color.YELLOW));

        expectedStartingArea = playingField.getRedStartingField();
        Assert.assertEquals(expectedStartingArea, playingField.getStartingFieldWithColor(Color.RED));
    }

    @Test
    public void getRightStartingAreaFieldTest() {
        Field expectedField = playingField.getRedStartingField().getPreviousStartingArea().getFieldAtDistance(-2, Color.RED);
        Assert.assertEquals(expectedField, playingField.getRightStartingAreaField(Color.RED));
    }

    @Test
    public void getRightStartingAreaFieldTestAllAvailable() {
        Field expectedField = playingField.getGreenStartingField().getPreviousStartingArea();
        Assert.assertEquals(expectedField, playingField.getRightStartingAreaField(Color.GREEN));
    }

    @Test
    public void checkMoveKing() {
        Field expectedField = playingField.getRootField().getNextField().getNextField();
        Assert.assertEquals(expectedField, playingField.move(figure2, 1));
    }

    @Test
    public void checkMoveJerk() {
        Field expectedField = playingField.getRootField().getNextField();
        Assert.assertEquals(expectedField.getFieldID(), playingField.move(figure1, 1).getFieldID());
    }

    /*@Test // TODO: Test OFFEN
    public void checkIfMovingPossibleKing() {
        card = new Card(Cardtype.TWO);
        Assert.assertTrue(Figure.checkMoving(figure2, card));
    }

     */

    @Test
    public void moveToBlueStart() {
        figure1.setColor(Color.BLUE);
        Field expected = playingField.getBlueStartingField();
        playingField.moveToStart(figure1);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void moveToYellowStart() {
        figure1.setColor(Color.YELLOW);
        Field expected = playingField.getYellowStartingField();
        playingField.moveToStart(figure1);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void moveToGreenStart() {
        figure1.setColor(Color.GREEN);
        Field expected = playingField.getGreenStartingField();
        playingField.moveToStart(figure1);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void testGetFieldWithIDregular() {

        Field actualField = playingField.getFieldWithID(37);
        Assert.assertEquals(playingField.getRootField().getFieldAtDistance(36, Color.BLACK), actualField);
    }

    @Test
    public void testGetFieldWithIDedgeCases1() {

        Field actualField = playingField.getFieldWithID(1);
        Assert.assertEquals(playingField.getRootField(), actualField);
    }

    @Test
    public void testGetFieldWithIDedgeCases64() {

        Field actualField = playingField.getFieldWithID(1);
        Assert.assertEquals(playingField.getRootField().getFieldAtDistance(64, Color.BLACK), actualField);
    }

    @Test
    public void testGetFieldWithIDGoal() {

        Field actualField = playingField.getFieldWithID(82);
        Assert.assertEquals(playingField.getGreenStartingField().getNextGoalField().getNextField(), actualField);
    }

    @Test
    public void testGetFieldWithIDStartingArea() {
        Field actualField = playingField.getFieldWithID(76);
        Assert.assertEquals(playingField.getRedStartingField().getPreviousStartingArea(), actualField);
    }

    @Test
    public void testRepairWormholeVisuals(){
        playingField.repairWormholeVisuals();
        verify(imageView, times(60)).setImageResource(anyInt());
    }
}
