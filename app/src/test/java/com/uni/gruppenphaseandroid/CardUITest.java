package com.uni.gruppenphaseandroid;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.CardUI;
import com.uni.gruppenphaseandroid.cards.Cardtype;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardUITest {

    CardUI cardUI;
    /*
    View view;
    Card card;
    Context context;
    CardViewFragment fragment;
     */

    @Before
    public void setUp() {
        /*
        view = mock(View.class);
        fragment = mock(CardViewFragment.class);
        context = mock(Context.class);
        //? when(view.findViewWithTag(anyString())).thenReturn(imageView);
        cardUI = new CardUI(context, view);
         */
        cardUI = CardUI.getInstance();
    }

    @After
    public void tearDown() {
        /*
        view = null;
        card = null;
         */
        cardUI = null;
    }
    /*
    @Test
    public void test1() {

        card = new Card (Cardtype.EIGTH);
        cardUI.addCardToHand(card);




    }
    */
    @Test
    public void idToCardType (){
        int id1 = R.drawable.ic_card_2;

        Assert.assertEquals(Cardtype.TWO, CardUI.getInstance().idToCardType(id1));
    }


    //TODO add more tests

    @Test
    public void cardtypeToId_TWO(){
        Cardtype cardtype = Cardtype.TWO;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_2, res);
    }

    @Test
    public void cardtypeToId_THREE(){
        Cardtype cardtype = Cardtype.THREE;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_3, res);
    }

    @Test
    public void cardtypeToId_FIVE(){
        Cardtype cardtype = Cardtype.FIVE;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_5, res);
    }

    @Test
    public void cardtypeToId_SIX(){
        Cardtype cardtype = Cardtype.SIX;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_6, res);
    }

    @Test
    public void cardtypeToId_EIGTH(){
        Cardtype cardtype = Cardtype.EIGTH;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_8, res);
    }

    @Test
    public void cardtypeToId_NINE(){
        Cardtype cardtype = Cardtype.NINE;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_9, res);
    }

    @Test
    public void cardtypeToId_TEN(){
        Cardtype cardtype = Cardtype.TEN;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_10, res);
    }

    @Test
    public void cardtypeToId_TWELVE(){
        Cardtype cardtype = Cardtype.TWELVE;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_12, res);
    }

    @Test
    public void cardtypeToId_EQUAL(){
        Cardtype cardtype = Cardtype.EQUAL;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_copy, res);
    }

    @Test
    public void cardtypeToId_FOUR(){
        Cardtype cardtype = Cardtype.FOUR_PLUSMINUS;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_4, res);
    }

    @Test
    public void cardtypeToId_SEVEN(){
        Cardtype cardtype = Cardtype.ONETOSEVEN;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_7, res);
    }

    @Test
    public void cardtypeToId_ELEVEN(){
        Cardtype cardtype = Cardtype.ONEORELEVEN_START;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_11, res);
    }

    @Test
    public void cardtypeToId_THIRTEEN(){
        Cardtype cardtype = Cardtype.THIRTEEN_START;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_13, res);
    }

    @Test
    public void cardtypeToId_MAGNET(){
        Cardtype cardtype = Cardtype.MAGNET;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_magnet, res);
    }

    @Test
    public void cardtypeToId_SWITCH(){
        Cardtype cardtype = Cardtype.SWITCH;

        int res = cardUI.cardtypeToId(cardtype);

        Assert.assertEquals(R.drawable.ic_card_switch, res);
    }

    @Test
    public void cardtypeToId_Exception(){
        assertThrows(NullPointerException.class, () ->cardUI.cardtypeToId(null));
    }
}
