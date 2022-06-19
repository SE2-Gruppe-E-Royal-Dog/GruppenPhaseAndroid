package com.uni.gruppenphaseandroid;

import static org.junit.Assert.assertThrows;

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
    public void findImageView_TWO(){
        Card card = new Card(Cardtype.TWO);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_2, res);
    }

    @Test
    public void findImageView_THREE(){
        Card card = new Card(Cardtype.THREE);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_3, res);
    }

    @Test
    public void findImageView_FIVE(){
        Card card = new Card(Cardtype.FIVE);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_5, res);
    }

    @Test
    public void findImageView_SIX(){
        Card card = new Card(Cardtype.SIX);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_6, res);
    }

    @Test
    public void findImageView_EIGTH(){
        Card card = new Card(Cardtype.EIGTH);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_8, res);
    }

    @Test
    public void findImageView_NINE(){
        Card card = new Card(Cardtype.NINE);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_9, res);
    }

    @Test
    public void findImageView_TEN(){
        Card card = new Card(Cardtype.TEN);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_10, res);
    }

    @Test
    public void findImageView_TWELVE(){
        Card card = new Card(Cardtype.TWELVE);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_12, res);
    }

    @Test
    public void findImageView_EQUAL(){
        Card card = new Card(Cardtype.EQUAL);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_copy, res);
    }

    @Test
    public void findImageView_FOUR(){
        Card card = new Card(Cardtype.FOUR_PLUSMINUS);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_4, res);
    }

    @Test
    public void findImageView_SEVEN(){
        Card card = new Card(Cardtype.ONETOSEVEN);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_7, res);
    }

    @Test
    public void findImageView_ELEVEN(){
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_11, res);
    }

    @Test
    public void findImageView_THIRTEEN(){
        Card card = new Card(Cardtype.THIRTEEN_START);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_13, res);
    }

    @Test
    public void findImageView_MAGNET(){
        Card card = new Card(Cardtype.MAGNET);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_magnet, res);
    }

    @Test
    public void findImageView_SWITCH(){
        Card card = new Card(Cardtype.SWITCH);

        int res = cardUI.findImageView(card);

        Assert.assertEquals(R.drawable.ic_card_switch, res);
    }

    @Test
    public void findImageView_Exception(){
        assertThrows(NullPointerException.class, () ->cardUI.findImageView(null));
    }
}
