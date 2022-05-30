package com.uni.gruppenphaseandroid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.LastTurn;
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
        figureUI1 = mock(FigureUIimpl.class);
        figureUI2 = mock(FigureUIimpl.class);
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
    public void playCard_FigureIsNull(){
        Card card = new Card(Cardtype.TWO);

        assertThrows(IllegalArgumentException.class, () -> card.playCard(null, -1, null));
    }

    @Test
    public void playEqualNum(){
        Field expected = figure1.getCurrentField().getNextField().getNextField();
        Card card = new Card(Cardtype.EQUAL);
        LastTurn lastTurn = new LastTurn(null, null, null, null, 0);
        lastTurn.setCardtype(Cardtype.TWO);
        GameManager.getInstance().setLastTurn(lastTurn);

        card.playCard(figure1, -1, null);

        assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playEqualEqual(){
        Field expected = figure1.getCurrentField().getNextField().getNextField();
        Card card = new Card(Cardtype.EQUAL);
        LastTurn lastTurn = new LastTurn(null, null, null, null, 0);
        lastTurn.setCardtype(Cardtype.EQUAL);
        GameManager.getInstance().setLastTurn(lastTurn);

        assertThrows(IllegalArgumentException.class, () -> card.playCard(figure1, -1, null));
    }

    @Test
    public void playEqualStart(){
        figure1.setCurrentField(playingField.getRedStartingField().getPreviousStartingArea());
        Field expected = playingField.getRedStartingField();
        Card card = new Card(Cardtype.EQUAL);
        LastTurn lastTurn = new LastTurn(null, null, null, null, 0);
        lastTurn.setCardtype(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setLastTurn(lastTurn);

        card.playCard(figure1, 0, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playEqualEffect(){
        Field expected = figure1.getCurrentField().getNextField();
        Card card = new Card(Cardtype.EQUAL);
        LastTurn lastTurn = new LastTurn(null, null, null, null, 0);
        lastTurn.setCardtype(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setLastTurn(lastTurn);

        card.playCard(figure1, 1, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playEqualSwitch(){
        figure2.setCurrentField(playingField.getBlueStartingField());
        figure1.setCurrentField(playingField.getRedStartingField());
        Field expected1 = figure2.getCurrentField();
        Field expected2 = figure1.getCurrentField();
        Card card = new Card(Cardtype.EQUAL);
        LastTurn lastTurn = new LastTurn(null, null, null, null, 0);
        lastTurn.setCardtype(Cardtype.SWITCH);
        GameManager.getInstance().setLastTurn(lastTurn);

        card.playCard(figure1, -1, figure2);

        Assert.assertEquals(expected1, figure1.getCurrentField());
        Assert.assertEquals(expected2, figure2.getCurrentField());
    }

    @Test
    public void playMAGNET() {
        figure2.setCurrentField(playingField.getBlueStartingField());
        playingField.getBlueStartingField().setCurrentFigure(figure2);
        figure1.setCurrentField(playingField.getRedStartingField());
        playingField.getRedStartingField().setCurrentFigure(figure1);
        Field expected = figure2.getCurrentField().getPreviousField();
        Card card = new Card(Cardtype.MAGNET);

        card.playCard(figure1, -1, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playNumberCard() {
        Field expected = figure1.getCurrentField().getNextField().getNextField();
        Card card = new Card(Cardtype.TWO);

        card.playCard(figure1, -1, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playNonEffectCard_Exception(){
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        assertThrows(IllegalArgumentException.class, () ->card.playCard(figure1, -1, null));
    }

    @Test
    public void playFOUR_PLUSMINUS_effect1() {
        Field expected = figure1.getCurrentField().getNextField().getNextField().getNextField().getNextField();
        Card card = new Card(Cardtype.FOUR_PLUSMINUS);

        card.playCard(figure1, 1, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playFOUR_PLUSMINUS_effect2() {
        Field expected = figure1.getCurrentField().getPreviousField().getPreviousField().getPreviousField().getPreviousField();
        Card card = new Card(Cardtype.FOUR_PLUSMINUS);

        card.playCard(figure1, 2, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playONETOSEVEN() {
        Field expected = figure1.getCurrentField().getNextField().getNextField().getNextField();
        Card card = new Card(Cardtype.ONETOSEVEN);

        card.playCard(figure1, 3, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playONETOELEVEN_START_effect1() {
        figure1.setCurrentField(playingField.getRootField());
        Field expected = playingField.getRedStartingField();

        Card card = new Card(Cardtype.ONEORELEVEN_START);

        card.playCard(figure1, 0, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playONETOELEVEN_START_effect2() {
        Field expected = figure1.getCurrentField().getNextField();
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        card.playCard(figure1, 1, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playONETOELEVEN_START_effect3() {
        Field expected = figure1.getCurrentField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField();
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        card.playCard(figure1, 2, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playTHIRTEEN_START_effect1() {
        Field expected = playingField.getRedStartingField();
        figure1.setCurrentField(playingField.getRootField());
        Card card = new Card(Cardtype.THIRTEEN_START);

        card.playCard(figure1, 0, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playTHIRTEEN_START_effect2() {
        Field expected = figure1.getCurrentField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField().getNextField();
        Card card = new Card(Cardtype.THIRTEEN_START);

        card.playCard(figure1, 1, null);

        Assert.assertEquals(expected, figure1.getCurrentField());
    }

    @Test
    public void playEffectCard_Exception(){
        Card card = new Card(Cardtype.TWO);

        assertThrows(IllegalArgumentException.class, () ->card.playCard(figure1, 0, null));
    }

    @Test
    public void playSWITCH() {
        figure2.setCurrentField(playingField.getBlueStartingField());
        figure1.setCurrentField(playingField.getRedStartingField());
        Field expected1 = figure2.getCurrentField();
        Field expected2 = figure1.getCurrentField();
        Card card = new Card(Cardtype.SWITCH);

        card.playCard(figure1, -1, figure2);

        Assert.assertEquals(expected1, figure1.getCurrentField());
        Assert.assertEquals(expected2, figure2.getCurrentField());
    }

    @Test
    public void playSwitchCard_Exception(){
        Card card = new Card(Cardtype.TWO);

        assertThrows(IllegalArgumentException.class, () ->card.playCard(figure1, -1, figure2));
    }

    @Test
    public void illegalCombinationOfValues() {
        Card card = new Card(Cardtype.TWO);

        assertThrows(IllegalArgumentException.class, () ->card.playCard(figure1, -1, figure2));
    }
}