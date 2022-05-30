package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.junit.Assert.*;
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
        assertNotNull(figureManager.getFigureWithID(1));
        assertNotNull(figureManager.getFigureWithID(4));
        try{
            assertNull(figureManager.getFigureWithID(5));
        }catch (IllegalArgumentException ignored)         {

        }
    }

    @Test
    public void createFigureSetOfColorTestType(){
        figureManager.createFigureObjects(Color.YELLOW, playingField);
        assertTrue(figureManager.getFigureWithID(1)instanceof Jerk);
        assertTrue(figureManager.getFigureWithID(4)instanceof King);
    }

    @Test
    public void createFigureSetOfColorTestField(){
        figureManager.createFigureObjects(Color.BLUE, playingField);
        assertEquals(playingField.getBlueStartingField().getPreviousStartingArea(), figureManager.getFigureWithID(1).getCurrentField());
        assertEquals(playingField.getBlueStartingField().getPreviousStartingArea().getPreviousField(), figureManager.getFigureWithID(2).getCurrentField());
    }

    @Test
    public void createFigureSetOfColorTestColor(){
        figureManager.createFigureObjects(Color.GREEN, playingField);
        assertEquals(Color.GREEN, figureManager.getFigureWithID(4).getColor());
    }

    @Test
    public void createAllFiguresTest(){
        figureManager.createFigureObjects(Color.GREEN, playingField);
        figureManager.createFigureObjects(Color.YELLOW, playingField);
        figureManager.createFigureObjects(Color.RED, playingField);
        figureManager.createFigureObjects(Color.BLUE, playingField);

        assertNotNull(figureManager.getFigureWithID(16));
    }

    @Test
    public void testCreateRightDrawableGreenJerk(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.JERK);
        assertEquals(R.drawable.ic_greenjester, result);
    }
    @Test
    public void testCreateRightDrawableGreenCitizen(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.CITIZEN);
        assertEquals(R.drawable.ic_greencitizen, result);
    }
    @Test
    public void testCreateRightDrawableGreenKnight(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.KNIGHT);
        assertEquals(R.drawable.ic_greenknight, result);
    }
    @Test
    public void testCreateRightDrawableGreenKing(){
        int result = figureManager.createRightDrawable(Color.GREEN, Typ.KING);
        assertEquals(R.drawable.ic_greenking, result);
    }
    @Test
    public void testCreateRightDrawableBlueJerk(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.JERK);
        assertEquals(R.drawable.ic_bluejester, result);
    }
    @Test
    public void testCreateRightDrawableBlueCitizen(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.CITIZEN);
        assertEquals(R.drawable.ic_bluecitizen, result);
    }
    @Test
    public void testCreateRightDrawableBlueKnight(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.KNIGHT);
        assertEquals(R.drawable.ic_blueknight, result);
    }
    @Test
    public void testCreateRightDrawableBlueKing(){
        int result = figureManager.createRightDrawable(Color.BLUE, Typ.KING);
        assertEquals(R.drawable.ic_blueking, result);
    }

    @Test
    public void testCreateRightDrawableYellowJerk(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.JERK);
        assertEquals(R.drawable.ic_yellowjester, result);
    }
    @Test
    public void testCreateRightDrawableYellowCitizen(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.CITIZEN);
        assertEquals(R.drawable.ic_yellowcitizen, result);
    }
    @Test
    public void testCreateRightDrawableYellowKnight(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.KNIGHT);
        assertEquals(R.drawable.ic_yellowknight, result);
    }
    @Test
    public void testCreateRightDrawableYellowKing(){
        int result = figureManager.createRightDrawable(Color.YELLOW, Typ.KING);
        assertEquals(R.drawable.ic_yellowking, result);
    }

    @Test
    public void testCreateRightDrawableRedJerk(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.JERK);
        assertEquals(R.drawable.ic_redjester, result);
    }
    @Test
    public void testCreateRightDrawableRedCitizen(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.CITIZEN);
        assertEquals(R.drawable.ic_redcitizen, result);
    }
    @Test
    public void testCreateRightDrawableRedKnight(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.KNIGHT);
        assertEquals(R.drawable.ic_redknight, result);
    }
    @Test
    public void testCreateRightDrawableRedKing(){
        int result = figureManager.createRightDrawable(Color.RED, Typ.KING);
        assertEquals(R.drawable.ic_redking, result);
    }

    @Test
    public void testCreateRightDrawableNotFound(){
        int result = figureManager.createRightDrawable(Color.BLACK, Typ.JERK);
        assertEquals(0, result);
    }
}
