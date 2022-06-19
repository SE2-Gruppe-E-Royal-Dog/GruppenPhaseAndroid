package com.uni.gruppenphaseandroid;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.CardManager;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.manager.LastTurn;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class CardManagerHandCardsTest {

    private CardManager cardManager;
    private Card card;


    @Before
    public void setup() {
        cardManager = new CardManager();
    }

    @After
    public void cleanup() {
        cardManager = null;
    }

    @Test
    public void addCardToHandTest() {
        Assert.assertEquals(0, cardManager.getMyHandCards().size());
        card = mock(Card.class);
        when(card.getCardtype()).thenReturn(Cardtype.TWO);

        LinkedList<Card> list = new LinkedList<>();
        list.add(card);

        cardManager.addCardToHand(list);

        Assert.assertEquals(1, cardManager.getMyHandCards().size());
    }
}
