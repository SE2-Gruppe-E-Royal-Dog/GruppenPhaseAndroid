package com.uni.gruppenphaseandroid;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.playingfield.Field;
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


    @Before
    public void setUp() {
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);
        playingField = new PlayingField(view);

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

}
