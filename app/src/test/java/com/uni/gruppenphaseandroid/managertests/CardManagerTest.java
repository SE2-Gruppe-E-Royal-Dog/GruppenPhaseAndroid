package com.uni.gruppenphaseandroid.managertests;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.CardManager;
import com.uni.gruppenphaseandroid.manager.Handcards;
import com.uni.gruppenphaseandroid.manager.LastTurn;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class CardManagerTest {

    CardManager cardManager;
    FigureManager figureManager;
    ArrayList<Figure> figures;
    LinkedList<Card> handCards1;
    LinkedList<Card> handCards2;
    LinkedList<Card> handCards3;
    Card oneOrElevenCard;
    Card eightCard;
    Card thirteenCard;
    Card oneToSevenCard;
    Card switchCard;
    Card fourPlusMinus;

    Figure figure1;
    Figure figure2;

    @Before
    public void setUp(){
        cardManager = new CardManager();
        figureManager = mock(FigureManager.class);
        figures = new ArrayList<>();
        figure1 = new Figure();
        figure2 = new Figure();
        figures.add(figure1);
        figures.add(figure2);
        when(figureManager.getFiguresOfColour(Color.RED)).thenReturn(figures);
        cardManager.setFigureManager(figureManager);

        handCards1 = new LinkedList<>();
        oneOrElevenCard = mock(Card.class);
        when(oneOrElevenCard.getCardtype()).thenReturn(Cardtype.ONEORELEVEN_START);
        eightCard = mock(Card.class);
        when(eightCard.getCardtype()).thenReturn(Cardtype.EIGTH);
        handCards1.add(oneOrElevenCard);
        handCards1.add(eightCard);

        handCards2 = new LinkedList<>();
        switchCard = mock(Card.class);
        when(switchCard.getCardtype()).thenReturn(Cardtype.SWITCH);
        thirteenCard = mock(Card.class);
        when(thirteenCard.getCardtype()).thenReturn(Cardtype.THIRTEEN_START);
        handCards2.add(switchCard);
        handCards2.add(thirteenCard);

        handCards3 = new LinkedList<>();
        oneToSevenCard = mock(Card.class);
        when(oneToSevenCard.getCardtype()).thenReturn(Cardtype.ONETOSEVEN);
        fourPlusMinus = mock(Card.class);
        when(fourPlusMinus.getCardtype()).thenReturn(Cardtype.FOUR_PLUSMINUS);
        handCards3.add(oneToSevenCard);
        handCards3.add(fourPlusMinus);

    }

    @After
    public void tearDown(){
        Handcards.getInstance().setMyCards(null);
    }

    @Test
    public void testCheckIfThereIsAnyPossibleMoveForOneOrElevenAndEight(){
        Handcards.getInstance().setMyCards(handCards1);
        when(oneOrElevenCard.checkIfCardIsPlayable(figure1, 0, null)).thenReturn(false);
        when(oneOrElevenCard.checkIfCardIsPlayable(figure1, 1, null)).thenReturn(false);
        when(oneOrElevenCard.checkIfCardIsPlayable(figure1, 11, null)).thenReturn(false);
        when(eightCard.checkIfCardIsPlayable(figure1, -1, null)).thenReturn(false);

        when(oneOrElevenCard.checkIfCardIsPlayable(figure2, 0, null)).thenReturn(false);
        when(oneOrElevenCard.checkIfCardIsPlayable(figure2, 1, null)).thenReturn(false);
        when(oneOrElevenCard.checkIfCardIsPlayable(figure2, 11, null)).thenReturn(false);
        when(eightCard.checkIfCardIsPlayable(figure2, -1, null)).thenReturn(true);
        Assert.assertTrue(cardManager.isThereAnyPossibleMove(2, new LastTurn()));

        verify(oneOrElevenCard, times(1)).checkIfCardIsPlayable(figure1, 0, null);
        verify(oneOrElevenCard, times(1)).checkIfCardIsPlayable(figure1, 1, null);
        verify(oneOrElevenCard, times(1)).checkIfCardIsPlayable(figure1, 11, null);
        verify(eightCard, times(1)).checkIfCardIsPlayable(figure1, -1, null);
        verify(oneOrElevenCard, times(1)).checkIfCardIsPlayable(figure1, 0, null);
        verify(oneOrElevenCard, times(1)).checkIfCardIsPlayable(figure1, 1, null);
        verify(oneOrElevenCard, times(1)).checkIfCardIsPlayable(figure2, 11, null);
        verify(eightCard, times(1)).checkIfCardIsPlayable(figure2, -1, null);
    }

    @Test
    public void testCheckIfThereIsAnyPossibleMoveForSwitchAndThirteen(){
        Handcards.getInstance().setMyCards(handCards2);
        when(thirteenCard.checkIfCardIsPlayable(figure1, 0, null)).thenReturn(false);
        when(thirteenCard.checkIfCardIsPlayable(figure1, 13, null)).thenReturn(false);
        when(switchCard.checkIfCardIsPlayable(figure1, -1, null)).thenReturn(false);

        when(thirteenCard.checkIfCardIsPlayable(figure2, 0, null)).thenReturn(false);
        when(thirteenCard.checkIfCardIsPlayable(figure2, 13, null)).thenReturn(true);
        when(switchCard.checkIfCardIsPlayable(figure2, -1, null)).thenReturn(false);
        Assert.assertTrue(cardManager.isThereAnyPossibleMove(2, new LastTurn()));

        verify(thirteenCard, times(1)).checkIfCardIsPlayable(figure1, 0, null);
        verify(thirteenCard, times(1)).checkIfCardIsPlayable(figure1, 13, null);
        verify(switchCard, times(16)).checkIfCardIsPlayable(figure1, -1, null);
        verify(thirteenCard, times(1)).checkIfCardIsPlayable(figure2, 0, null);
        verify(thirteenCard, times(1)).checkIfCardIsPlayable(figure2, 13, null);
        verify(switchCard, times(16)).checkIfCardIsPlayable(figure2, -1, null);
    }

    @Test
    public void testCheckIfThereIsAnyPossibleMoveForOneToSevenAndFour(){
        Handcards.getInstance().setMyCards(handCards3);
        for(int i = 1; i <= 7;i++){
            when(oneToSevenCard.checkIfCardIsPlayable(figure1, i, null)).thenReturn(false);
            when(oneToSevenCard.checkIfCardIsPlayable(figure2, i, null)).thenReturn(false);
        }
        when(fourPlusMinus.checkIfCardIsPlayable(figure1, 1, null)).thenReturn(false);
        when(fourPlusMinus.checkIfCardIsPlayable(figure1, 2, null)).thenReturn(false);

        when(fourPlusMinus.checkIfCardIsPlayable(figure2, 1, null)).thenReturn(false);
        when(fourPlusMinus.checkIfCardIsPlayable(figure2, 2, null)).thenReturn(true);
        Assert.assertTrue(cardManager.isThereAnyPossibleMove(2, new LastTurn()));

        for(int i = 1; i <= 7;i++){
            verify(oneToSevenCard, times(1)).checkIfCardIsPlayable(figure1, i, null);
            verify(oneToSevenCard, times(1)).checkIfCardIsPlayable(figure2, i, null);
        }
        verify(fourPlusMinus, times(1)).checkIfCardIsPlayable(figure1, 1, null);
        verify(fourPlusMinus, times(1)).checkIfCardIsPlayable(figure1, 2, null);
        verify(fourPlusMinus, times(1)).checkIfCardIsPlayable(figure2, 1, null);
        verify(fourPlusMinus, times(1)).checkIfCardIsPlayable(figure2, 2, null);
    }

    @Test
    public void testCheckIfThereIsAnyPossibleMoveNoHand(){
        Handcards.getInstance().setMyCards(new LinkedList<>());
        Assert.assertFalse(cardManager.isThereAnyPossibleMove(2, new LastTurn()));
    }
}
