package com.uni.gruppenphaseandroid;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUIimpl;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.StartingField;
import com.uni.gruppenphaseandroid.playingfield.Wormhole;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WormholeTest {

    View view;
    PlayingField playingField;
    ImageView imageView;
    Figure figure_1;
    Figure figure_2;
    FigureUIimpl figure_1UI;
    FigureUIimpl figure_2UI;



    @Before
    public void setUp() {
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);
        playingField = new PlayingField(view);
        figure_1 = new Figure();
        figure_2 = new Figure();
        figure_1UI = mock(FigureUIimpl.class);
        figure_1.setFigureUI(figure_1UI);
        figure_2UI = mock(FigureUIimpl.class);
        figure_2.setFigureUI(figure_2UI);

    }

    @Test
    public void getNewFieldforWormholeSwitchTest() {
        Field wormhole = playingField.getRootField();

        while (!(wormhole instanceof Wormhole)) {
            wormhole = wormhole.getNextField();

        }

        wormhole = ((Wormhole) wormhole).getNewFieldforWormholeSwitch(((Wormhole) wormhole).generateRandomNumber());
        assertFalse(wormhole instanceof Wormhole);
        assertFalse(wormhole instanceof StartingField);
    }

    @Test
    public void testTriggerEffectSingleFigure(){
       Wormhole wormhole_1 = playingField.getWormholeList().get(0);
       Wormhole wormhole_2 = playingField.getWormholeList().get(1);

       figure_1.setCurrentField(wormhole_1);
       wormhole_1.setCurrentFigure(figure_1);

       wormhole_1.triggerSpecialFieldEffect();
       Assert.assertEquals(wormhole_2, figure_1.getCurrentField());
       Assert.assertEquals(figure_1, wormhole_2.getCurrentFigure());

    }

    @Test
    public void testTriggerEffectSwitchFigures(){
        Wormhole wormhole_1 = playingField.getWormholeList().get(3);
        Wormhole wormhole_2 = playingField.getWormholeList().get(2);

        figure_1.setCurrentField(wormhole_1);
        wormhole_1.setCurrentFigure(figure_1);
        figure_2.setCurrentField(wormhole_2);
        wormhole_2.setCurrentFigure(figure_2);

        wormhole_1.triggerSpecialFieldEffect();

        Assert.assertEquals(wormhole_2, figure_1.getCurrentField());
        Assert.assertEquals(figure_1, wormhole_2.getCurrentFigure());
        Assert.assertEquals(wormhole_1, figure_2.getCurrentField());
        Assert.assertEquals(figure_2, wormhole_1.getCurrentFigure());
    }
}
