package com.uni.gruppenphaseandroid;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUI;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayCardTest {
    PlayingField playingField;
    View view;
    Figure figure1;
    FigureUI figureUI1;
    Figure figure2;
    FigureUI figureUI2;
    ImageView imageView;

    @Before
    public void setUp(){
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);

        playingField = new PlayingField(view);
        GameManager.getInstance().setPlayingField(playingField);
        Field startingAreaField = playingField.getRedStartingField().getPreviousStartingArea();
        startingAreaField.setCurrentFigure(new Figure());
        startingAreaField.getPreviousField().setCurrentFigure(new Figure());
        figure1 = new Figure(1, Color.RED, playingField.getRedStartingField(), Typ.JERK, figureUI1);
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getNextField(), Typ.KING, figureUI2);
    }

    @After
    public void tearDown(){
        view = null;
        playingField = null;
        imageView = null;
    }

    @Test
    public void playNumberCard() throws Exception {
        Field expected = figure1.getCurrentField().getNextField().getNextField();
        Card card = new Card(Cardtype.TWO);

        card.playCard(figure1, 0, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playMAGNET() throws Exception {
        figure2.setCurrentField(playingField.getBlueStartingField());
        figure1.setCurrentField(playingField.getRedStartingField());
        Field expected = figure2.getCurrentField().getPreviousField();
        Card card = new Card(Cardtype.MAGNET);

        card.playCard(figure1, 0, figure2);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playSWITCH() throws Exception {
        figure2.setCurrentField(playingField.getBlueStartingField());
        figure1.setCurrentField(playingField.getRedStartingField());
        Field expected1 = figure2.getCurrentField();
        Field expected2 = figure1.getCurrentField();
        Card card = new Card(Cardtype.SWITCH);

        card.playCard(figure1, 0, figure2);

        Assert.assertEquals(expected1, figure1.getCurrentField());
        Assert.assertEquals(expected2, figure2.getCurrentField());
    }
}