package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.Mockito.mock;
import android.view.View;

import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import com.uni.gruppenphaseandroid.playingfield.Jerk;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

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
        Assert.assertNotNull(figureManager.getFigureWithID(1));
        Assert.assertNotNull(figureManager.getFigureWithID(4));
        try{
            Assert.assertNotNull(figureManager.getFigureWithID(5));
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

        Assert.assertNotNull(figureManager.getFigureWithID(16));
    }

    @Test
    public void testCreateRightDrawableGreenJerk(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.JERK);
        Assert.assertEquals(R.drawable.ic_greenjestercat, result);
    }
    @Test
    public void testCreateRightDrawableGreenCitizen(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.CITIZEN);
        Assert.assertEquals(R.drawable.ic_greencitizencat, result);
    }
    @Test
    public void testCreateRightDrawableGreenKnight(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.KNIGHT);
        Assert.assertEquals(R.drawable.ic_greenknightcat, result);
    }
    @Test
    public void testCreateRightDrawableGreenKing(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.KING);
        Assert.assertEquals(R.drawable.ic_greenkingcat, result);
    }
    @Test
    public void testCreateRightDrawableBlueJerk(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.JERK);
        Assert.assertEquals(R.drawable.ic_bluejestercat, result);
    }
    @Test
    public void testCreateRightDrawableBlueCitizen(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.CITIZEN);
        Assert.assertEquals(R.drawable.ic_bluecitizencat, result);
    }
    @Test
    public void testCreateRightDrawableBlueKnight(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.KNIGHT);
        Assert.assertEquals(R.drawable.ic_blueknightcat, result);
    }
    @Test
    public void testCreateRightDrawableBlueKing(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.KING);
        Assert.assertEquals(R.drawable.ic_bluekingcat, result);
    }

    @Test
    public void testCreateRightDrawableYellowJerk(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.JERK);
        Assert.assertEquals(R.drawable.ic_yellowjestercat, result);
    }
    @Test
    public void testCreateRightDrawableYellowCitizen(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.CITIZEN);
        Assert.assertEquals(R.drawable.ic_yellowcitizencat, result);
    }
    @Test
    public void testCreateRightDrawableYellowKnight(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.KNIGHT);
        Assert.assertEquals(R.drawable.ic_yellowknightcat, result);
    }
    @Test
    public void testCreateRightDrawableYellowKing(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.KING);
        Assert.assertEquals(R.drawable.ic_yellowkingcat, result);
    }

    @Test
    public void testCreateRightDrawableRedJerk(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.JERK);
        Assert.assertEquals(R.drawable.ic_redjestercat, result);
    }
    @Test
    public void testCreateRightDrawableRedCitizen(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.CITIZEN);
        Assert.assertEquals(R.drawable.ic_redcitizencat, result);
    }
    @Test
    public void testCreateRightDrawableRedKnight(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.KNIGHT);
        Assert.assertEquals(R.drawable.ic_redknightcat, result);
    }
    @Test
    public void testCreateRightDrawableRedKing(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.KING);
        Assert.assertEquals(R.drawable.ic_redkingcat, result);
    }

    @Test
    public void testCreateRightDrawableNotFound(){
        int result = figureManager.createRightDrawable(Color.BLACK, Typ.JERK);
        Assert.assertEquals(0, result);
    }
}
