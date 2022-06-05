package com.uni.gruppenphaseandroid;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.Handcards;
import com.uni.gruppenphaseandroid.manager.LastTurn;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.LinkedList;

public class HandcardsTest {

    private Handcards handcards;
    private Card card;


    @Before
    public void setup() {
        handcards = Handcards.getInstance();
    }

    @After
    public void cleanup() {
        handcards = null;
    }

    @Test
    public void singletonTest() {
        Handcards secondCall = Handcards.getInstance();

        Assert.assertEquals(handcards, secondCall);
    }

    @Test
    public void addCardToHandTest() {
        Assert.assertEquals(0, handcards.getMyCards().size());
        card = mock(Card.class);
        when(card.getCardtype()).thenReturn(Cardtype.TWO);

        LinkedList<Card> list = new LinkedList<>();
        list.add(card);

        handcards.addCardToHand(list);

        Assert.assertEquals(1, handcards.getMyCards().size());
    }

    @Test
    public void discardHandcard(){
        LinkedList<Card> list = new LinkedList<>();
        card = mock(Card.class);
        when(card.getCardtype()).thenReturn(Cardtype.TWO);
        list.add(card);
        handcards.setMyCards(list);
        LastTurn lastTurn = new LastTurn(null, null, null, null, 0);
        GameManager.getInstance().setLastTurn(lastTurn);

        Assert.assertEquals(1, handcards.getMyCards().size());

        handcards.discardHandcard(0);

        Assert.assertEquals(0, handcards.getMyCards().size());
    }
}
