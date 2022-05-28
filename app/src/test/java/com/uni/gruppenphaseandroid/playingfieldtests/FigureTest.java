package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUI;
import com.uni.gruppenphaseandroid.playingfield.FigureUIimpl;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FigureTest {
    PlayingField playingField;
    View view;
    Figure figure1;
    FigureUI figureUI1;
    Figure figure2;
    FigureUI figureUI2;
    Figure figure3;
    FigureUI figureUI3;
    Figure figure4;
    FigureUI figureUI4;
    Figure figure5;
    FigureUI figureUI5;
    Figure figure6;
    FigureUI figureUI6;
    Figure figure7;
    FigureUI figureUI7;
    Figure figure8;
    FigureUI figureUI8;
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

        //verify(imageView, times(16)).setImageDrawable(any());
        //verify(imageView, times(16)).getDrawable();

        Field startingAreaField = playingField.getRedStartingField().getPreviousStartingArea();
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



        figure5 = new Figure(5, Color.BLUE, field4, Typ.JERK, figureUI5);
        figure6 = new Figure(6, Color.BLUE, field3, Typ.CITIZEN, figureUI6);
        figure7 = new Figure(7, Color.BLUE, field2, Typ.KNIGHT, figureUI7);
        figure8 = new Figure(8, Color.BLUE, field1, Typ.KING, figureUI8);
    }

    @After
    public void tearDown(){
        view = null;
        playingField = null;
        imageView = null;
    }
    /*
    @Test
    public void checkMoveKing() throws Exception {
        Field expectedField = playingField.getRootField().getNextField().getNextField();
        Assert.assertEquals(expectedField, playingField.move(figure2,1));
    }

    @Test
    public void checkMoveJerk() throws Exception {
        Field expectedField = playingField.getRootField().getNextField();
        Assert.assertEquals(expectedField, playingField.move(figure1,1));
    }

    @Test
    public void checkIfMovingPossibleKing() {
        Assert.assertTrue(playingField.checkMovingPossible(figure2, 1));
    }

     */

    /**
     * Test Rangfolge innerhalb einer Farbe: Jerk - Citizen - Knight - King
     */
    @Test
    public void checkOvertakingRedJerkByRedCitizen() {
        figure1 = new Figure(1, Color.RED, field2, Typ.JERK, figureUI1);
        figure2 = new Figure(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertTrue(figure2.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByRedKnight() {
        figure1 = new Figure(1, Color.RED, field2, Typ.JERK, figureUI1);
        figure3 = new Figure(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(figure3.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedJerkByRedKing() {
        figure1 = new Figure(1, Color.RED, field2, Typ.JERK, figureUI1);
        figure4 = new Figure(4, Color.RED, field1, Typ.KING, figureUI4);
        Assert.assertTrue(figure4.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedJerk() {
        figure2 = new Figure(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        figure1 = new Figure(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertTrue(figure2.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedKnight() {
        figure2 = new Figure(2, Color.RED, field2, Typ.CITIZEN, figureUI2);
        figure3 = new Figure(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        Assert.assertTrue(figure3.checkOvertaking());
    }

    @Test
    public void checkOvertakingRedCitizenByRedKing() {

    }
}
