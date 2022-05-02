package com.uni.gruppenphaseandroid.playingfieldtests;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.StartingField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Any;

import static org.mockito.Mockito.*;

public class PlayingFieldTest {

    PlayingField playingField;
    View view;
    ImageView imageView;

    @Before
    public void setUp(){
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);

        playingField = new PlayingField(view);

        verify(imageView, times(16)).setImageDrawable(any());
        verify(imageView, times(16)).getDrawable();

        Field startingAreaField = playingField.getRedStartingField().getPreviousStartingArea();
        startingAreaField.setCurrentFigure(new Figure());
        startingAreaField.getPreviousField().setCurrentFigure(new Figure());
    }

    @After
    public void tearDown(){
        view = null;
        playingField = null;
        imageView = null;
    }

    @Test
    public void regularFieldsConnectedTest(){

        Field expectedRootField = playingField.getRootField().getFieldAtDistance(64, Color.GREEN);
        Assert.assertEquals(playingField.getRootField(), expectedRootField);
        verify(view, times(96)).findViewWithTag(any());

    }

    @Test
    public void testRegularIDs(){
        Field currentField = playingField.getRootField();
        for(int i = 1; i <= 64;i++){
            Assert.assertEquals(i, currentField.getFieldID());
            currentField = currentField.getNextField();
        }
    }

    @Test
    public void startingFieldsTest(){
        Assert.assertTrue(playingField.getBlueStartingField() instanceof StartingField);
        Assert.assertTrue(playingField.getRedStartingField() instanceof StartingField);
        Assert.assertTrue(playingField.getYellowStartingField() instanceof StartingField);
        Assert.assertTrue(playingField.getGreenStartingField() instanceof StartingField);
    }

    @Test
    public void getStartFieldWithColorTest(){
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
    public void getRightStartingAreaFieldTest(){

        Field expectedField = playingField.getRedStartingField().getPreviousStartingArea().getFieldAtDistance(-2, Color.RED);
        Assert.assertEquals(expectedField, playingField.getRightStartingAreaField(Color.RED));
    }

    @Test
    public void getRightStartingAreaFieldTestAllAvailable(){
        Field expectedField = playingField.getGreenStartingField().getPreviousStartingArea();
        Assert.assertEquals(expectedField, playingField.getRightStartingAreaField(Color.GREEN));
    }
}
