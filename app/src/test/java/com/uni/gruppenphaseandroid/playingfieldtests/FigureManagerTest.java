package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.Mockito.mock;
import android.view.View;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.Jerk;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FigureManagerTest {

    FigureManager figureManager;
    PlayingField playingField;
    View view;

    @Before
    public void setUp(){
        figureManager = new FigureManager();
        view = mock(View.class);
        playingField = new PlayingField(view);
    }

    @After
    public void tearDown(){
        figureManager = null;
        playingField=null;
        view = null;
    }

    @Test
    public void createFigureSetOfColorTestID(){
        figureManager.createFigureObjects(Color.RED, playingField);
        Assert.assertTrue(figureManager.getFigureWithID(1)!=null);
        Assert.assertTrue(figureManager.getFigureWithID(4)!=null);
        try{
            Assert.assertTrue(figureManager.getFigureWithID(5)==null);
        }catch (IllegalArgumentException e)         {

        }
    }

    @Test
    public void createFigureSetOfColorTestType(){
        figureManager.createFigureObjects(Color.YELLOW, playingField);
        Assert.assertTrue(figureManager.getFigureWithID(1)instanceof Jerk);
        Assert.assertTrue(figureManager.getFigureWithID(4)instanceof King);
    }

    @Test
    public void createFigureSetOfColorTestField(){
        figureManager.createFigureObjects(Color.BLUE, playingField);
        Assert.assertEquals(playingField.getBlueStartingField().getPreviousStartingArea(), figureManager.getFigureWithID(1).getCurrentField());
        Assert.assertEquals(playingField.getBlueStartingField().getPreviousStartingArea().getPreviousField(), figureManager.getFigureWithID(2).getCurrentField());
    }

    @Test
    public void createFigureSetOfColorTestColor(){
        figureManager.createFigureObjects(Color.GREEN, playingField);
        Assert.assertEquals(Color.GREEN, figureManager.getFigureWithID(4).getColor());
    }

    @Test
    public void createAllFiguresTest(){
        figureManager.createFigureObjects(Color.GREEN, playingField);
        figureManager.createFigureObjects(Color.YELLOW, playingField);
        figureManager.createFigureObjects(Color.RED, playingField);
        figureManager.createFigureObjects(Color.BLUE, playingField);

        Assert.assertTrue(figureManager.getFigureWithID(16)!= null);
    }
}
