package com.uni.gruppenphaseandroid;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.CardUI;
import com.uni.gruppenphaseandroid.Cards.Cardtype;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardUITest {
/*

    CardUI cardUI;
    View view;
    Card card;
    Context context;
    CardViewFragment fragment;


    @Before
    public void setUp() {
        view = mock(View.class);
        fragment = mock(CardViewFragment.class);
        context = mock(Context.class);
        //? when(view.findViewWithTag(anyString())).thenReturn(imageView);
        cardUI = new CardUI(context, view);


    }

    @After
    public void tearDown() {
        view = null;
        cardUI = null;
        card = null;
    }

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



}
