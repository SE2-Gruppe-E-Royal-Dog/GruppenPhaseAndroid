package com.uni.gruppenphaseandroid;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.manager.Handcards;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.LinkedList;

public class HandcardsTest {

    private Handcards handcards;

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

    @Mock
    Card card;

    @Test
    public void addCardToHandTest() {
        Assert.assertEquals(0, handcards.getMyCards().size());

        LinkedList<Card> list = new LinkedList<>();
        list.add(card);

        handcards.addCardToHand(list);

        Assert.assertEquals(1, handcards.getMyCards().size());
    }
}
