package com.uni.gruppenphaseandroid;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

    private Card card;

    @Before
    public void setup(){
        card = new Card(Cardtype.TWO);
    }

    @After
    public void cleanup(){
        card = null;
    }

    @Test
    public void getCardtypeTest(){
        Assert.assertEquals(Cardtype.TWO, card.getCardtype());
    }
}
